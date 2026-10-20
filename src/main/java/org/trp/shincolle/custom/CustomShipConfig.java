package org.trp.shincolle.custom;

import org.trp.shincolle.item.ShipClass;

import java.nio.file.Path;

public class CustomShipConfig {
    public String id = "";
    public String name = "";
    public String ship_class = "DESTROYER";
    public HitboxConfig hitbox = new HitboxConfig();
    public StatsConfig stats = new StatsConfig();
    public ShipyardConfig shipyard = new ShipyardConfig();
    public java.util.List<AppearancePartConfig> appearance_parts = new java.util.ArrayList<>();

    public static class AppearancePartConfig {
        public String key = "";
        public String name = "";
        public java.util.List<String> bones = new java.util.ArrayList<>();
    }

    public transient Path folderPath;

    public static class HitboxConfig {
        public float width = 0.6F;
        public float height = 1.5F;
        public float eye_height = 1.3F;
    }

    public static class StatsConfig {
        public double max_hp = 80.0;
        public double attack_power = 12.0;
        public double armor = 8.0;
        public double movement_speed = 0.3;
        public double attack_range = 16.0;
    }

    public static class ShipyardConfig {
        public boolean enable_build = true;
        public boolean large_shipyard = false;
        public int target_total_mats = 120;
        public String preferred_material = "grudge";
    }

    public ShipClass getParsedShipClass() {
        if (ship_class != null) {
            try {
                return ShipClass.valueOf(ship_class.toUpperCase());
            } catch (IllegalArgumentException ignored) {
            }
        }
        return ShipClass.DESTROYER;
    }
}
