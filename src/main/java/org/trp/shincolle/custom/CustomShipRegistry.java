package org.trp.shincolle.custom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public final class CustomShipRegistry {
    private static final Logger LOGGER = LogManager.getLogger(CustomShipRegistry.class);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final String NAMESPACE = "shincolle_custom";
    private static final Map<String, CustomShipConfig> REGISTERED_SHIPS = new ConcurrentHashMap<>();

    private static final Map<String, ResourceLocation> TEXTURE_LOCATIONS = new ConcurrentHashMap<>();

    private static final boolean geckoLibLoaded = net.neoforged.fml.loading.LoadingModList.get().getModFileById("geckolib") != null;

    private CustomShipRegistry() {
    }

    public static Path getCustomShipsDir() {
        return FMLPaths.GAMEDIR.get().resolve("shincolle").resolve("custom_ships");
    }

    public static void scanAndRegister() {
        REGISTERED_SHIPS.clear();
        if (isGeckoLibLoaded()) {
            CustomShipGeoData.clear();
        }
        TEXTURE_LOCATIONS.clear();

        Path dir = getCustomShipsDir();
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
                LOGGER.info("Created custom ships directory: {}", dir);
            } catch (Exception e) {
                LOGGER.error("Failed to create custom ships directory: {}", dir, e);
                return;
            }
        }

        try (Stream<Path> stream = Files.list(dir)) {
            stream.filter(Files::isDirectory).forEach(CustomShipRegistry::loadShipFolder);
        } catch (Exception e) {
            LOGGER.error("Error scanning custom ships directory", e);
        }

        LOGGER.info("Registered {} custom ships.", REGISTERED_SHIPS.size());
    }

    private static void loadShipFolder(Path folder) {
        Path jsonPath = folder.resolve("ship.json");
        if (!Files.exists(jsonPath)) {
            return;
        }

        try (Reader reader = Files.newBufferedReader(jsonPath)) {
            CustomShipConfig config = GSON.fromJson(reader, CustomShipConfig.class);
            if (config == null || config.id == null || config.id.isBlank()) {
                LOGGER.warn("Invalid ship.json in folder: {}", folder);
                return;
            }

            config.folderPath = folder;
            REGISTERED_SHIPS.put(config.id, config);
            LOGGER.info("Loaded custom ship config: {} ({}) from {}", config.name, config.id, folder.getFileName());

            if (FMLEnvironment.dist.isClient()) {
                loadClientAssets(config, folder);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to load custom ship from folder: {}", folder, e);
        }
    }

    private static void loadClientAssets(CustomShipConfig config, Path folder) {
        String id = config.id;

        Path texPath = folder.resolve("texture.png");
        if (Files.exists(texPath)) {
            ResourceLocation texLoc = ResourceLocation.fromNamespaceAndPath(NAMESPACE, "textures/entity/" + id + ".png");
            TEXTURE_LOCATIONS.put(id, texLoc);
            LOGGER.debug("Recognized custom ship texture path: {}", texPath);
        } else {
            LOGGER.warn("Missing texture.png in: {}", folder);
        }

        if (isGeckoLibLoaded()) {
            CustomShipGeoData.loadAssets(id, folder);
        } else {
            LOGGER.warn("GeckoLib is not installed; custom ship '{}' will not be rendered.", id);
        }
    }

    public static CustomShipConfig getConfig(String id) {
        return REGISTERED_SHIPS.get(id);
    }

    public static Collection<CustomShipConfig> getAllConfigs() {
        return Collections.unmodifiableCollection(REGISTERED_SHIPS.values());
    }

    public static boolean hasShip(String id) {
        return REGISTERED_SHIPS.containsKey(id);
    }

    public static boolean hasBakedModel(String id) {
        return isGeckoLibLoaded() && CustomShipGeoData.hasBakedModel(id);
    }

    public static ResourceLocation getTextureLocation(String id) {
        if (!FMLEnvironment.dist.isClient() || id == null || id.isEmpty()) {
            return null;
        }
        ResourceLocation texLoc = TEXTURE_LOCATIONS.computeIfAbsent(id,
                k -> ResourceLocation.fromNamespaceAndPath(NAMESPACE, "textures/entity/" + k + ".png"));

        ensureTextureRegistered(id, texLoc);
        return texLoc;
    }

    private static void ensureTextureRegistered(String id, ResourceLocation texLoc) {
        try {
            var tm = Minecraft.getInstance().getTextureManager();
            if (tm == null) {
                return;
            }
            var existing = tm.getTexture(texLoc, null);
            if (existing == null || existing == net.minecraft.client.renderer.texture.MissingTextureAtlasSprite.getTexture()) {
                CustomShipConfig config = REGISTERED_SHIPS.get(id);
                if (config != null && config.folderPath != null) {
                    Path texPath = config.folderPath.resolve("texture.png");
                    if (Files.exists(texPath)) {
                        try (InputStream is = Files.newInputStream(texPath)) {
                            NativeImage nativeImage = NativeImage.read(is);
                            DynamicTexture dynamicTexture = new DynamicTexture(nativeImage);
                            tm.register(texLoc, dynamicTexture);
                            LOGGER.info("Registered DynamicTexture for custom ship: {}", texLoc);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to ensure texture registered for {}: {}", id, t.getMessage());
        }
    }

    public static boolean hasAnimation(String id, String animName) {
        return isGeckoLibLoaded() && CustomShipGeoData.hasAnimation(id, animName);
    }

    /** GeckoLib is an optional dependency; any GeckoLib-touching code must be guarded by this. */
    public static boolean isGeckoLibLoaded() {
        return geckoLibLoaded;
    }
}
