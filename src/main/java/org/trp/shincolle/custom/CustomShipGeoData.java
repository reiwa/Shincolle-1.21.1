package org.trp.shincolle.custom;

import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.loading.json.raw.Model;
import software.bernie.geckolib.loading.json.typeadapter.KeyFramesAdapter;
import software.bernie.geckolib.loading.object.BakedAnimations;
import software.bernie.geckolib.loading.object.BakedModelFactory;
import software.bernie.geckolib.loading.object.GeometryTree;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CustomShipGeoData {
    private static final Logger LOGGER = LogManager.getLogger(CustomShipGeoData.class);

    private static final Map<String, BakedGeoModel> BAKED_MODELS = new ConcurrentHashMap<>();
    private static final Map<String, BakedAnimations> BAKED_ANIMATIONS = new ConcurrentHashMap<>();

    private CustomShipGeoData() {
    }

    public static void clear() {
        BAKED_MODELS.clear();
        BAKED_ANIMATIONS.clear();
    }

    public static void loadAssets(String id, Path folder) {
        Path geoPath = folder.resolve("model.geo.json");
        if (Files.exists(geoPath)) {
            try (Reader geoReader = Files.newBufferedReader(geoPath)) {
                JsonObject jsonObject = GsonHelper.fromJson(KeyFramesAdapter.GEO_GSON, geoReader, JsonObject.class);
                Model rawModel = KeyFramesAdapter.GEO_GSON.fromJson(jsonObject, Model.class);
                BakedGeoModel bakedModel = BakedModelFactory.DEFAULT_FACTORY.constructGeoModel(GeometryTree.fromModel(rawModel));
                BAKED_MODELS.put(id, bakedModel);
                LOGGER.info("Successfully baked custom ship model for: {}", id);
            } catch (Exception e) {
                LOGGER.error("Failed to bake model for custom ship: {}", id, e);
            }
        } else {
            LOGGER.warn("Missing model.geo.json in: {}", folder);
        }

        Path animPath = folder.resolve("animation.json");
        if (Files.exists(animPath)) {
            try (Reader animReader = Files.newBufferedReader(animPath)) {
                JsonObject jsonObject = GsonHelper.fromJson(KeyFramesAdapter.GEO_GSON, animReader, JsonObject.class);
                if (jsonObject.has("animations")) {
                    JsonObject animsObj = GsonHelper.getAsJsonObject(jsonObject, "animations");
                    BakedAnimations bakedAnimations = KeyFramesAdapter.GEO_GSON.fromJson(animsObj, BakedAnimations.class);
                    BAKED_ANIMATIONS.put(id, bakedAnimations);
                    LOGGER.info("Successfully baked custom ship animations for: {}", id);
                }
            } catch (Exception e) {
                LOGGER.error("Failed to load animations for custom ship: {}", id, e);
            }
        }
    }

    public static BakedGeoModel getBakedModel(String id) {
        return BAKED_MODELS.get(id);
    }

    public static BakedAnimations getBakedAnimations(String id) {
        return BAKED_ANIMATIONS.get(id);
    }

    public static boolean hasBakedModel(String id) {
        return BAKED_MODELS.containsKey(id);
    }

    public static boolean hasAnimation(String id, String animName) {
        BakedAnimations anims = BAKED_ANIMATIONS.get(id);
        return anims != null && anims.getAnimation(animName) != null;
    }
}
