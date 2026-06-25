package org.trp.shincolle.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.UuidArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.trp.shincolle.crafting.ShipyardRecipes;
import org.trp.shincolle.entity.EntityShipGrudge;
import org.trp.shincolle.entity.base.EntityMountBase;
import org.trp.shincolle.entity.base.EntityShipBase;
import org.trp.shincolle.init.ModParticles;

import java.util.*;

public class ModCommands {

    private static final Map<String, Integer> EmoNameToID = new HashMap<>();
    static {
        EmoNameToID.put("0", 0); EmoNameToID.put("swt", 0); EmoNameToID.put("drop", 0);
        EmoNameToID.put("1", 1); EmoNameToID.put("lv", 1); EmoNameToID.put("love", 1); EmoNameToID.put("heart", 1);
        EmoNameToID.put("2", 2); EmoNameToID.put("swt2", 2); EmoNameToID.put("wah", 2); EmoNameToID.put("panic", 2);
        EmoNameToID.put("3", 3); EmoNameToID.put("?", 3);
        EmoNameToID.put("4", 4); EmoNameToID.put("!", 4);
        EmoNameToID.put("5", 5); EmoNameToID.put("...", 5);
        EmoNameToID.put("6", 6); EmoNameToID.put("an", 6); EmoNameToID.put("anger", 6); EmoNameToID.put("angry", 6);
        EmoNameToID.put("7", 7); EmoNameToID.put("note", 7); EmoNameToID.put("ho", 7);
        EmoNameToID.put("8", 8); EmoNameToID.put("sob", 8); EmoNameToID.put("cry", 8); EmoNameToID.put("sad", 8);
        EmoNameToID.put("9", 9); EmoNameToID.put("spit", 9); EmoNameToID.put("rice", 9); EmoNameToID.put("hungry", 9);
        EmoNameToID.put("10", 10); EmoNameToID.put("spin", 10); EmoNameToID.put("dizzy", 10);
        EmoNameToID.put("11", 11); EmoNameToID.put("find", 11); EmoNameToID.put("??", 11);
        EmoNameToID.put("12", 12); EmoNameToID.put("omg", 12); EmoNameToID.put("shock", 12);
        EmoNameToID.put("13", 13); EmoNameToID.put("ok", 13); EmoNameToID.put("nod", 13);
        EmoNameToID.put("14", 14); EmoNameToID.put("fsh", 14); EmoNameToID.put("flash", 14); EmoNameToID.put("+_+", 14);
        EmoNameToID.put("15", 15); EmoNameToID.put("kiss", 15); EmoNameToID.put("kis", 15);
        EmoNameToID.put("16", 16); EmoNameToID.put("lol", 16); EmoNameToID.put("ha", 16); EmoNameToID.put("heh", 16);
        EmoNameToID.put("17", 17); EmoNameToID.put("gg", 17); EmoNameToID.put("giggle", 17);
        EmoNameToID.put("18", 18); EmoNameToID.put("sigh", 18);
        EmoNameToID.put("19", 19); EmoNameToID.put("meh", 19); EmoNameToID.put("lick", 19);
        EmoNameToID.put("20", 20); EmoNameToID.put("orz", 20); EmoNameToID.put("otl", 20);
        EmoNameToID.put("21", 21); EmoNameToID.put("o", 21); EmoNameToID.put("oh", 21); EmoNameToID.put("yes", 21);
        EmoNameToID.put("22", 22); EmoNameToID.put("x", 22); EmoNameToID.put("no", 22);
        EmoNameToID.put("23", 23); EmoNameToID.put("!?", 23); EmoNameToID.put("surprised", 23);
        EmoNameToID.put("24", 24); EmoNameToID.put("rock", 24); EmoNameToID.put("bawi", 24);
        EmoNameToID.put("25", 25); EmoNameToID.put("paper", 25); EmoNameToID.put("bo", 25);
        EmoNameToID.put("26", 26); EmoNameToID.put("scissors", 26); EmoNameToID.put("gawi", 26); EmoNameToID.put("ya", 26); EmoNameToID.put("yeah", 26);
        EmoNameToID.put("27", 27); EmoNameToID.put("-w-", 27);
        EmoNameToID.put("28", 28); EmoNameToID.put("-o-", 28);
        EmoNameToID.put("29", 29); EmoNameToID.put("blink", 29); EmoNameToID.put("wink", 29);
        EmoNameToID.put("30", 30); EmoNameToID.put("pif", 30);
        EmoNameToID.put("31", 31); EmoNameToID.put("shy", 31); EmoNameToID.put("shine", 31);
        EmoNameToID.put("32", 32); EmoNameToID.put("hmm", 32);
        EmoNameToID.put("33", 33); EmoNameToID.put(":p", 33);
        EmoNameToID.put("34", 34); EmoNameToID.put("lll", 34);
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(
            Commands.literal("ship")
                .then(Commands.literal("list")
                    .executes(ctx -> executeShipList(ctx, 1))
                    .then(Commands.argument("page", IntegerArgumentType.integer(1))
                        .executes(ctx -> executeShipList(ctx, IntegerArgumentType.getInteger(ctx, "page")))))
                .then(Commands.literal("get")
                    .requires(source -> source.hasPermission(2))
                    .then(Commands.argument("uuid", UuidArgument.uuid())
                        .executes(ctx -> executeShipGet(ctx, UuidArgument.getUuid(ctx, "uuid")))))
                .then(Commands.literal("del")
                    .requires(source -> source.hasPermission(2))
                    .then(Commands.argument("uuid", UuidArgument.uuid())
                        .executes(ctx -> executeShipDel(ctx, UuidArgument.getUuid(ctx, "uuid")))))
        );

        dispatcher.register(
            Commands.literal("shipchangeowner")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("player", EntityArgument.player())
                    .executes(ctx -> executeShipChangeOwner(ctx, EntityArgument.getPlayer(ctx, "player"))))
        );
        dispatcher.register(
            Commands.literal("shipch")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("player", EntityArgument.player())
                    .executes(ctx -> executeShipChangeOwner(ctx, EntityArgument.getPlayer(ctx, "player"))))
        );

        var emotesCmd = Commands.literal("shipemotes")
            .executes(ctx -> executeShipEmotes(ctx, null))
            .then(Commands.argument("emote", StringArgumentType.string())
                .executes(ctx -> executeShipEmotes(ctx, StringArgumentType.getString(ctx, "emote"))));
        dispatcher.register(emotesCmd);
        dispatcher.register(Commands.literal("em").executes(emotesCmd.getCommand()).then(Commands.argument("emote", StringArgumentType.string()).executes(emotesCmd.getCommand())));
        dispatcher.register(Commands.literal("emo").executes(emotesCmd.getCommand()).then(Commands.argument("emote", StringArgumentType.string()).executes(emotesCmd.getCommand())));
        dispatcher.register(Commands.literal("emote").executes(emotesCmd.getCommand()).then(Commands.argument("emote", StringArgumentType.string()).executes(emotesCmd.getCommand())));
        dispatcher.register(Commands.literal("emotes").executes(emotesCmd.getCommand()).then(Commands.argument("emote", StringArgumentType.string()).executes(emotesCmd.getCommand())));

        dispatcher.register(
            Commands.literal("shipkill")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("class_id", IntegerArgumentType.integer())
                    .executes(ctx -> executeShipKill(ctx, IntegerArgumentType.getInteger(ctx, "class_id"), 64))
                    .then(Commands.argument("range", IntegerArgumentType.integer(1))
                        .executes(ctx -> executeShipKill(ctx, IntegerArgumentType.getInteger(ctx, "class_id"), IntegerArgumentType.getInteger(ctx, "range")))))
        );

        dispatcher.register(
            Commands.literal("shipattrs")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("level", IntegerArgumentType.integer(1, 150))
                    .executes(ctx -> executeShipAttrs(ctx, IntegerArgumentType.getInteger(ctx, "level"), null))
                    .then(Commands.argument("hp", IntegerArgumentType.integer(0, 100))
                        .then(Commands.argument("atk", IntegerArgumentType.integer(0, 100))
                            .then(Commands.argument("def", IntegerArgumentType.integer(0, 100))
                                .then(Commands.argument("spd", IntegerArgumentType.integer(0, 100))
                                    .then(Commands.argument("mov", IntegerArgumentType.integer(0, 100))
                                        .then(Commands.argument("hit", IntegerArgumentType.integer(0, 100))
                                            .executes(ctx -> executeShipAttrs(ctx, IntegerArgumentType.getInteger(ctx, "level"),
                                                new int[] {
                                                    IntegerArgumentType.getInteger(ctx, "hp"),
                                                    IntegerArgumentType.getInteger(ctx, "atk"),
                                                    IntegerArgumentType.getInteger(ctx, "def"),
                                                    IntegerArgumentType.getInteger(ctx, "spd"),
                                                    IntegerArgumentType.getInteger(ctx, "mov"),
                                                    IntegerArgumentType.getInteger(ctx, "hit")
                                                })))))))))
        );

        dispatcher.register(
            Commands.literal("shipcleardrop")
                .requires(source -> source.hasPermission(2))
                .executes(ctx -> executeShipClearDrop(ctx, 128))
                .then(Commands.argument("range", IntegerArgumentType.integer(1))
                    .executes(ctx -> executeShipClearDrop(ctx, IntegerArgumentType.getInteger(ctx, "range"))))
        );

        dispatcher.register(
            Commands.literal("shipinfo")
                .executes(ModCommands::executeShipInfo)
        );

        dispatcher.register(
            Commands.literal("shipstopai")
                .requires(source -> source.hasPermission(2))
                .executes(ModCommands::executeShipStopAI)
        );
        dispatcher.register(
            Commands.literal("shipstop")
                .requires(source -> source.hasPermission(2))
                .executes(ModCommands::executeShipStopAI)
        );

        dispatcher.register(
            Commands.literal("shipupdateowneruid")
                .executes(ctx -> executeShipUpdateOwnerUID(ctx, null))
                .then(Commands.argument("player", EntityArgument.player())
                    .requires(source -> source.hasPermission(2))
                    .executes(ctx -> executeShipUpdateOwnerUID(ctx, EntityArgument.getPlayer(ctx, "player"))))
        );
    }

    private static EntityShipBase getLookedAtShip(ServerPlayer player, double maxDistance) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = eyePos.add(look.x * maxDistance, look.y * maxDistance, look.z * maxDistance);
        AABB searchBox = player.getBoundingBox().expandTowards(look.scale(maxDistance)).inflate(1.0D);
        EntityHitResult hit = ProjectileUtil.getEntityHitResult(
            player.level(), player, eyePos, end, searchBox,
            entity -> !entity.isSpectator() && entity.isPickable() && entity instanceof EntityShipBase
        );
        return (hit != null && hit.getEntity() instanceof EntityShipBase) ? (EntityShipBase) hit.getEntity() : null;
    }

    private static int executeShipList(CommandContext<CommandSourceStack> ctx, int page) {
        CommandSourceStack source = ctx.getSource();
        List<EntityShipBase> ships = new ArrayList<>();
        for (ServerLevel level : source.getServer().getAllLevels()) {
            for (Entity entity : level.getAllEntities()) {
                if (entity instanceof EntityShipBase ship) {
                    ships.add(ship);
                }
            }
        }

        int size = ships.size();
        int numPerPage = 10;
        int maxPage = (size - 1) / numPerPage + 1;
        if (page < 1 || page > maxPage) {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.wrongpararange").append(" Page: " + page));
            return 0;
        }

        MutableComponent header = Component.translatable("chat.shincolle.command.command")
            .append(Component.literal(" ship: page ( ").withStyle(ChatFormatting.DARK_GREEN))
            .append(Component.literal(String.valueOf(page)).withStyle(ChatFormatting.AQUA))
            .append(Component.literal(" / ").withStyle(ChatFormatting.DARK_GREEN))
            .append(Component.literal(String.valueOf(maxPage)).withStyle(ChatFormatting.AQUA))
            .append(Component.literal(" )").withStyle(ChatFormatting.DARK_GREEN));
        source.sendSystemMessage(header);

        int start = (page - 1) * numPerPage;
        int end = Math.min(size, start + numPerPage);
        for (int i = start; i < end; i++) {
            EntityShipBase ship = ships.get(i);
            String shipClassName = Component.translatable(ship.getType().getDescriptionId()).getString();
            String ownerName = ship.getOwner() != null ? ship.getOwner().getName().getString() : "None";
            String wid = ship.level().dimension().location().toString();
            String isDead = String.valueOf(!ship.isAlive());
            String isEntityPresent = "true";

            Component line1 = Component.literal("  UUID: ").append(Component.literal(ship.getUUID().toString()).withStyle(ChatFormatting.AQUA))
                .append("  WID: ").append(Component.literal(wid).withStyle(ChatFormatting.DARK_PURPLE))
                .append("  D/E: ").append(Component.literal(isDead).withStyle(ChatFormatting.RED))
                .append("/").append(Component.literal(isEntityPresent).withStyle(ChatFormatting.LIGHT_PURPLE))
                .append("  Cls: ").append(Component.literal(shipClassName).withStyle(ChatFormatting.YELLOW));

            Component line2 = Component.literal("       Pos( ").append(Component.literal((int) ship.getX() + " " + (int) ship.getY() + " " + (int) ship.getZ()).withStyle(ChatFormatting.GRAY))
                .append(" )  Lv: ").append(Component.literal(String.valueOf(ship.getLevel())).withStyle(ChatFormatting.GOLD))
                .append("  Owner: ").append(Component.literal(ownerName).withStyle(ChatFormatting.GREEN));

            source.sendSystemMessage(line1);
            source.sendSystemMessage(line2);
        }
        return size;
    }

    private static int executeShipGet(CommandContext<CommandSourceStack> ctx, UUID uuid) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        ServerPlayer player = source.getPlayerOrException();

        EntityShipBase foundShip = null;
        for (ServerLevel level : source.getServer().getAllLevels()) {
            Entity entity = level.getEntity(uuid);
            if (entity instanceof EntityShipBase ship) {
                foundShip = ship;
                break;
            }
        }

        if (foundShip == null) {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.shipnull").append(" " + uuid));
            return 0;
        }

        if (foundShip.level() != player.level()) {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.worldnull"));
            return 0;
        }

        double dx = player.getX() - foundShip.getX();
        double dy = player.getY() - foundShip.getY();
        double dz = player.getZ() - foundShip.getZ();
        double distSq = dx * dx + dy * dy + dz * dz;

        if (distSq >= 4096.0) {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.shiptoofar"));
            return 0;
        }

        foundShip.stopRiding();
        foundShip.setTarget(null);
        foundShip.setDeltaMovement(Vec3.ZERO);
        foundShip.teleportTo(player.getX(), player.getY() + 0.5, player.getZ());
        foundShip.setOrderedToSit(false);
        foundShip.setGuardedPos((int) player.getX(), (int) (player.getY() + 0.5), (int) player.getZ(), 0, 1);
        foundShip.getStateComponent().setStateDisableGuardPos(false);

        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(Component.literal(" ship: ").append(Component.literal("get: " + uuid).withStyle(ChatFormatting.YELLOW))));
        return 1;
    }

    private static int executeShipDel(CommandContext<CommandSourceStack> ctx, UUID uuid) {
        CommandSourceStack source = ctx.getSource();

        EntityShipBase foundShip = null;
        for (ServerLevel level : source.getServer().getAllLevels()) {
            Entity entity = level.getEntity(uuid);
            if (entity instanceof EntityShipBase ship) {
                foundShip = ship;
                break;
            }
        }

        if (foundShip == null) {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.shipnull").append(" " + uuid));
            return 0;
        }

        foundShip.discard();
        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(Component.literal(" ship: ").append(Component.literal("delete: " + uuid).withStyle(ChatFormatting.RED))));
        return 1;
    }

    private static int executeShipChangeOwner(CommandContext<CommandSourceStack> ctx, ServerPlayer owner) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        ServerPlayer player = source.getPlayerOrException();

        EntityShipBase ship = getLookedAtShip(player, 32.0);
        if (ship == null) {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.notship"));
            return 0;
        }

        ship.setOwnerUUID(owner.getUUID());
        ship.setTame(true, false);

        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(" shipchangeowner: ship: ").append(Component.literal(ship.toString()).withStyle(ChatFormatting.AQUA)));
        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(" shipchangeowner: owner: ").append(Component.literal(owner.getName().getString()).withStyle(ChatFormatting.AQUA))
            .append(" ").append(Component.literal(owner.getUUID().toString()).withStyle(ChatFormatting.LIGHT_PURPLE)));

        return 1;
    }

    private static int executeShipEmotes(CommandContext<CommandSourceStack> ctx, String emoteName) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        int emo = emoteName != null ? EmoNameToID.getOrDefault(emoteName, 0) : source.getLevel().getRandom().nextInt(30);

        Entity sender = source.getEntity();
        double px, py, pz;
        float height;
        int entityType;
        int hostEntityId = -1;

        if (sender instanceof ServerPlayer host) {
            px = host.getX();
            py = host.getY();
            pz = host.getZ();
            height = host.getBbHeight() * 0.65F;
            entityType = 1;
            hostEntityId = host.getId();
        } else if (sender != null) {
            px = sender.getX();
            py = sender.getY();
            pz = sender.getZ();
            height = sender.getBbHeight() * 0.25F;
            entityType = 1;
            hostEntityId = sender.getId();
        } else {
            Vec3 pos = source.getPosition();
            px = pos.x;
            py = pos.y;
            pz = pos.z;
            height = 0.5F;
            entityType = 0;
        }

        source.getLevel().sendParticles(
            ModParticles.PARTICLE_EMOTION.get(),
            px, py, pz,
            0,
            height, hostEntityId, emo,
            1.0D
        );
        return 1;
    }

    private static int executeShipKill(CommandContext<CommandSourceStack> ctx, int id, int range) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        ServerPlayer player = source.getPlayerOrException();

        int classId = id - 2;
        if (classId < 0) {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.wrongcid").append(" " + id));
            return 0;
        }

        int actualClassId = classId < 2000 ? classId : classId - 2000;
        net.minecraft.world.entity.EntityType<?> type = ShipyardRecipes.rollShipEntityType(classId >= 2000, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.AIR));

        String shipName = getShipNameForClassId(actualClassId);
        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(" shipkill: CID: " + id + " " + shipName));

        AABB aabb = new AABB(player.getX() - range, player.getY() - 256.0, player.getZ() - range, player.getX() + range, player.getY() + 512.0, player.getZ() + range);
        List<EntityShipBase> ships = player.serverLevel().getEntitiesOfClass(EntityShipBase.class, aabb);
        int killed = 0;
        for (EntityShipBase ship : ships) {
            int shipClass = ShipyardRecipes.getShipClassFromEntityType(ship.getType());
            if (classId < 2000) {
                if (!ship.isHostileShipMob() && shipClass == classId) {
                    ship.discard();
                    source.sendSystemMessage(Component.literal("remove " + ship.toString()));
                    killed++;
                }
            } else {
                int hostileClassId = classId - 2000;
                if (ship.isHostileShipMob() && shipClass == hostileClassId) {
                    ship.discard();
                    source.sendSystemMessage(Component.literal("remove " + ship.toString()));
                    killed++;
                }
            }
        }
        return killed;
    }

    private static String getShipNameForClassId(int type) {
        return switch (type) {
            case 0 -> "Destroyer I-Class";
            case 1 -> "Destroyer Ro-Class";
            case 2 -> "Destroyer Ha-Class";
            case 3 -> "Destroyer Ni-Class";
            case 9 -> "Heavy Cruiser Ri-Class";
            case 10 -> "Heavy Cruiser Ne-Class";
            case 12 -> "Aircraft Carrier Wo-Class";
            case 13 -> "Battleship Ru-Class";
            case 14 -> "Battleship Ta-Class";
            case 15 -> "Battleship Re-Class";
            case 16 -> "Transport Ship Wa-Class";
            case 17 -> "Submarine Ka-Class";
            case 18 -> "Submarine Yo-Class";
            case 19 -> "Submarine So-Class";
            case 20 -> "Aircraft Carrier Princess";
            case 21 -> "Airfield Princess";
            case 26 -> "Battleship Princess";
            case 27 -> "Destroyer Princess";
            case 28 -> "Harbour Princess";
            case 29 -> "Isolated Princess";
            case 30 -> "Midway Princess";
            case 31 -> "Northern Princess";
            case 33 -> "Aircraft Carrier Demon";
            case 44 -> "Submarine Princess";
            case 49 -> "Heavy Cruiser Princess";
            case 72 -> "Submarine New Princess";
            case 36 -> "Destroyer Shimakaze";
            case 60 -> "Battleship Kongou";
            case 61 -> "Battleship Hiei";
            case 62 -> "Battleship Haruna";
            case 63 -> "Battleship Kirishima";
            case 37 -> "Battleship Nagato";
            case 46 -> "Battleship Yamato";
            case 38 -> "Submarine U-511";
            case 39 -> "Submarine Ro-500";
            case 47 -> "Standard Carrier Kaga";
            case 48 -> "Standard Carrier Akagi";
            case 51 -> "Destroyer Akatsuki";
            case 52 -> "Destroyer Hibiki";
            case 53 -> "Destroyer Ikazuchi";
            case 54 -> "Destroyer Inazuma";
            case 56 -> "Light Cruiser Tenryuu";
            case 57 -> "Light Cruiser Tatsuta";
            case 58 -> "Heavy Cruiser Atago";
            case 59 -> "Heavy Cruiser Takao";
            default -> "Unknown Ship";
        };
    }

    private static int executeShipAttrs(CommandContext<CommandSourceStack> ctx, int level, int[] bonuses) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        ServerPlayer player = source.getPlayerOrException();

        EntityShipBase ship = getLookedAtShip(player, 16.0);
        if (ship == null) {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.notship"));
            return 0;
        }

        ship.setLevel(level);
        if (bonuses != null) {
            for (int i = 0; i < 6; i++) {
                ship.setAttrBonus(i, bonuses[i]);
            }
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
                .append(" shipattrs: LV: ").append(Component.literal(String.valueOf(level)).withStyle(ChatFormatting.LIGHT_PURPLE))
                .append(" BonusValue: ").append(Component.literal(bonuses[0] + " " + bonuses[1] + " " + bonuses[2] + " " + bonuses[3] + " " + bonuses[4] + " " + bonuses[5]).withStyle(ChatFormatting.RED)));
        } else {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
                .append(" shipattrs: LV: ").append(Component.literal(String.valueOf(level)).withStyle(ChatFormatting.LIGHT_PURPLE)));
        }
        source.sendSystemMessage(Component.literal(ship.toString()).withStyle(ChatFormatting.AQUA));
        return 1;
    }

    private static int executeShipClearDrop(CommandContext<CommandSourceStack> ctx, int range) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        ServerPlayer player = source.getPlayerOrException();

        AABB aabb = new AABB(player.getX() - range, player.getY() - 128.0, player.getZ() - range, player.getX() + range, player.getY() + 256.0, player.getZ() + range);
        List<EntityShipGrudge> hitent = player.serverLevel().getEntitiesOfClass(EntityShipGrudge.class, aabb);

        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(" shipcleardrop: remove " + hitent.size() + " item entities."));

        for (EntityShipGrudge i : hitent) {
            i.discard();
        }
        return hitent.size();
    }

    private static int executeShipInfo(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        ServerPlayer player = source.getPlayerOrException();

        EntityShipBase ship = getLookedAtShip(player, 32.0);
        if (ship == null) {
            source.sendSystemMessage(Component.translatable("chat.shincolle.command.notship"));
            return 0;
        }

        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(" user: ").append(Component.literal(player.getName().getString()).withStyle(ChatFormatting.LIGHT_PURPLE))
            .append(" UUID: ").append(Component.literal(player.getUUID().toString()).withStyle(ChatFormatting.GOLD)));

        source.sendSystemMessage(Component.literal("CustomName: " + ship.getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
        source.sendSystemMessage(Component.literal("EntityID: " + ship.getId()).withStyle(ChatFormatting.GOLD));
        source.sendSystemMessage(Component.literal("UUID: " + ship.getUUID().toString()).withStyle(ChatFormatting.GREEN));
        source.sendSystemMessage(Component.literal("Owner UUID: " + (ship.getOwnerUUID() != null ? ship.getOwnerUUID().toString() : "None")).withStyle(ChatFormatting.YELLOW));
        source.sendSystemMessage(Component.literal("Morale: " + ship.getMorale()).withStyle(ChatFormatting.YELLOW));

        return 1;
    }

    private static int executeShipStopAI(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();
        EntityShipBase.setStopAI(!EntityShipBase.isStopAI());
        EntityMountBase.stopAI = EntityShipBase.isStopAI();

        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(" shipstopai: " + EntityShipBase.isStopAI()));
        return 1;
    }

    private static int executeShipUpdateOwnerUID(CommandContext<CommandSourceStack> ctx, ServerPlayer targetPlayer) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        ServerPlayer player = targetPlayer != null ? targetPlayer : source.getPlayerOrException();

        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(" shipupdateowneruid: get player: ").append(Component.literal(player.getName().getString()).withStyle(ChatFormatting.AQUA)));
        source.sendSystemMessage(Component.translatable("chat.shincolle.command.command")
            .append(" shipupdateowneruid: owner: ").append(Component.literal(player.getUUID().toString()).withStyle(ChatFormatting.LIGHT_PURPLE)));

        int updated = 0;
        for (ServerLevel level : source.getServer().getAllLevels()) {
            for (Entity entity : level.getAllEntities()) {
                if (entity instanceof EntityShipBase ship && player.getUUID().equals(ship.getOwnerUUID())) {
                    ship.setOwnerUUID(player.getUUID());
                    source.sendSystemMessage(Component.literal("get ship: ").append(Component.literal(ship.toString()).withStyle(ChatFormatting.GOLD)));
                    updated++;
                }
            }
        }
        return updated;
    }
}
