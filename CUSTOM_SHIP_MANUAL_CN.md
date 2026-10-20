# Shincolle 自定义舰娘制作手册

本模组支持通过准备使用 **Blockbench** 制作的模型（GeckoLib / Bedrock 格式）与配置文件，来添加原创的舰娘与深海栖舰。

---

## 1. 文件夹结构

自定义舰娘的数据需放置在 Minecraft 运行目录（`.minecraft/` 或 `run/`）下的以下路径：

```text
.minecraft/
 └── shincolle/
      └── custom_ships/
           └── <ship_id>/             每个舰娘的专属唯一ID
                ├── ship.json         基本配置・属性数值・Appearance（外观）设置
                ├── model.geo.json    Blockbench 模型数据
                ├── texture.png       128x128px 贴图材质
                └── animation.json    GeckoLib 动作动画数据（可选）
```

> **使用模板**:
> 模组文件夹内提供了模板目录 `template_custom_ship/`。推荐以此为基底进行修改与创作。

---

## 2. `ship.json` 规范与编写说明

用于定义舰娘的名称、碰撞箱、属性数值、建造配方以及可在物品栏中装卸切换的外观部件。

### 示例 (`ship.json`)
```json
{
  "id": "my_ship",
  "name": "原创舰娘",
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
      "name": "猫耳",
      "bones": ["CatEarsLeft", "CatEarsRight"]
    }
  ]
}
```

### 各配置项说明

| 配置项 | 类型 | 说明 |
| :--- | :--- | :--- |
| `id` | 字符串 | 舰娘的唯一标识 ID。请与文件夹名称保持一致。 |
| `name` | 字符串 | 在游戏内显示的名称。 |
| `ship_class` | 字符串 | 舰种。会影响刷怪蛋图标边框以及实体行为。<br>`DESTROYER`（驱逐舰）、`LIGHT_CRUISER`（轻巡洋舰）、`HEAVY_CRUISER`（重巡洋舰）、`BATTLESHIP`（战列舰）、`AIRCRAFT_CARRIER`（航空母舰）、`SUBMARINE`（潜艇）、`AUXILIARY_OILER`（补给舰）、`DEMON`（鬼/栖鬼）、`PRINCESS`（姬/栖姬） |
| `hitbox.width` | 数值 | 碰撞箱宽度（方块单位。默认值: 0.6） |
| `hitbox.height` | 数值 | 碰撞箱高度（方块单位。默认值: 1.5） |
| `hitbox.eye_height` | 数值 | 视线高度（默认值: 1.3） |
| `stats.max_hp` | 数值 | 最大生命值（例如: 80.0 ~ 150.0） |
| `stats.attack_power` | 数值 | 基础攻击力 |
| `stats.armor` | 数值 | 装甲值（防御力） |
| `stats.movement_speed` | 数值 | 移动速度（标准值: 0.3 ~ 0.35） |
| `stats.attack_range` | 数值 | 索敌与攻击射程距离（方块数） |
| `shipyard.enable_build` | 布尔值 | 设为 `true` 时，将自动注册到造船厂方块的建造配方中。 |
| `shipyard.large_shipyard` | 布尔值 | 设为 `true` 为大型建造专用（超深海热漩），`false` 为小型建造（深海热泉）。 |
| `shipyard.target_total_mats` | 数值 | 建造所需投入资材总量的参考标准。 |
| `shipyard.preferred_material` | 字符串 | 建造中偏好的主要资材名称（如 `grudge`: 怨念，`abyssium`: 深海金属 等）。 |
| `appearance_parts` | 数组 | 在舰娘物品栏的 **Appearance（外观）标签页** 中可切换显示/隐藏的部件配置。 |

#### `appearance_parts` 的配置方法
* `key`: 部件的标识键（英数字符，例如: `"hat"`、`"ribbon"`）
* `name`: 在 GUI 悬浮提示中显示的名称。
  * **现有的语言翻译键**: 支持使用 `gui.shincolle.equip.hat`（帽子）、`gui.shincolle.equip.rigging`（艤装/舰装）、`gui.shincolle.equip.cannon`（主炮）、`gui.shincolle.equip.torpedo`（鱼雷）、`gui.shincolle.equip.anchor`（锚）、`gui.shincolle.equip.shoulder_searchlight`（探照灯）等。
  * **直接输入文本**: 亦可直接填写任意自定义名称，如 `"猫耳"` 或 `"Ribbon"`。
* `bones`: 当切换为关闭（OFF）时需要隐藏（不渲染）的模型骨骼名称列表。若指定父骨骼，其子骨骼亦会自动隐藏。

---

## 3. `model.geo.json`（在 Blockbench 中的制作方法）

### 推荐环境
* 工具: **Blockbench**
* 项目格式: **Bedrock Model** 或 **GeckoLib Animated Model**
* 格式版本: `1.12.0`

### 必需与推荐骨骼名称
| 骨骼名称 | 说明 |
| :--- | :--- |
| `root` | 整个模型的最高层级根骨骼（pivot: `[0, 0, 0]`）。用于坐姿或击沉倒地时的整体位置平移。 |
| `body` | 躯干/身体（pivot: `[0, 22, 0]`）。 |
| `head` 或 `Head` | 头部（pivot: `[0, 40, 0]`）。**会自动朝向并跟随玩家视线。** |
| `arm_left` / `arm_right` | 左臂 / 右臂。 |
| `leg_left` / `leg_right` | 左腿 / 右腿。 |

### 表情动画（眨眼、张嘴、害羞脸红）
作为头部骨骼（`head` 或 `Head`）的子元素，使用以下骨骼名称放置厚度为 0 的平面网格（Plane），即可自动联动眨眼、张嘴与情绪变化循环：

* `Face0` ~ `Face4`: 眨眼（0: 睁眼, 1: 半睁眼, 2: 闭眼, 3: 微笑/眨单眼, 4: 受击/困扰眼）
* `Mouth0` ~ `Mouth2`: 嘴部动作（0: 闭嘴, 1: 微笑嘴, 2: 张嘴）
* `Flush0`, `Flush1`: 害羞腮红（在感到害羞或开心时自动显示）

### 发光部件（Glow Parts）
* **表情部件（眼睛、嘴巴、腮红）**:
  * `Face0`~`Face4`、`Mouth0`~`Mouth2`、`Flush0`~`Flush1` 与 `GlowHead` 类似，均会自动作为自发光图层进行渲染。因此无需进行特殊配置，眼睛与面部表情在黑暗中也能明亮发光。
* **其他发光部件（探照灯、光效、发光装饰等）**:
  * 骨骼名称中若包含 `Glow` 或 `LightEF`（例如: `GlowSearchlight`, `LightEF01`, `sword_glow`），会自动渲染为不受环境光照影响的全亮发光图层，即使在漆黑环境下也能清晰可见。

---

## 4. `texture.png`（贴图材质）规范

* **尺寸**: **128 × 128 px**（RGBA，带透明通道 PNG）
* **表情区域排布**:
  * 右下角区域（X: 98~128, Y: 53~128）为 Shincolle 原版的标准表情部件区域。
  * 建议直接复制 `template_custom_ship/texture.png` 或 `template_akatsuki/texture.png` 的右下角区域作为基底进行绘制。

---

## 5. `animation.json`（动作动画）规范

在 Blockbench 的动画模式中制作，并导出为 GeckoLib 格式（`1.8.0`）。

### 可定义的动作名称
1. `idle`: 待机动作（循环）
2. `walk`: 行走动作（循环）
3. `run`: 奔跑冲刺动作（循环）
4. `sit`: 坐下姿势（循环）
5. `attack`: 攻击动作（播放一次）
6. `dead`: 大破/击沉倒地姿势（循环）

### 未定义动作时的自动回退机制（Fallback）
若未提供 `animation.json` 或缺少特定动作，模组将自动应用以下默认基础动作：
* 缺少 `walk` / `run`: 原版 Minecraft 标准的手臂与腿部摆动行走
* 缺少 `sit`: 双腿向前伸出并坐下的坐姿
* 缺少 `dead`: 仰面朝天平躺在地上的击沉姿态

---

## 6. 游戏内测试与召唤

### 1. 刷怪蛋（Spawn Egg）
* 创造模式物品栏的对应分类标签页中会自动注册所添加自定义舰娘的刷怪蛋。

### 2. 指令召唤
可直接使用 `/summon` 指令进行召唤：
```text
/summon shincolle:custom_ship ~ ~ ~ {CustomShipId:"my_ship"}
```
※ `CustomShipId` 中填入 `ship.json` 中的 `id`（即文件夹名）。

### 3. 造船（Shipyard）
当配置为 `shipyard.enable_build: true` 时，将作为建造配方出现在小型造船厂（深海热泉）或大型造船厂（超深海热漩）中。

---
