package org.trp.shincolle.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.trp.shincolle.Shincolle;
import org.trp.shincolle.entity.*;
import org.trp.shincolle.entity.projectile.EntityAbyssMissile;
import org.trp.shincolle.entity.projectile.EntityProjectileBeam;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Shincolle.MODID);

    private ModEntities() {}

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerI>> DESTROYER_I = ENTITY_TYPES.register("destroyer_i",
            () -> EntityType.Builder.of(EntityDestroyerI::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_i"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerRo>> DESTROYER_RO = ENTITY_TYPES.register("destroyer_ro",
            () -> EntityType.Builder.of(EntityDestroyerRo::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_ro"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerHa>> DESTROYER_HA = ENTITY_TYPES.register("destroyer_ha",
            () -> EntityType.Builder.of(EntityDestroyerHa::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_ha"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerNi>> DESTROYER_NI = ENTITY_TYPES.register("destroyer_ni",
            () -> EntityType.Builder.of(EntityDestroyerNi::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_ni"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityHeavyCruiserRi>> HEAVY_CRUISER_RI = ENTITY_TYPES.register("heavy_cruiser_ri",
            () -> EntityType.Builder.of(EntityHeavyCruiserRi::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("heavy_cruiser_ri"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityHeavyCruiserNe>> HEAVY_CRUISER_NE = ENTITY_TYPES.register("heavy_cruiser_ne",
            () -> EntityType.Builder.of(EntityHeavyCruiserNe::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("heavy_cruiser_ne"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCarrierWo>> CARRIER_WO = ENTITY_TYPES.register("carrier_wo",
            () -> EntityType.Builder.of(EntityCarrierWo::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("carrier_wo"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBattleshipRu>> BATTLESHIP_RU = ENTITY_TYPES.register("battleship_ru",
            () -> EntityType.Builder.of(EntityBattleshipRu::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("battleship_ru"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBattleshipTa>> BATTLESHIP_TA = ENTITY_TYPES.register("battleship_ta",
            () -> EntityType.Builder.of(EntityBattleshipTa::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("battleship_ta"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBattleshipRe>> BATTLESHIP_RE = ENTITY_TYPES.register("battleship_re",
            () -> EntityType.Builder.of(EntityBattleshipRe::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("battleship_re"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityTransportWa>> TRANSPORT_WA = ENTITY_TYPES.register("transport_wa",
            () -> EntityType.Builder.of(EntityTransportWa::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("transport_wa"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntitySubmKa>> SUBM_KA = ENTITY_TYPES.register("subm_ka",
            () -> EntityType.Builder.of(EntitySubmKa::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("subm_ka"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntitySubmYo>> SUBM_YO = ENTITY_TYPES.register("subm_yo",
            () -> EntityType.Builder.of(EntitySubmYo::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("subm_yo"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntitySubmSo>> SUBM_SO = ENTITY_TYPES.register("subm_so",
            () -> EntityType.Builder.of(EntitySubmSo::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("subm_so"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerHime>> DESTROYER_HIME = ENTITY_TYPES.register("destroyer_hime",
            () -> EntityType.Builder.of(EntityDestroyerHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCAHime>> CA_HIME = ENTITY_TYPES.register("ca_hime",
            () -> EntityType.Builder.of(EntityCAHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("ca_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityAirfieldHime>> AIRFIELD_HIME = ENTITY_TYPES.register("airfield_hime",
            () -> EntityType.Builder.of(EntityAirfieldHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("airfield_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBattleshipHime>> BATTLESHIP_HIME = ENTITY_TYPES.register("battleship_hime",
            () -> EntityType.Builder.of(EntityBattleshipHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("battleship_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCarrierHime>> CARRIER_HIME = ENTITY_TYPES.register("carrier_hime",
            () -> EntityType.Builder.of(EntityCarrierHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("carrier_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityHarbourHime>> HARBOUR_HIME = ENTITY_TYPES.register("harbour_hime",
            () -> EntityType.Builder.of(EntityHarbourHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("harbour_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityIsolatedHime>> ISOLATED_HIME = ENTITY_TYPES.register("isolated_hime",
            () -> EntityType.Builder.of(EntityIsolatedHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("isolated_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMidwayHime>> MIDWAY_HIME = ENTITY_TYPES.register("midway_hime",
            () -> EntityType.Builder.of(EntityMidwayHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("midway_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityNorthernHime>> NORTHERN_HIME = ENTITY_TYPES.register("northern_hime",
            () -> EntityType.Builder.of(EntityNorthernHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("northern_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntitySubmHime>> SUBM_HIME = ENTITY_TYPES.register("subm_hime",
            () -> EntityType.Builder.of(EntitySubmHime::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("subm_hime"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntitySSNH>> SSNH = ENTITY_TYPES.register("ssnh",
            () -> EntityType.Builder.of(EntitySSNH::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("ssnh"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCarrierWDemon>> CARRIER_W_DEMON = ENTITY_TYPES.register("carrier_w_demon",
            () -> EntityType.Builder.of(EntityCarrierWDemon::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("carrier_w_demon"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerAkatsuki>> DESTROYER_AKATSUKI = ENTITY_TYPES.register("destroyer_akatsuki",
            () -> EntityType.Builder.of(EntityDestroyerAkatsuki::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_akatsuki"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerHibiki>> DESTROYER_HIBIKI = ENTITY_TYPES.register("destroyer_hibiki",
            () -> EntityType.Builder.of(EntityDestroyerHibiki::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_hibiki"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerIkazuchi>> DESTROYER_IKAZUCHI = ENTITY_TYPES.register("destroyer_ikazuchi",
            () -> EntityType.Builder.of(EntityDestroyerIkazuchi::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_ikazuchi"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerInazuma>> DESTROYER_INAZUMA = ENTITY_TYPES.register("destroyer_inazuma",
            () -> EntityType.Builder.of(EntityDestroyerInazuma::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_inazuma"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityDestroyerShimakaze>> DESTROYER_SHIMAKAZE = ENTITY_TYPES.register("destroyer_shimakaze",
            () -> EntityType.Builder.of(EntityDestroyerShimakaze::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("destroyer_shimakaze"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCruiserTenryuu>> CRUISER_TENRYUU = ENTITY_TYPES.register("cruiser_tenryuu",
            () -> EntityType.Builder.of(EntityCruiserTenryuu::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("cruiser_tenryuu"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCruiserTatsuta>> CRUISER_TATSUTA = ENTITY_TYPES.register("cruiser_tatsuta",
            () -> EntityType.Builder.of(EntityCruiserTatsuta::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("cruiser_tatsuta"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCruiserTakao>> CRUISER_TAKAO = ENTITY_TYPES.register("cruiser_takao",
            () -> EntityType.Builder.of(EntityCruiserTakao::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("cruiser_takao"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCruiserAtago>> CRUISER_ATAGO = ENTITY_TYPES.register("cruiser_atago",
            () -> EntityType.Builder.of(EntityCruiserAtago::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("cruiser_atago"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCarrierKaga>> CARRIER_KAGA = ENTITY_TYPES.register("carrier_kaga",
            () -> EntityType.Builder.of(EntityCarrierKaga::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("carrier_kaga"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityCarrierAkagi>> CARRIER_AKAGI = ENTITY_TYPES.register("carrier_akagi",
            () -> EntityType.Builder.of(EntityCarrierAkagi::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("carrier_akagi"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBBKongou>> BB_KONGOU = ENTITY_TYPES.register("bb_kongou",
            () -> EntityType.Builder.of(EntityBBKongou::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("bb_kongou"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBBHiei>> BB_HIEI = ENTITY_TYPES.register("bb_hiei",
            () -> EntityType.Builder.of(EntityBBHiei::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("bb_hiei"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBBHaruna>> BB_HARUNA = ENTITY_TYPES.register("bb_haruna",
            () -> EntityType.Builder.of(EntityBBHaruna::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("bb_haruna"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBBKirishima>> BB_KIRISHIMA = ENTITY_TYPES.register("bb_kirishima",
            () -> EntityType.Builder.of(EntityBBKirishima::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("bb_kirishima"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBattleshipNagato>> BATTLESHIP_NAGATO = ENTITY_TYPES.register("battleship_nagato",
            () -> EntityType.Builder.of(EntityBattleshipNagato::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("battleship_nagato"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityBattleshipYamato>> BATTLESHIP_YAMATO = ENTITY_TYPES.register("battleship_yamato",
            () -> EntityType.Builder.of(EntityBattleshipYamato::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("battleship_yamato"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntitySubmU511>> SUBM_U511 = ENTITY_TYPES.register("subm_u511",
            () -> EntityType.Builder.of(EntitySubmU511::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("subm_u511"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntitySubmRo500>> SUBM_RO500 = ENTITY_TYPES.register("subm_ro500",
            () -> EntityType.Builder.of(EntitySubmRo500::new, MobCategory.MISC)
                    .sized(0.5f, 0.9f)
                    .build("subm_ro500"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityAbyssMissile>> ABYSS_MISSILE = ENTITY_TYPES.register("abyss_missile",
            () -> EntityType.Builder.<EntityAbyssMissile>of(EntityAbyssMissile::new, MobCategory.MISC)
                    .sized(0.6f, 0.6f)
                    .clientTrackingRange(64)
                    .updateInterval(1)
                    .build("abyss_missile"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityAirplane>> AIRPLANE = ENTITY_TYPES.register("airplane",
            () -> EntityType.Builder.of(EntityAirplane::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("airplane"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityAirplaneT>> AIRPLANE_T = ENTITY_TYPES.register("airplane_t",
            () -> EntityType.Builder.of(EntityAirplaneT::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("airplane_t"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityAirplaneZero>> AIRPLANE_ZERO = ENTITY_TYPES.register("airplane_zero",
            () -> EntityType.Builder.of(EntityAirplaneZero::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("airplane_zero"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMountAfH>> MOUNT_AF_H = ENTITY_TYPES.register("mount_af_h",
            () -> EntityType.Builder.of(EntityMountAfH::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("mount_af_h"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMountBaH>> MOUNT_BA_H = ENTITY_TYPES.register("mount_ba_h",
            () -> EntityType.Builder.of(EntityMountBaH::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("mount_ba_h"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMountCaH>> MOUNT_CA_H = ENTITY_TYPES.register("mount_ca_h",
            () -> EntityType.Builder.of(EntityMountCaH::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("mount_ca_h"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMountCaWD>> MOUNT_CA_WD = ENTITY_TYPES.register("mount_ca_wd",
            () -> EntityType.Builder.of(EntityMountCaWD::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("mount_ca_wd"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMountHbH>> MOUNT_HB_H = ENTITY_TYPES.register("mount_hb_h",
            () -> EntityType.Builder.of(EntityMountHbH::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("mount_hb_h"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMountIsH>> MOUNT_IS_H = ENTITY_TYPES.register("mount_is_h",
            () -> EntityType.Builder.of(EntityMountIsH::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("mount_is_h"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMountMiH>> MOUNT_MI_H = ENTITY_TYPES.register("mount_mi_h",
            () -> EntityType.Builder.of(EntityMountMiH::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("mount_mi_h"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMountSuH>> MOUNT_SU_H = ENTITY_TYPES.register("mount_su_h",
            () -> EntityType.Builder.of(EntityMountSuH::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("mount_su_h"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityProjectileBeam>> PROJECTILE_BEAM = ENTITY_TYPES.register("projectile_beam",
            () -> EntityType.Builder.<EntityProjectileBeam>of(EntityProjectileBeam::new, MobCategory.MISC)
                    .sized(0.6f, 0.6f)
                    .clientTrackingRange(64)
                    .updateInterval(1)
                    .build("projectile_beam"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityRensouhou>> RENSOUHOU = ENTITY_TYPES.register("rensouhou",
            () -> EntityType.Builder.of(EntityRensouhou::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("rensouhou"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityRensouhouS>> RENSOUHOU_S = ENTITY_TYPES.register("rensouhou_s",
            () -> EntityType.Builder.of(EntityRensouhouS::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("rensouhou_s"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityShipGrudge>> SHIP_GRUDGE = ENTITY_TYPES.register("ship_grudge",
            () -> EntityType.Builder.<EntityShipGrudge>of(EntityShipGrudge::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(32)
                    .updateInterval(10)
                    .build("ship_grudge"));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityTakoyaki>> TAKOYAKI = ENTITY_TYPES.register("takoyaki",
            () -> EntityType.Builder.of(EntityTakoyaki::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("takoyaki"));

}
