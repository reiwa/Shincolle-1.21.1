# Shincolle 自訂艦娘製作手冊

本模組支援透過準備使用 **Blockbench** 製作的模型（GeckoLib / Bedrock 格式）與設定檔，來新增原創的艦娘與深海棲艦。

---

## 1. 資料夾架構

自訂艦娘的資料需放置在 Minecraft 執行目錄（`.minecraft/` 或 `run/`）下的以下路徑：

```text
.minecraft/
 └── shincolle/
      └── custom_ships/
           └── <ship_id>/             每位艦娘專屬的唯一ID
                ├── ship.json         基本設定・數值能力・Appearance（外觀）設定
                ├── model.geo.json    Blockbench 模型資料
                ├── texture.png       128x128px 貼圖材質
                └── animation.json    GeckoLib 動作動畫資料（可選）
```

> **使用範本**:
> 模組資料夾內提供了範本目錄 `template_custom_ship/`。建議以此為基礎進行修改與創作。

---

## 2. `ship.json` 規格與撰寫方式

定義艦娘的名稱、碰撞箱（判定範圍）、各項能力數值、建造配方，以及可在物品欄中切換穿脫的外觀部件。

### 範例 (`ship.json`)
```json
{
  "id": "my_ship",
  "name": "原創艦娘",
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
      "name": "貓耳",
      "bones": ["CatEarsLeft", "CatEarsRight"]
    }
  ]
}
```

### 各設定項目說明

| 設定項目 | 型態 | 說明 |
| :--- | :--- | :--- |
| `id` | 字串 | 艦娘的唯一識別 ID。請與資料夾名稱保持一致。 |
| `name` | 字串 | 遊戲內顯示的名稱。 |
| `ship_class` | 字串 | 艦種。會影響生怪蛋圖示外框與實體行為。<br>`DESTROYER`（驅逐艦）、`LIGHT_CRUISER`（輕巡洋艦）、`HEAVY_CRUISER`（重巡洋艦）、`BATTLESHIP`（戰艦/戰列艦）、`AIRCRAFT_CARRIER`（航空母艦）、`SUBMARINE`（潛水艦/潛艇）、`AUXILIARY_OILER`（補給艦）、`DEMON`（鬼/棲鬼）、`PRINCESS`（姬/棲姬） |
| `hitbox.width` | 數值 | 碰撞箱寬度（方塊單位。預設值: 0.6） |
| `hitbox.height` | 數值 | 碰撞箱高度（方塊單位。預設值: 1.5） |
| `hitbox.eye_height` | 數值 | 視線高度（預設值: 1.3） |
| `stats.max_hp` | 數值 | 最大生命值（例如: 80.0 ~ 150.0） |
| `stats.attack_power` | 數值 | 基礎攻擊力 |
| `stats.armor` | 數值 | 裝甲值（防禦力） |
| `stats.movement_speed` | 數值 | 移動速度（標準值: 0.3 ~ 0.35） |
| `stats.attack_range` | 數值 | 索敵與攻擊射程距離（方塊數） |
| `shipyard.enable_build` | 布林值 | 設為 `true` 時，將自動登錄至造船廠方塊的建造配方中。 |
| `shipyard.large_shipyard` | 布林值 | 設為 `true` 為大型建造專用（超深海熱漩），`false` 為小型建造（深海熱泉）。 |
| `shipyard.target_total_mats` | 數值 | 建造所需投入資材總量的參考基準。 |
| `shipyard.preferred_material` | 字串 | 建造中偏好的優先資材名稱（例如 `grudge`: 怨念，`abyssium`: 深海金屬 等）。 |
| `appearance_parts` | 陣列 | 艦娘物品欄 **Appearance（外觀）分頁** 中可切換顯示/隱藏的部件設定。 |

#### `appearance_parts` 的設定方式
* `key`: 部件的識別鍵（英數字，例如: `"hat"`、`"ribbon"`）
* `name`: GUI 工具提示中顯示的名稱。
  * **現有語言翻譯鍵**: 支援使用 `gui.shincolle.equip.hat`（帽子）、`gui.shincolle.equip.rigging`（艤裝）、`gui.shincolle.equip.cannon`（主砲）、`gui.shincolle.equip.torpedo`（魚雷）、`gui.shincolle.equip.anchor`（錨）、`gui.shincolle.equip.shoulder_searchlight`（探照燈）等。
  * **直接輸入字串**: 亦可直接輸入任意自訂名稱，如 `"貓耳"` 或 `"Ribbon"`。
* `bones`: 切換為關閉（OFF）時隱藏（不渲染）的模型骨骼名稱清單。指定父骨骼時，其子骨骼亦會自動隱藏。

---

## 3. `model.geo.json`（在 Blockbench 中的製作方式）

### 建議環境
* 工具: **Blockbench**
* 專案格式: **Bedrock Model** 或 **GeckoLib Animated Model**
* 格式版本: `1.12.0`

### 必要與建議骨骼名稱
| 骨骼名稱 | 說明 |
| :--- | :--- |
| `root` | 整個模型的最高層級根骨骼（pivot: `[0, 0, 0]`）。用於坐姿或擊沉時的整體位置位移。 |
| `body` | 軀幹/身體（pivot: `[0, 22, 0]`）。 |
| `head` 或 `Head` | 頭部（pivot: `[0, 40, 0]`）。**會自動朝向並追隨玩家視線。** |
| `arm_left` / `arm_right` | 左臂 / 右臂。 |
| `leg_left` / `leg_right` | 左腿 / 右腿。 |

### 表情動畫（眨眼、開合嘴、害羞臉紅）
作為頭部骨骼（`head` 或 `Head`）的子元素，使用以下骨骼名稱放置厚度為 0 的平面網格（Plane），即可自動連動眨眼、嘴型動作與情緒變化循環：

* `Face0` ~ `Face4`: 眨眼（0: 睜眼, 1: 半睜眼, 2: 閉眼, 3: 微笑/單眼眨眼, 4: 受到傷害/困擾眼）
* `Mouth0` ~ `Mouth2`: 嘴型動作（0: 閉嘴, 1: 微笑嘴, 2: 張嘴）
* `Flush0`, `Flush1`: 害羞腮紅（在感到害羞或高興時自動顯示）

### 發光部件（Glow Parts）
* **表情部件（眼睛、嘴型、腮紅）**:
  * `Face0`~`Face4`、`Mouth0`~`Mouth2`、`Flush0`~`Flush1` 與 `GlowHead` 相同，皆會自動作為自發光圖層進行渲染。因此即使未進行特殊設定，瞳孔與臉部表情在黑暗中也能明亮發光。
* **其他發光部件（探照燈、光線特效、裝飾等）**:
  * 骨骼名稱中若包含 `Glow` 或 `LightEF`（例如: `GlowSearchlight`, `LightEF01`, `sword_glow`），將自動渲染為不受環境光影影響的全亮發光圖層，即使在漆黑環境中也能發光顯現。

---

## 4. `texture.png`（貼圖材質）規格

* **尺寸**: **128 × 128 px**（RGBA，透明 PNG）
* **表情區域配置**:
  * 右下角區域（X: 98~128, Y: 53~128）為 Shincolle 預設的標準表情部件區域。
  * 建議直接複製 `template_custom_ship/texture.png` 或 `template_akatsuki/texture.png` 的右下角區域作為底圖進行繪製。

---

## 5. `animation.json`（動作動畫）規格

在 Blockbench 的動畫模式中製作，並以 GeckoLib 格式（`1.8.0`）匯出。

### 可定義的動作名稱
1. `idle`: 待機動作（循環）
2. `walk`: 行走動作（循環）
3. `run`: 衝刺疾跑動作（循環）
4. `sit`: 坐下姿勢（循環）
5. `attack`: 攻擊動作（播放一次）
6. `dead`: 大破/擊沉姿勢（循環）

### 未定義動作時的自動備援機制（Fallback）
未提供 `animation.json` 或缺少特定動作時，模組會自動套用以下標準預設動作：
* 缺少 `walk` / `run`: 原版 Minecraft 標準的手臂與腿部擺動行走
* 缺少 `sit`: 雙腿向前伸直落座的坐姿
* 缺少 `dead`: 仰面平躺在地面上的擊沉姿勢

---

## 6. 遊戲內確認與召喚

### 1. 生怪蛋（Spawn Egg）
* 創造模式物品欄分頁中會自動登錄新增之自訂艦娘的生怪蛋。

### 2. 指令召喚
亦可直接透過 `/summon` 指令進行召喚：
```text
/summon shincolle:custom_ship ~ ~ ~ {CustomShipId:"my_ship"}
```
※ `CustomShipId` 請填入 `ship.json` 中的 `id`（即資料夾名稱）。

### 3. 造船（Shipyard）
若設定為 `shipyard.enable_build: true`，將作為建造配方出現在小型造船廠（深海熱泉）或大型造船廠（超深海熱漩）中。

---
