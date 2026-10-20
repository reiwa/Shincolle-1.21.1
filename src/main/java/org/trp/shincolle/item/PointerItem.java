package org.trp.shincolle.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.menu.FormationMenu;
import org.trp.shincolle.network.C2SPetShipPayload;
import org.trp.shincolle.network.C2SPointerActionPayload;
import org.trp.shincolle.network.ModNetwork;
import org.trp.shincolle.utility.CalcHelper;

import java.util.List;
import java.util.Optional;

public class PointerItem extends Item {

    private static final String TAG_VARIANT = "LegacyVariant";

    public static final int MODE_SINGLE = 0;
    public static final int MODE_GROUP = 1;
    public static final int MODE_FORMATION = 2;

    private static final int MODE_COUNT = 3;

    public PointerItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    public int getMode(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return MODE_SINGLE;
        }

        int raw = customData.copyTag().getInt(TAG_VARIANT);
        return Mth.clamp(raw, MODE_SINGLE, MODE_COUNT - 1);
    }

    public int getModelVariant(ItemStack stack) {
        int mode = getMode(stack);
        if (isPetting(stack)) {
            return mode + 3;
        }
        return mode;
    }

    public int cycleMode(ItemStack stack) {
        int next = (getMode(stack) + 1) % MODE_COUNT;
        setMode(stack, next);
        return next;
    }

    public void setMode(ItemStack stack, int mode) {
        int clamped = Mth.clamp(mode, MODE_SINGLE, MODE_COUNT - 1);
        if (clamped == MODE_SINGLE) {
            stack.remove(DataComponents.CUSTOM_DATA);
            return;
        }

        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data ->
            data.update(tag -> tag.putInt(TAG_VARIANT, clamped))
        );
    }

    public static void updateServerSideMode(
        Player player,
        ItemStack stack,
        int nextMode
    ) {
        if (player.level().isClientSide) return;

        if (nextMode == MODE_SINGLE) {
            double radius = 100.0;
            org.trp.shincolle.attachment.AdmiralData data = player.getData(
                org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA
            );
            int teamId = data.getCurrentTeamID();
            org.trp.shincolle.entity.base.EntityShipBase firstShip = null;

            if (player.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                for (int slot = 0; slot < org.trp.shincolle.attachment.AdmiralData.SLOT_COUNT; slot++) {
                    java.util.UUID shipUUID = data.getShipUUID(teamId, slot);
                    if (shipUUID != null) {
                        net.minecraft.world.entity.Entity e = serverLevel.getEntity(shipUUID);
                        if (e instanceof org.trp.shincolle.entity.base.EntityShipBase ship && ship.isOwnedBy(player) && !ship.isInDeadPose()) {
                            firstShip = ship;
                            break;
                        }
                    }
                }
            }

            if (firstShip != null) {
                clearOwnedPointerSelection(player, firstShip, radius);
                firstShip.setPointerSelected(true);
            } else {
                net.minecraft.world.phys.AABB searchArea = player
                    .getBoundingBox()
                    .inflate(radius);
                List<org.trp.shincolle.entity.base.EntityShipBase> ships = player
                    .level()
                    .getEntitiesOfClass(
                        org.trp.shincolle.entity.base.EntityShipBase.class,
                        searchArea,
                        ship ->
                            ship.isOwnedBy(player) &&
                            ship.isPointerSelected() &&
                            !ship.isInDeadPose()
                    );
                if (ships.size() > 1) {
                    org.trp.shincolle.entity.base.EntityShipBase keep = ships.get(0);
                    clearOwnedPointerSelection(player, keep, radius);
                    keep.setPointerSelected(true);
                }
            }
        } else if (nextMode == MODE_FORMATION) {
            org.trp.shincolle.attachment.AdmiralData data = player.getData(
                org.trp.shincolle.init.ModDataAttachments.ADMIRAL_DATA
            );
            int teamId = data.getCurrentTeamID();
            double radius = 100.0;
            net.minecraft.world.phys.AABB searchArea = player
                .getBoundingBox()
                .inflate(radius);
            List<org.trp.shincolle.entity.base.EntityShipBase> ships = player
                .level()
                .getEntitiesOfClass(
                    org.trp.shincolle.entity.base.EntityShipBase.class,
                    searchArea,
                    ship -> ship.isOwnedBy(player) && !ship.isInDeadPose()
                );
            for (org.trp.shincolle.entity.base.EntityShipBase ship : ships) {
                ship.setPointerSelected(ship.getFormationTeam() == teamId);
            }
        }
        player.displayClientMessage(
            Component.translatable(getModeTranslationKey(nextMode)),
            true
        );
    }

    public static void clearOwnedPointerSelection(
        Player player,
        org.trp.shincolle.entity.base.EntityShipBase keepSelected,
        double radius
    ) {
        net.minecraft.world.phys.AABB searchArea = player
            .getBoundingBox()
            .inflate(radius);
        List<org.trp.shincolle.entity.base.EntityShipBase> ships = player
            .level()
            .getEntitiesOfClass(
                org.trp.shincolle.entity.base.EntityShipBase.class,
                searchArea,
                ship ->
                    ship.isOwnedBy(player) &&
                    ship.isPointerSelected() &&
                    !ship.isInDeadPose()
            );
        for (org.trp.shincolle.entity.base.EntityShipBase ship : ships) {
            if (ship == keepSelected) continue;
            ship.setPointerSelected(false);
            ship.clearPointerTarget();
            ship.clearPointerTargetEntity();
        }
    }

    public ItemStack createVariantStack(int mode) {
        ItemStack stack = new ItemStack(this);
        setMode(stack, mode);
        return stack;
    }

    public void addAllVariantsToCreativeTab(CreativeModeTab.Output output) {
        for (int mode = MODE_SINGLE; mode < MODE_COUNT; mode++) {
            output.accept(createVariantStack(mode));
        }
    }

    public static String getModeTranslationKey(int mode) {
        return switch (mode) {
            case MODE_GROUP -> "gui.shincolle.pointer1";
            case MODE_FORMATION -> "gui.shincolle.pointer2";
            default -> "gui.shincolle.pointer0";
        };
    }

    public boolean isPetting(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return false;
        }
        return customData.copyTag().getBoolean("IsPetting");
    }

    public void setPetting(ItemStack stack, boolean petting) {
        if (!petting) {
            stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data ->
                data.update(tag -> tag.remove("IsPetting"))
            );
            return;
        }
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data ->
            data.update(tag -> tag.putBoolean("IsPetting", true))
        );
    }

    @Override
    public void appendHoverText(
        ItemStack stack,
        Item.TooltipContext context,
        List<Component> tooltipComponents,
        TooltipFlag tooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        int mode = getMode(stack);

        if (net.neoforged.fml.loading.FMLEnvironment.dist == net.neoforged.api.distmarker.Dist.CLIENT) {
            org.trp.shincolle.client.ClientProxy.appendPointerTooltip(stack, tooltipComponents, mode);
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
        Level level,
        Player player,
        InteractionHand hand
    ) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            double range = 64.0D;
            Vec3 eyePos = player.getEyePosition();
            Vec3 look = player.getViewVector(1.0F);
            Vec3 end = eyePos.add(look.scale(range));

            BlockHitResult blockHit = level.clip(new ClipContext(eyePos, end, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, player));
            double maxDist = range;
            if (blockHit.getType() != HitResult.Type.MISS) {
                maxDist = eyePos.distanceTo(blockHit.getLocation());
            }

            AABB searchBox = player.getBoundingBox().expandTowards(look.scale(range)).inflate(1.0D);
            EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                level,
                player,
                eyePos,
                eyePos.add(look.scale(maxDist)),
                searchBox,
                entity -> !entity.isSpectator() && entity.isPickable() && !entity.equals(player.getVehicle())
            );

            if (entityHit != null && entityHit.getEntity() instanceof LivingEntity livingTarget) {
                if (isPetting(stack)) {
                    if (livingTarget instanceof EntityShipBase ship) {
                        int hitHeight = CalcHelper.getEntityHitHeight(player, ship);
                        int hitAngle = CalcHelper.getEntityHitSide(player, ship);
                        ship.setHitHeight(hitHeight);
                        ship.setHitAngle(hitAngle);
                        ship.checkCaressed();
                        ModNetwork.sendToServer(
                            new C2SPetShipPayload(
                                ship.getUUID(),
                                hitHeight,
                                hitAngle
                            )
                        );
                        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
                    }
                }

                int mode = getMode(stack);
                if (mode == MODE_FORMATION) {
                    if (livingTarget instanceof EntityShipBase ship && ship.isOwnedBy(player)) {
                        if (player.isShiftKeyDown()) {
                            ModNetwork.sendToServer(
                                new C2SPointerActionPayload(
                                    3,
                                    Optional.of(ship.getUUID()),
                                    Optional.empty()
                                )
                            );
                        } else {
                            ModNetwork.sendToServer(
                                new C2SPointerActionPayload(
                                    5,
                                    Optional.of(ship.getUUID()),
                                    Optional.empty()
                                )
                            );
                        }
                    } else {
                        ModNetwork.sendToServer(
                            new C2SPointerActionPayload(
                                1,
                                Optional.of(livingTarget.getUUID()),
                                Optional.empty()
                            )
                        );
                    }
                    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
                }
            } else if (player.isShiftKeyDown()) {
                int mode = getMode(stack);
                if (mode == MODE_FORMATION) {
                    ModNetwork.sendToServer(
                        new C2SPointerActionPayload(
                            4,
                            Optional.empty(),
                            Optional.empty()
                        )
                    );
                    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
                }
            } else if (blockHit.getType() != HitResult.Type.MISS) {
                int mode = getMode(stack);
                if (mode == MODE_FORMATION) {
                    BlockPos blockPos = blockHit.getBlockPos();
                    Vec3 pos = Vec3.atBottomCenterOf(blockPos).add(0, 1.0, 0);
                    ModNetwork.sendToServer(
                        new C2SPointerActionPayload(
                            2,
                            Optional.empty(),
                            Optional.of(pos)
                        )
                    );
                    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
                }
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public net.minecraft.world.InteractionResult useOn(
        net.minecraft.world.item.context.UseOnContext context
    ) {
        Player player = context.getPlayer();
        if (player == null) {
            return net.minecraft.world.InteractionResult.PASS;
        }

        Level level = context.getLevel();
        int mode = getMode(context.getItemInHand());

        if (player.isShiftKeyDown()) {
            if (mode == MODE_FORMATION && level.isClientSide) {
                ModNetwork.sendToServer(
                    new C2SPointerActionPayload(
                        4,
                        Optional.empty(),
                        Optional.empty()
                    )
                );
            }
            return net.minecraft.world.InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (level.isClientSide && mode == MODE_FORMATION) {
            BlockPos blockPos = context.getClickedPos();
            Vec3 pos = Vec3.atBottomCenterOf(blockPos).add(0, 1.0, 0);
            ModNetwork.sendToServer(
                new C2SPointerActionPayload(
                    2,
                    Optional.empty(),
                    Optional.of(pos)
                )
            );
            return net.minecraft.world.InteractionResult.sidedSuccess(level.isClientSide);
        }
        return net.minecraft.world.InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public net.minecraft.world.InteractionResult interactLivingEntity(
        ItemStack stack,
        Player player,
        LivingEntity target,
        InteractionHand hand
    ) {
        if (isPetting(stack)) {
            if (target instanceof EntityShipBase ship) {
                if (player.level().isClientSide) {
                    int hitHeight = CalcHelper.getEntityHitHeight(player, ship);
                    int hitAngle = CalcHelper.getEntityHitSide(player, ship);
                    ship.setHitHeight(hitHeight);
                    ship.setHitAngle(hitAngle);
                    ship.checkCaressed();
                    ModNetwork.sendToServer(
                        new C2SPetShipPayload(
                            ship.getUUID(),
                            hitHeight,
                            hitAngle
                        )
                    );
                }
                return net.minecraft.world.InteractionResult.sidedSuccess(player.level().isClientSide);
            }
        }

        if (player.level().isClientSide) {
            int mode = getMode(stack);
            if (mode == MODE_FORMATION) {
                if (target instanceof EntityShipBase ship && ship.isOwnedBy(player)) {
                    if (player.isShiftKeyDown()) {
                        ModNetwork.sendToServer(
                            new C2SPointerActionPayload(
                                3,
                                Optional.of(ship.getUUID()),
                                Optional.empty()
                            )
                        );
                    } else {
                        ModNetwork.sendToServer(
                            new C2SPointerActionPayload(
                                5,
                                Optional.of(ship.getUUID()),
                                Optional.empty()
                            )
                        );
                    }
                    return net.minecraft.world.InteractionResult.sidedSuccess(player.level().isClientSide);
                } else {
                    ModNetwork.sendToServer(
                        new C2SPointerActionPayload(
                            1,
                            Optional.of(target.getUUID()),
                            Optional.empty()
                        )
                    );
                    return net.minecraft.world.InteractionResult.sidedSuccess(player.level().isClientSide);
                }
            }
        }
        return net.minecraft.world.InteractionResult.sidedSuccess(player.level().isClientSide);
    }

    public void onSwingMiss(Player player, ItemStack stack) {
        if (player.level().isClientSide) {
            org.trp.shincolle.network.ModNetwork.sendToServer(
                new C2SPointerActionPayload(
                    0,
                    Optional.empty(),
                    Optional.empty()
                )
            );
        }
    }
}
