# Shincolle Custom Ship Creation Manual

In this mod, by preparing a model created in **Blockbench** (GeckoLib / Bedrock format) along with configuration files, you can add your own original shipgirls and abyssal ships.

---

## 1. Directory Structure

Custom ship data should be placed in the Minecraft runtime directory (`.minecraft/` or `run/`) under the following path:

```text
.minecraft/
 └── shincolle/
      └── custom_ships/
           └── <ship_id>/             Unique ID for each ship
                ├── ship.json         Basic settings, stats, and Appearance configuration
                ├── model.geo.json    Blockbench model data
                ├── texture.png       128x128px texture image
                └── animation.json    GeckoLib animation data (optional)
```

> **Using the Template**:
> A template directory named `template_custom_ship/` is provided within the mod directory. We recommend using this as a base for your custom ship.

---

## 2. `ship.json` Specifications and Configuration

Defines the ship's name, hitbox, stats, shipbuilding recipe, and parts that can be toggled on/off in the inventory.

### Sample (`ship.json`)
```json
{
  "id": "my_ship",
  "name": "Original Shipgirl",
  "ship_class": "DESTROYER",
  "hitbox": {
    "width": 0.6,
    "height": 1.5,
    "eye_height": 1.3
  },
  "stats": {
    "max_hp": 88.0,
    "attack_power": 16.0,
    "armor": 12.0,
    "movement_speed": 0.33,
    "attack_range": 18.0
  },
  "shipyard": {
    "enable_build": true,
    "large_shipyard": false,
    "target_total_mats": 150,
    "preferred_material": "grudge"
  },
  "appearance_parts": [
    {
      "key": "hat",
      "name": "gui.shincolle.equip.hat",
      "bones": ["HatBase"]
    },
    {
      "key": "rigging",
      "name": "gui.shincolle.equip.rigging",
      "bones": ["EquipBase"]
    },
    {
      "key": "cat_ears",
      "name": "Cat Ears",
      "bones": ["CatEarsLeft", "CatEarsRight"]
    }
  ]
}
```

### Property Details

| Property | Type | Description |
| :--- | :--- | :--- |
| `id` | String | Unique identifier ID of the ship. Must match the folder name. |
| `name` | String | Name displayed in-game. |
| `ship_class` | String | Ship class. Affects spawn egg icon frames and entity behavior.<br>`DESTROYER` (Destroyer), `LIGHT_CRUISER` (Light Cruiser), `HEAVY_CRUISER` (Heavy Cruiser), `BATTLESHIP` (Battleship), `AIRCRAFT_CARRIER` (Aircraft Carrier), `SUBMARINE` (Submarine), `AUXILIARY_OILER` (Auxiliary Oiler), `DEMON` (Demon), `PRINCESS` (Princess) |
| `hitbox.width` | Number | Hitbox width (in blocks. Default: 0.6) |
| `hitbox.height` | Number | Hitbox height (in blocks. Default: 1.5) |
| `hitbox.eye_height` | Number | Eye level height (Default: 1.3) |
| `stats.max_hp` | Number | Maximum HP (e.g., 80.0 – 150.0) |
| `stats.attack_power` | Number | Base attack power |
| `stats.armor` | Number | Armor value (Defense) |
| `stats.movement_speed` | Number | Movement speed (Default: 0.3 – 0.35) |
| `stats.attack_range` | Number | Detection and attack range (in blocks) |
| `shipyard.enable_build` | Boolean | If `true`, automatically registered as a build recipe in Shipyard blocks. |
| `shipyard.large_shipyard` | Boolean | `true` for Large Shipyard (Hadal Vortex) only, `false` for Small Shipyard (Abyssal Vent). |
| `shipyard.target_total_mats` | Number | Target total material investment required for building. |
| `shipyard.preferred_material` | String | Preferred material in shipbuilding (`grudge`: Grudge, `abyssium`: Abyssium, etc.). |
| `appearance_parts` | Array | Part configurations toggleable via the **Appearance tab** in the inventory. |

#### Configuring `appearance_parts`
* `key`: Identifier key for the part (alphanumeric, e.g., `"hat"`, `"ribbon"`)
* `name`: Name displayed in the GUI tooltip.
  * **Existing translation keys**: `gui.shincolle.equip.hat` (Hat), `gui.shincolle.equip.rigging` (Rigging), `gui.shincolle.equip.cannon` (Cannon), `gui.shincolle.equip.torpedo` (Torpedo), `gui.shincolle.equip.anchor` (Anchor), `gui.shincolle.equip.shoulder_searchlight` (Searchlight), etc., are available.
  * **Direct string input**: You can also enter arbitrary custom names directly, such as `"Cat Ears"` or `"Ribbon"`.
* `bones`: List of bone names in the model to hide (disable rendering) when toggled OFF. Specifying a parent bone will automatically hide all of its child bones as well.

---

## 3. `model.geo.json` (Modeling in Blockbench)

### Recommended Environment
* Tool: **Blockbench**
* Project Format: **Bedrock Model** or **GeckoLib Animated Model**
* Format Version: `1.12.0`

### Required & Recommended Bone Names
| Bone Name | Description |
| :--- | :--- |
| `root` | Top-level root bone of the entire model (pivot: `[0, 0, 0]`). Used for position offsets when sitting or sinking. |
| `body` | Torso / Body (pivot: `[0, 22, 0]`). |
| `head` or `Head` | Head (pivot: `[0, 40, 0]`). **Automatically tracks and turns toward the player's line of sight.** |
| `arm_left` / `arm_right` | Left and right arms. |
| `leg_left` / `leg_right` | Left and right legs. |

### Facial Expression Animations (Blinking, Mouth Movement, Blushing)
By placing 0-thickness quad planes under the head bone (`head` or `Head`) with the following bone names, blinking, mouth movement, and emotional state cycles will link automatically:

* `Face0` – `Face4`: Blinking (0: Open eyes, 1: Half-open eyes, 2: Closed eyes, 3: Smile / Wink, 4: Hurt / Troubled eyes)
* `Mouth0` – `Mouth2`: Mouth movement (0: Closed mouth, 1: Smiling mouth, 2: Open mouth)
* `Flush0`, `Flush1`: Blushing cheeks (Automatically displayed during shy or joyful emotion states)

### Glowing Parts (Glow Parts)
* **Facial Expression Parts (Eyes, mouth, blushing)**:
  * `Face0`–`Face4`, `Mouth0`–`Mouth2`, and `Flush0`–`Flush1` are automatically rendered on a glowing layer, similar to `GlowHead`. Therefore, eyes and facial expressions will glow vibrantly in the dark without any special configuration.
* **Other Glowing Parts (Searchlights, beam effects, decorations, etc.)**:
  * Any bone whose name contains `Glow` or `LightEF` (e.g., `GlowSearchlight`, `LightEF01`, `sword_glow`) will automatically be rendered on a full-bright emissive layer, shining brightly even in pitch darkness.

---

## 4. `texture.png` (Texture Specifications)

* **Dimensions**: **128 × 128 px** (RGBA, PNG with transparency)
* **Facial Expression Region Layout**:
  * The bottom-right region (X: 98–128, Y: 53–128) is the standard Shincolle facial expression parts area.
  * It is recommended to copy the bottom-right region directly from `template_custom_ship/texture.png` or `template_akatsuki/texture.png` to use as a base.

---

## 5. `animation.json` (Motion / Animation Specifications)

Created in Blockbench's Animation mode and exported in GeckoLib format (`1.8.0`).

### Definable Animation Names
1. `idle`: Idle motion (Looping)
2. `walk`: Walking motion (Looping)
3. `run`: Dash / Running motion (Looping)
4. `sit`: Sitting pose (Looping)
5. `attack`: Attack motion (Plays once)
6. `dead`: Heavily damaged / Sunken pose (Looping)

### Automatic Fallback for Undefined Motions
If `animation.json` is omitted, or if specific animations are not defined, the following default fallback behaviors will automatically apply:
* Missing `walk` / `run`: Vanilla-style arm and leg swinging walk
* Missing `sit`: Sitting pose with legs extended forward
* Missing `dead`: Sunken pose lying face-up flat on the ground

---

## 6. In-Game Testing & Summoning

### 1. Spawn Egg
* A spawn egg for each added custom ship is automatically registered in the corresponding Creative Inventory tab.

### 2. Summoning via Command
You can also summon custom ships directly using the `/summon` command:
```text
/summon shincolle:custom_ship ~ ~ ~ {CustomShipId:"my_ship"}
```
* Note: Replace `my_ship` with the `id` specified in `ship.json` (the folder name).

### 3. Shipbuilding (Shipyard)
When configured with `shipyard.enable_build: true`, the ship will appear as a constructible recipe in either the Small Shipyard (Abyssal Vent) or Large Shipyard (Hadal Vortex).

---
