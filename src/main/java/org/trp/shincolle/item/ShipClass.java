package org.trp.shincolle.item;

public enum ShipClass {
    DESTROYER("DD", "shipspawnegg0"),
    LIGHT_CRUISER("CL", "shipspawnegg1"),
    HEAVY_CRUISER("CA", "shipspawnegg2"),
    BATTLESHIP("BB", "shipspawnegg3"),
    AUXILIARY_OILER("AO", "shipspawnegg4"),
    SUBMARINE("SS", "shipspawnegg5"),
    DESTROYER_ESCORT("DE", "shipspawnegg6"),
    PRINCESS("PR", "shipspawnegg7"),
    AIRCRAFT_CARRIER("CV", "shipspawnegg8");

    private final String code;
    private final String textureName;

    ShipClass(String code, String textureName) {
        this.code = code;
        this.textureName = textureName;
    }

    public String getCode() {
        return code;
    }

    public String getTextureName() {
        return textureName;
    }
}
