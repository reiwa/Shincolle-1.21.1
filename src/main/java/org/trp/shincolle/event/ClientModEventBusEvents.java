package org.trp.shincolle.event;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.client.model.*;
import org.trp.shincolle.client.particle.ParticleEmotion;
import org.trp.shincolle.client.particle.ParticleHealSparkle;
import org.trp.shincolle.client.particle.ParticleTeam;
import org.trp.shincolle.client.particle.ParticleTexts;
import org.trp.shincolle.client.renderer.*;
import org.trp.shincolle.client.renderer.block.RenderLargeShipyard;
import org.trp.shincolle.client.renderer.block.RenderSmallShipyard;
import org.trp.shincolle.client.screen.LargeShipyardScreen;
import org.trp.shincolle.client.screen.SmallShipyardScreen;
import org.trp.shincolle.client.screen.ShipInventoryScreen;
import org.trp.shincolle.init.ModBlockEntities;
import org.trp.shincolle.init.ModEntities;
import org.trp.shincolle.init.ModItems;
import org.trp.shincolle.init.ModParticles;
import org.trp.shincolle.item.CombatRationItem;
import org.trp.shincolle.item.LegacyEquipItem;
import org.trp.shincolle.item.PointerItem;
import org.trp.shincolle.item.ShipTankItem;
import org.trp.shincolle.menu.ModMenus;

@EventBusSubscriber(modid = Shincolle.MODID, value = Dist.CLIENT)
public class ClientModEventBusEvents {

    private static final float DEFAULT_MODEL_SCALE = 0.34f;
        private static final ResourceLocation LEGACY_VARIANT_MODEL_PROPERTY =
                        ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "legacy_variant");

    private static ResourceLocation entityTexture(String name) {
        return ResourceLocation.fromNamespaceAndPath(Shincolle.MODID, "textures/entity/" + name + ".png");
    }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
                event.enqueueWork(() -> {
                        registerLegacyVariantProperty(ModItems.EQUIP_AIRPLANE.get());
                        registerLegacyVariantProperty(ModItems.EQUIP_CANNON.get());
                        registerLegacyVariantProperty(ModItems.EQUIP_DRUM.get());
                        registerLegacyVariantProperty(ModItems.SHIP_TANK.get());
                        registerLegacyVariantProperty(ModItems.COMBAT_RATION.get());
                        registerLegacyVariantProperty(ModItems.POINTER_ITEM.get());
                });
        }

        private static void registerLegacyVariantProperty(net.minecraft.world.item.Item item) {
                ItemProperties.register(item, LEGACY_VARIANT_MODEL_PROPERTY, (stack, level, entity, seed) -> {
                        if (stack.getItem() instanceof LegacyEquipItem legacyEquipItem) {
                                return legacyEquipItem.getModelVariant(stack);
                        }
                        if (stack.getItem() instanceof ShipTankItem shipTankItem) {
                                return shipTankItem.getModelVariant(stack);
                        }
                        if (stack.getItem() instanceof CombatRationItem combatRationItem) {
                                return combatRationItem.getModelVariant(stack);
                        }
                        if (stack.getItem() instanceof PointerItem pointerItem) {
                                return pointerItem.getModelVariant(stack);
                        }
                        return 0.0F;
                });
        }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DESTROYER_I.get(), RendererDestroyerI::new);
        event.registerEntityRenderer(ModEntities.DESTROYER_RO.get(), RendererDestroyerRo::new);
        event.registerEntityRenderer(ModEntities.DESTROYER_HA.get(), RendererDestroyerHa::new);
        event.registerEntityRenderer(ModEntities.DESTROYER_NI.get(), RendererDestroyerNi::new);
        event.registerEntityRenderer(ModEntities.HEAVY_CRUISER_RI.get(), RendererHeavyCruiserRi::new);
        event.registerEntityRenderer(ModEntities.HEAVY_CRUISER_NE.get(), RendererHeavyCruiserNe::new);
        event.registerEntityRenderer(ModEntities.CARRIER_WO.get(), RendererCarrierWo::new);
        event.registerEntityRenderer(ModEntities.BATTLESHIP_RU.get(), RendererBattleshipRu::new);
        event.registerEntityRenderer(ModEntities.BATTLESHIP_TA.get(), RendererBattleshipTa::new);
        event.registerEntityRenderer(ModEntities.BATTLESHIP_RE.get(), RendererBattleshipRe::new);
        event.registerEntityRenderer(ModEntities.TRANSPORT_WA.get(), RendererTransportWa::new);
        event.registerEntityRenderer(ModEntities.SUBM_KA.get(), RendererSubmKa::new);
        event.registerEntityRenderer(ModEntities.SUBM_YO.get(), RendererSubmYo::new);
        event.registerEntityRenderer(ModEntities.SUBM_SO.get(), RendererSubmSo::new);
        event.registerEntityRenderer(ModEntities.DESTROYER_HIME.get(), RendererDestroyerHime::new);
        event.registerEntityRenderer(ModEntities.CA_HIME.get(), RendererCAHime::new);
        event.registerEntityRenderer(ModEntities.AIRFIELD_HIME.get(), RendererAirfieldHime::new);
        event.registerEntityRenderer(ModEntities.BATTLESHIP_HIME.get(), RendererBattleshipHime::new);
        event.registerEntityRenderer(ModEntities.CARRIER_HIME.get(), RendererCarrierHime::new);
        event.registerEntityRenderer(ModEntities.HARBOUR_HIME.get(), RendererHarbourHime::new);
        event.registerEntityRenderer(ModEntities.ISOLATED_HIME.get(), RendererIsolatedHime::new);
        event.registerEntityRenderer(ModEntities.MIDWAY_HIME.get(), RendererMidwayHime::new);
        event.registerEntityRenderer(ModEntities.NORTHERN_HIME.get(), RendererNorthernHime::new);
        event.registerEntityRenderer(ModEntities.SUBM_HIME.get(), RendererSubmHime::new);
        event.registerEntityRenderer(ModEntities.SSNH.get(), RendererSSNH::new);
        event.registerEntityRenderer(ModEntities.DESTROYER_AKATSUKI.get(), RendererDestroyerAkatsuki::new);
        event.registerEntityRenderer(ModEntities.DESTROYER_HIBIKI.get(), RendererDestroyerHibiki::new);
        event.registerEntityRenderer(ModEntities.DESTROYER_IKAZUCHI.get(), RendererDestroyerIkazuchi::new);
        event.registerEntityRenderer(ModEntities.DESTROYER_INAZUMA.get(), RendererDestroyerInazuma::new);
        event.registerEntityRenderer(ModEntities.DESTROYER_SHIMAKAZE.get(), RendererDestroyerShimakaze::new);
        event.registerEntityRenderer(ModEntities.CARRIER_W_DEMON.get(), RendererCarrierWDemon::new);
        event.registerEntityRenderer(ModEntities.CRUISER_TENRYUU.get(), RendererCruiserTenryuu::new);
        event.registerEntityRenderer(ModEntities.CRUISER_TATSUTA.get(), RendererCruiserTatsuta::new);
        event.registerEntityRenderer(ModEntities.CRUISER_TAKAO.get(), RendererCruiserTakao::new);
        event.registerEntityRenderer(ModEntities.CRUISER_ATAGO.get(), RendererCruiserAtago::new);
        event.registerEntityRenderer(ModEntities.CARRIER_KAGA.get(), RendererCarrierKaga::new);
        event.registerEntityRenderer(ModEntities.CARRIER_AKAGI.get(), RendererCarrierAkagi::new);
        event.registerEntityRenderer(ModEntities.BB_KONGOU.get(), RendererBBKongou::new);
        event.registerEntityRenderer(ModEntities.BB_HIEI.get(), RendererBBHiei::new);
        event.registerEntityRenderer(ModEntities.BB_HARUNA.get(), RendererBBHaruna::new);
        event.registerEntityRenderer(ModEntities.BB_KIRISHIMA.get(), RendererBBKirishima::new);
        event.registerEntityRenderer(ModEntities.BATTLESHIP_NAGATO.get(), RendererBattleshipNagato::new);
        event.registerEntityRenderer(ModEntities.BATTLESHIP_YAMATO.get(), RendererBattleshipYamato::new);
        event.registerEntityRenderer(ModEntities.SUBM_U511.get(), RendererSubmU511::new);
        event.registerEntityRenderer(ModEntities.SUBM_RO500.get(), RendererSubmRo500::new);

        event.registerEntityRenderer(ModEntities.AIRPLANE.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelAirplane<>(context.bakeLayer(ModelAirplane.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("airplane")));
        event.registerEntityRenderer(ModEntities.AIRPLANE_T.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelAirplaneT<>(context.bakeLayer(ModelAirplaneT.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("airplane_t")));
        event.registerEntityRenderer(ModEntities.AIRPLANE_ZERO.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelAirplaneZero<>(context.bakeLayer(ModelAirplaneZero.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("airplane_zero")));
        event.registerEntityRenderer(ModEntities.MOUNT_AF_H.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelMountAfH<>(context.bakeLayer(ModelMountAfH.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("mount_af_h")));
        event.registerEntityRenderer(ModEntities.MOUNT_BA_H.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelMountBaH<>(context.bakeLayer(ModelMountBaH.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("mount_ba_h")));
        event.registerEntityRenderer(ModEntities.MOUNT_CA_H.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelMountCaH<>(context.bakeLayer(ModelMountCaH.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("mount_ca_h")));
        event.registerEntityRenderer(ModEntities.MOUNT_CA_WD.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelMountCaWD<>(context.bakeLayer(ModelMountCaWD.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("mount_ca_wd")));
        event.registerEntityRenderer(ModEntities.MOUNT_HB_H.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelMountHbH<>(context.bakeLayer(ModelMountHbH.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("mount_hb_h")));
        event.registerEntityRenderer(ModEntities.MOUNT_IS_H.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelMountIsH<>(context.bakeLayer(ModelMountIsH.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("mount_is_h")));
        event.registerEntityRenderer(ModEntities.MOUNT_MI_H.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelMountMiH<>(context.bakeLayer(ModelMountMiH.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("mount_mi_h")));
        event.registerEntityRenderer(ModEntities.MOUNT_SU_H.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelMountSuH<>(context.bakeLayer(ModelMountSuH.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("mount_su_h")));
        event.registerEntityRenderer(ModEntities.RENSOUHOU.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelRensouhou<>(context.bakeLayer(ModelRensouhou.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("rensouhou")));
        event.registerEntityRenderer(ModEntities.RENSOUHOU_S.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelRensouhouS<>(context.bakeLayer(ModelRensouhouS.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("rensouhou_s")));
        event.registerEntityRenderer(ModEntities.TAKOYAKI.get(), context -> new RendererSimpleMob<>(
                context,
                new ModelTakoyaki<>(context.bakeLayer(ModelTakoyaki.LAYER_LOCATION)),
                0.5f,
                DEFAULT_MODEL_SCALE,
                entityTexture("takoyaki")));
        event.registerEntityRenderer(ModEntities.ABYSS_MISSILE.get(), RendererAbyssMissile::new);
        event.registerEntityRenderer(ModEntities.PROJECTILE_BEAM.get(), RendererProjectileBeam::new);
        event.registerEntityRenderer(ModEntities.SHIP_GRUDGE.get(), RendererShipGrudge::new);

        event.registerBlockEntityRenderer(ModBlockEntities.SMALL_SHIPYARD.get(), RenderSmallShipyard::new);
        event.registerBlockEntityRenderer(ModBlockEntities.LARGE_SHIPYARD.get(), RenderLargeShipyard::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelDestroyerI.LAYER_LOCATION, ModelDestroyerI::createBodyLayer);
        event.registerLayerDefinition(ModelDestroyerRo.LAYER_LOCATION, ModelDestroyerRo::createBodyLayer);
        event.registerLayerDefinition(ModelDestroyerHa.LAYER_LOCATION, ModelDestroyerHa::createBodyLayer);
        event.registerLayerDefinition(ModelDestroyerNi.LAYER_LOCATION, ModelDestroyerNi::createBodyLayer);
        event.registerLayerDefinition(ModelHeavyCruiserRi.LAYER_LOCATION, ModelHeavyCruiserRi::createBodyLayer);
        event.registerLayerDefinition(ModelHeavyCruiserNe.LAYER_LOCATION, ModelHeavyCruiserNe::createBodyLayer);
        event.registerLayerDefinition(ModelCarrierWo.LAYER_LOCATION, ModelCarrierWo::createBodyLayer);
        event.registerLayerDefinition(ModelBattleshipRu.LAYER_LOCATION, ModelBattleshipRu::createBodyLayer);
        event.registerLayerDefinition(ModelBattleshipTa.LAYER_LOCATION, ModelBattleshipTa::createBodyLayer);
        event.registerLayerDefinition(ModelBattleshipRe.LAYER_LOCATION, ModelBattleshipRe::createBodyLayer);
        event.registerLayerDefinition(ModelTransportWa.LAYER_LOCATION, ModelTransportWa::createBodyLayer);
        event.registerLayerDefinition(ModelSubmKa.LAYER_LOCATION, ModelSubmKa::createBodyLayer);
        event.registerLayerDefinition(ModelSubmYo.LAYER_LOCATION, ModelSubmYo::createBodyLayer);
        event.registerLayerDefinition(ModelSubmSo.LAYER_LOCATION, ModelSubmSo::createBodyLayer);
        event.registerLayerDefinition(ModelDestroyerHime.LAYER_LOCATION, ModelDestroyerHime::createBodyLayer);
        event.registerLayerDefinition(ModelCAHime.LAYER_LOCATION, ModelCAHime::createBodyLayer);
        event.registerLayerDefinition(ModelAirfieldHime.LAYER_LOCATION, ModelAirfieldHime::createBodyLayer);
        event.registerLayerDefinition(ModelBattleshipHime.LAYER_LOCATION, ModelBattleshipHime::createBodyLayer);
        event.registerLayerDefinition(ModelCarrierHime.LAYER_LOCATION, ModelCarrierHime::createBodyLayer);
        event.registerLayerDefinition(ModelHarbourHime.LAYER_LOCATION, ModelHarbourHime::createBodyLayer);
        event.registerLayerDefinition(ModelIsolatedHime.LAYER_LOCATION, ModelIsolatedHime::createBodyLayer);
        event.registerLayerDefinition(ModelMidwayHime.LAYER_LOCATION, ModelMidwayHime::createBodyLayer);
        event.registerLayerDefinition(ModelNorthernHime.LAYER_LOCATION, ModelNorthernHime::createBodyLayer);
        event.registerLayerDefinition(ModelSubmHime.LAYER_LOCATION, ModelSubmHime::createBodyLayer);
        event.registerLayerDefinition(ModelSSNH.LAYER_LOCATION, ModelSSNH::createBodyLayer);
        event.registerLayerDefinition(ModelCarrierWDemon.LAYER_LOCATION, ModelCarrierWDemon::createBodyLayer);
        event.registerLayerDefinition(ModelDestroyerAkatsuki.LAYER_LOCATION, ModelDestroyerAkatsuki::createBodyLayer);
        event.registerLayerDefinition(ModelDestroyerHibiki.LAYER_LOCATION, ModelDestroyerHibiki::createBodyLayer);
        event.registerLayerDefinition(ModelDestroyerIkazuchi.LAYER_LOCATION, ModelDestroyerIkazuchi::createBodyLayer);
        event.registerLayerDefinition(ModelDestroyerInazuma.LAYER_LOCATION, ModelDestroyerInazuma::createBodyLayer);
        event.registerLayerDefinition(ModelDestroyerShimakaze.LAYER_LOCATION, ModelDestroyerShimakaze::createBodyLayer);
        event.registerLayerDefinition(ModelCruiserTenryuu.LAYER_LOCATION, ModelCruiserTenryuu::createBodyLayer);
        event.registerLayerDefinition(ModelCruiserTatsuta.LAYER_LOCATION, ModelCruiserTatsuta::createBodyLayer);
        event.registerLayerDefinition(ModelCruiserTakao.LAYER_LOCATION, ModelCruiserTakao::createBodyLayer);
        event.registerLayerDefinition(ModelCruiserAtago.LAYER_LOCATION, ModelCruiserAtago::createBodyLayer);
        event.registerLayerDefinition(ModelCarrierKaga.LAYER_LOCATION, ModelCarrierKaga::createBodyLayer);
        event.registerLayerDefinition(ModelCarrierAkagi.LAYER_LOCATION, ModelCarrierAkagi::createBodyLayer);
        event.registerLayerDefinition(ModelBBKongou.LAYER_LOCATION, ModelBBKongou::createBodyLayer);
        event.registerLayerDefinition(ModelBBHiei.LAYER_LOCATION, ModelBBHiei::createBodyLayer);
        event.registerLayerDefinition(ModelBBHaruna.LAYER_LOCATION, ModelBBHaruna::createBodyLayer);
        event.registerLayerDefinition(ModelBBKirishima.LAYER_LOCATION, ModelBBKirishima::createBodyLayer);
        event.registerLayerDefinition(ModelBattleshipNagato.LAYER_LOCATION, ModelBattleshipNagato::createBodyLayer);
        event.registerLayerDefinition(ModelBattleshipYamato.LAYER_LOCATION, ModelBattleshipYamato::createBodyLayer);
        event.registerLayerDefinition(ModelSubmU511.LAYER_LOCATION, ModelSubmU511::createBodyLayer);
        event.registerLayerDefinition(ModelSubmRo500.LAYER_LOCATION, ModelSubmRo500::createBodyLayer);

        event.registerLayerDefinition(ModelAirplane.LAYER_LOCATION, ModelAirplane::createBodyLayer);
        event.registerLayerDefinition(ModelAirplaneT.LAYER_LOCATION, ModelAirplaneT::createBodyLayer);
        event.registerLayerDefinition(ModelAirplaneZero.LAYER_LOCATION, ModelAirplaneZero::createBodyLayer);
        event.registerLayerDefinition(ModelMountAfH.LAYER_LOCATION, ModelMountAfH::createBodyLayer);
        event.registerLayerDefinition(ModelMountBaH.LAYER_LOCATION, ModelMountBaH::createBodyLayer);
        event.registerLayerDefinition(ModelMountCaH.LAYER_LOCATION, ModelMountCaH::createBodyLayer);
        event.registerLayerDefinition(ModelMountCaWD.LAYER_LOCATION, ModelMountCaWD::createBodyLayer);
        event.registerLayerDefinition(ModelMountHbH.LAYER_LOCATION, ModelMountHbH::createBodyLayer);
        event.registerLayerDefinition(ModelMountIsH.LAYER_LOCATION, ModelMountIsH::createBodyLayer);
        event.registerLayerDefinition(ModelMountMiH.LAYER_LOCATION, ModelMountMiH::createBodyLayer);
        event.registerLayerDefinition(ModelMountSuH.LAYER_LOCATION, ModelMountSuH::createBodyLayer);
        event.registerLayerDefinition(ModelRensouhou.LAYER_LOCATION, ModelRensouhou::createBodyLayer);
        event.registerLayerDefinition(ModelRensouhouS.LAYER_LOCATION, ModelRensouhouS::createBodyLayer);
        event.registerLayerDefinition(ModelTakoyaki.LAYER_LOCATION, ModelTakoyaki::createBodyLayer);
        event.registerLayerDefinition(ModelAbyssMissile.LAYER_LOCATION, ModelAbyssMissile::createBodyLayer);
        event.registerLayerDefinition(ModelProjectileBeam.LAYER_LOCATION, ModelProjectileBeam::createBodyLayer);
        event.registerLayerDefinition(ModelShipGrudge.LAYER_LOCATION, ModelShipGrudge::createBodyLayer);
                event.registerLayerDefinition(ModelSmallShipyard.LAYER_LOCATION, ModelSmallShipyard::createBodyLayer);
                event.registerLayerDefinition(ModelLargeShipyard.LAYER_LOCATION, ModelLargeShipyard::createBodyLayer);
                event.registerLayerDefinition(ModelVortex.LAYER_LOCATION, ModelVortex::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.SHIP_MENU.get(), ShipInventoryScreen::new);
                event.register(ModMenus.SMALL_SHIPYARD_MENU.get(), SmallShipyardScreen::new);
                event.register(ModMenus.LARGE_SHIPYARD_MENU.get(), LargeShipyardScreen::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.PARTICLE_EMOTION.get(), ParticleEmotion.Provider::new);
        event.registerSpriteSet(ModParticles.PARTICLE_HEAL_SPARKLE.get(), ParticleHealSparkle.Provider::new);
        event.registerSpriteSet(ModParticles.PARTICLE_TEXTS.get(), ParticleTexts.Provider::new);
        event.registerSpriteSet(ModParticles.PARTICLE_TEAM.get(), ParticleTeam.Provider::new);
        event.registerSpriteSet(ModParticles.PARTICLE_TEAM_SELECTED.get(),
                sprites -> new ParticleTeam.Provider(sprites, ParticleTeam.RenderStyle.DEFAULT_BLUE));
        event.registerSpriteSet(ModParticles.PARTICLE_TEAM_SELECTED_RED.get(),
                sprites -> new ParticleTeam.Provider(sprites, ParticleTeam.RenderStyle.SELECTED_RED));
        event.registerSpriteSet(ModParticles.PARTICLE_TEAM_TARGET.get(),
                sprites -> new ParticleTeam.Provider(sprites, ParticleTeam.RenderStyle.TARGET_WHITE));
        event.registerSpriteSet(ModParticles.PARTICLE_TEAM_TARGET_ENTITY.get(),
                sprites -> new ParticleTeam.Provider(sprites, ParticleTeam.RenderStyle.TARGET_RED));
    }
}
