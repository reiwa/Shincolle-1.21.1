package org.trp.shincolle.client.model;

public final class LegacyPoseOffsets {

    private LegacyPoseOffsets() {
    }

    public static float deadY(String modelName) {
        return switch (modelName) {
            case "ModelAirfieldHime" -> 0.55F * 3;
            case "ModelDestroyerAkatsuki" -> 1.9F;
            case "ModelDestroyerHibiki" -> 1.9F;
            case "ModelDestroyerIkazuchi" -> 1.9F;
            case "ModelDestroyerShimakaze" -> 1.9F;
            case "ModelDestroyerInazuma" -> 1.9F;
            case "ModelDestroyerHime" -> 0.59F * 3.2F;
            case "ModelCruiserAtago" -> 1.9F;
            case "ModelCruiserTakao" -> 1.5F;
            case "ModelCruiserTenryuu" -> 0.53F * 3.1F;
            case "ModelCruiserTatsuta" -> 0.51F * 3.1F;
            case "ModelCarrierHime" -> 0.49F * 3;
            case "ModelCarrierWDemon" -> 0.48F * 3;
            case "ModelCarrierAkagi" -> 0.53F * 3.2F;
            case "ModelCarrierKaga" -> 0.48F * 3;
            case "ModelCarrierWo" -> 0.41F * 3;
            case "ModelBattleshipHime" -> 1.05F * 1.5F;
            case "ModelBattleshipYamato" -> 0.58F * 3;
            case "ModelBattleshipRu" -> 0.65F * 3;
            case "ModelBattleshipRe" -> 1.13F * 1.5F;
            case "ModelBattleshipTa" -> 0.62F * 3;
            case "ModelBattleshipNagato" -> 0.65F * 3;
            case "ModelBBKongou" -> 0.7F * 3;
            case "ModelBBKirishima" -> 0.7F * 3;
            case "ModelBBHaruna" -> 0.72F * 3;
            case "ModelBBHiei" -> 0.6F * 3;
            case "ModelSubmHime" -> 0.62F * 3;
            case "ModelSubmRo500" -> 0.55F * 3.2F;
            case "ModelSubmU511" -> 0.41F * 3.2F;
            case "ModelSubmSo" -> 0.0F * 3;
            case "ModelSubmKa" -> 0.0F * 3;
            case "ModelSubmYo" -> 0.39F * 3;
            case "ModelSSNH" -> 0.27F * 3;
            case "ModelTransportWa" -> 0.12F * 3;
            case "ModelCAHime" -> 0.2F * 5;
            case "ModelIsolatedHime" -> 0.43F * 3.5F;
            case "ModelNorthernHime" -> 1.1F;
            case "ModelMidwayHime" -> 0.59F * 3;
            default -> 0.0F;
        };
    }

    public static float sneakY(String modelName) {
        return switch (modelName) {
            case "ModelAirfieldHime" -> 0.07F;
            case "ModelDestroyerAkatsuki" -> 0.05F;
            case "ModelDestroyerHibiki" -> 0.05F;
            case "ModelDestroyerIkazuchi" -> 0.05F;
            case "ModelDestroyerShimakaze" -> 0.05F;
            case "ModelDestroyerInazuma" -> 0.05F;
            case "ModelDestroyerHime" -> 0.07F;
            case "ModelCruiserAtago" -> 0.05F;
            case "ModelCruiserTakao" -> 0.06F;
            case "ModelCruiserTenryuu" -> 0.06F;
            case "ModelCruiserTatsuta" -> 0.06F;
            case "ModelCarrierHime" -> 0.05F;
            case "ModelCarrierWDemon" -> 0.05F;
            case "ModelCarrierAkagi" -> 0.1F;
            case "ModelCarrierKaga" -> 0.1F;
            case "ModelCarrierWo" -> 0.05F;
            case "ModelBattleshipHime" -> 0.58F;
            case "ModelBattleshipYamato" -> 0.07F;
            case "ModelBattleshipRu" -> 0.05F;
            case "ModelBattleshipRe" -> 0.1F;
            case "ModelBattleshipTa" -> 0.05F;
            case "ModelBattleshipNagato" -> 0.06F;
            case "ModelBBKongou" -> 0.14F;
            case "ModelBBKirishima" -> 0.14F;
            case "ModelBBHaruna" -> 0.14F;
            case "ModelBBHiei" -> 0.14F;
            case "ModelSubmHime" -> 0.09F;
            case "ModelSubmRo500" -> 0.1F;
            case "ModelSubmU511" -> 0.1F;
            case "ModelSubmSo" -> 0.05F;
            case "ModelSubmKa" -> 0.05F;
            case "ModelSubmYo" -> 0.05F;
            case "ModelSSNH" -> 0.01F;
            case "ModelTransportWa" -> 0.05F;
            case "ModelIsolatedHime" -> 0.06F;
            case "ModelNorthernHime" -> 0.02F;
            case "ModelMidwayHime" -> 0.09F;
            default -> 0.0F;
        };
    }

    public static float sittingY(String modelName) {
        return switch (modelName) {
            case "ModelAirfieldHime" -> 0.37F * 4;
            case "ModelDestroyerIkazuchi" -> 1.3F;
            case "ModelDestroyerHime" -> 0.43F * 3.2F;
            case "ModelCruiserTakao" -> 0.35F * 3.1F;
            case "ModelCruiserTenryuu" -> 0.46F * 3.2F;
            case "ModelCruiserTatsuta" -> 0.47F * 3.2F;
            case "ModelCarrierAkagi" -> 0.36F * 3.2F;
            case "ModelCarrierKaga" -> 0.36F * 3.2F;
            case "ModelBattleshipHime" -> 0.83F / 2;
            case "ModelBattleshipYamato" -> 0.54F * 3;
            case "ModelBattleshipRu" -> 0.54F * 2;
            case "ModelBattleshipRe" -> 0.51F * 3;
            case "ModelBattleshipTa" -> 0.51F * 3;
            case "ModelBattleshipNagato" -> 0.55F * 2.2F;
            case "ModelBBKongou" -> 0.31F * 3;
            case "ModelBBKirishima" -> 0.31F * 3;
            case "ModelBBHaruna" -> 0.55F * 3;
            case "ModelBBHiei" -> 0.55F * 3;
            case "ModelSubmHime" -> 0.495F * 3.2F;
            case "ModelSubmRo500" -> 0.41F * 3.2F;
            case "ModelSubmU511" -> 0.4F * 3.2F;
            case "ModelSubmSo" -> 0.45F * 3;
            case "ModelSubmKa" -> 0.45F * 3;
            case "ModelSSNH" -> 0.26F * 3.2F;
            case "ModelTransportWa" -> 0.42F * 3.2F;
            case "ModelCAHime" -> 0.21F * 4.1F;
            case "ModelIsolatedHime" -> 0.48F * 3.5F;
            case "ModelNorthernHime" -> 1.1F;
            case "ModelMidwayHime" -> 0.51F * 3;
            default -> 0.0F;
        };
    }

    public static float sittingAltY(String modelName) {
        return switch (modelName) {
            case "ModelCruiserTenryuu" -> 0.41F;
            case "ModelBBHiei" -> 0.69F * 2.7F;
            default -> 0.0F;
        };
    }

    public static float ridingY(String modelName) {
        return switch (modelName) {
            case "ModelAirfieldHime" -> 0.22F;
            case "ModelDestroyerIkazuchi" -> -0.375F;
            case "ModelBattleshipHime" -> 1.01F;
            case "ModelSubmHime" -> 0.22F;
            case "ModelBBHiei" -> 0.53F * 3;
            case "ModelIsolatedHime" -> 0.02F;
            case "ModelNorthernHime" -> 0.24F;
            case "ModelMidwayHime" -> 0.08F;
            default -> 0.0F;
        };
    }

    public static float sprintY(String modelName) {
        return switch (modelName) {
            case "ModelSubmSo" -> 0.05F;
            case "ModelSubmKa" -> 0.06F;
            case "ModelSubmYo" -> 0.1F;
            default -> 0.0F;
        };
    }

    public static float deadZ(String modelName) {
        return switch (modelName) {
            case "ModelSubmYo" -> -0.1F;
            default -> 0.0F;
        };
    }

    public static float ridingZ(String modelName) {
        return switch (modelName) {
            case "ModelBattleshipHime" -> -0.05F;
            case "ModelNorthernHime" -> 0.27F;
            default -> 0.0F;
        };
    }

    public static float baseY(String modelName) {
        return switch (modelName) {
            case "ModelBattleshipHime" -> 0.5F;
            default -> 0.0F;
        };
    }
}
