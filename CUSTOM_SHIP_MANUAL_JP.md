# Shincolle カスタム艦娘作成マニュアル

本MODでは、**Blockbench**で作成したモデル（GeckoLib / Bedrock形式）と設定ファイルを用意することで、オリジナルの艦娘・深海棲艦を追加できます。

---

## 1. フォルダ構成

カスタム艦娘のデータは、Minecraft実行フォルダ（`.minecraft/`または`run/`）の以下のパスに配置します：

```text
.minecraft/
 └── shincolle/
      └── custom_ships/
           └── <ship_id>/             艦娘ごとの固有ID
                ├── ship.json         基本設定・ステータス・Appearance設定
                ├── model.geo.json    Blockbenchモデルデータ
                ├── texture.png       128x128px テクスチャ画像
                └── animation.json    GeckoLibアニメーションデータ（任意）
```

> **テンプレートの利用**:
> MODフォルダ内にテンプレート`template_custom_ship/`があります。こちらを素体に作成することをお勧めします。

---

## 2. `ship.json`の仕様と書き方

艦娘の名前、当たり判定、ステータス、造船レシピ、インベントリから着脱可能なパーツを定義します。

### サンプル (`ship.json`)
```json
{
  "id": "my_ship",
  "name": "オリジナル艦娘",
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
      "name": "ネコ耳",
      "bones": ["CatEarsLeft", "CatEarsRight"]
    }
  ]
}
```

### 各項目の解説

| 項目 | 型 | 説明 |
| :--- | :--- | :--- |
| `id` | 文字列 | 艦娘の一意な識別ID。フォルダ名と一致させてください。 |
| `name` | 文字列 | ゲーム内で表示される名称。 |
| `ship_class` | 文字列 | 艦種。スポーンエッグのアイコン枠や挙動に影響します。<br>`DESTROYER`（駆逐艦）, `LIGHT_CRUISER`（軽巡）, `HEAVY_CRUISER`（重巡）, `BATTLESHIP`（戦艦）, `AIRCRAFT_CARRIER`（空母）, `SUBMARINE`（潜水艦）, `AUXILIARY_OILER`（補給艦）, `DEMON`（鬼/棲鬼）, `PRINCESS`（姫/棲姫） |
| `hitbox.width` | 数値 | 当たり判定の横幅（ブロック単位。標準: 0.6） |
| `hitbox.height` | 数値 | 当たり判定の高さ（ブロック単位。標準: 1.5） |
| `hitbox.eye_height` | 数値 | 視線の高さ（標準: 1.3） |
| `stats.max_hp` | 数値 | 最大HP（例: 80.0 〜 150.0） |
| `stats.attack_power` | 数値 | 基礎攻撃力 |
| `stats.armor` | 数値 | 装甲値（防御力） |
| `stats.movement_speed` | 数値 | 移動速度（標準: 0.3 〜 0.35） |
| `stats.attack_range` | 数値 | 索敵・攻撃射程距離（ブロック数） |
| `shipyard.enable_build` | 真偽値 | `true`にすると、造船所ブロックでの建造レシピに自動登録されます。 |
| `shipyard.large_shipyard` | 真偽値 | `true`で大型建造専用、`false`で小型建造になります。 |
| `shipyard.target_total_mats` | 数値 | 建造に必要な資材投入量の目安。 |
| `shipyard.preferred_material` | 文字列 | 建造で優先される資材名（`grudge`: 怨念, `abyssium`: 深海金属 など）。 |
| `appearance_parts` | 配列 | インベントリの**Appearance タブ**で表示/非表示を切り替えられるパーツ設定。 |

#### `appearance_parts`の設定方法
* `key`: パーツの識別キー（英数字、例: `"hat"`, `"ribbon"`）
* `name`: GUIツールチップに表示される名前。
  * **既存の翻訳キー**: `gui.shincolle.equip.hat`（帽子）、`gui.shincolle.equip.rigging`（艤装）、`gui.shincolle.equip.cannon`（主砲）、`gui.shincolle.equip.torpedo`（魚雷）、`gui.shincolle.equip.anchor`（錨）、`gui.shincolle.equip.shoulder_searchlight`（探照灯）などが利用可能です。
  * **直接の文字列入力**: `"ネコ耳"` や `"Ribbon"` など、自由な名前をそのまま直接入力可能。
* `bones`: トグルOFF時に非表示（非描画）にするモデル内のボーン名リスト。親ボーンを指定すると子ボーンも自動的に非表示になります。

---

## 3. `model.geo.json`（Blockbenchでの作成方法）

### 推奨環境
* ツール: **Blockbench**
* プロジェクト形式: **Bedrock Model** または **GeckoLib Animated Model**
* フォーマットバージョン: `1.12.0`

### 必須・推奨ボーン名
| ボーン名 | 説明 |
| :--- | :--- |
| `root` | モデル全体の最上位ルートボーン（pivot: `[0, 0, 0]`）。座りや死亡時の位置移動に使用。 |
| `body` | 胴体（pivot: `[0, 22, 0]`）。 |
| `head` または `Head` | 頭部（pivot: `[0, 40, 0]`）。**自動的にプレイヤーへの視線に追従します。** |
| `arm_left` / `arm_right` | 左右の腕。 |
| `leg_left` / `leg_right` | 左右の脚。 |

### 表情アニメーション（目パチ・口パク・照れ）
頭部ボーン（`head` または `Head`）の子要素として、以下のボーン名で厚み 0 の板ポリゴンを配置すると、瞬き・口パク・感情変化サイクルが自動連動します：

* `Face0` 〜 `Face4` : 目パチ（0: 開き目, 1: 半目, 2: 閉じ目, 3: 笑顔/ウインク, 4: 被弾/困り目）
* `Mouth0` 〜 `Mouth2` : 口パク（0: 閉じ口, 1: 笑顔口, 2: 開き口）
* `Flush0`, `Flush1` : 照れチーク（照れ・嬉しい感情時に自動表示）


### 発光パーツ（Glow Parts）
* **表情パーツ（目・口・照れ）**:
  * `Face0`〜`Face4`、`Mouth0`〜`Mouth2`、`Flush0`〜`Flush1` は、`GlowHead`と同様に自動的に発光レイヤーとして描画されます。そのため、特別な設定をしなくても暗闇の中で瞳や表情が鮮やかに光ります。
* **その他の発光パーツ（探照灯、光線エフェクト、装飾など）**:
  * ボーン名に `Glow` または `LightEF` を含める（例: `GlowSearchlight`, `LightEF01`, `sword_glow`）と、暗闇でも光を放つフルブライト発光レイヤーとして自動描画されます。

---

## 4. `texture.png`（テクスチャ）の仕様

* **サイズ**: **128 × 128 px**（RGBA、透過PNG）
* **表情領域の配置**:
  * 右下領域（X: 98〜128, Y: 53〜128）は既存Shincolle標準の表情パーツ領域です。
  * `template_custom_ship/texture.png` または `template_akatsuki/texture.png` の右下領域をそのままコピーしてベースとして利用することを推奨します。

---

## 5. `animation.json`（モーション）の仕様

Blockbench のアニメーションモードで作成し、GeckoLib形式（`1.8.0`）で書き出します。

### 定義可能なアニメーション名
1. `idle`: 待機モーション（ループ）
2. `walk`: 歩行モーション（ループ）
3. `run`: ダッシュ疾走モーション（ループ）
4. `sit`: お座りポーズ（ループ）
5. `attack`: 攻撃モーション（1回再生）
6. `dead`: 大破・撃沈ポーズ（ループ）

### モーション未定義時の自動フォールバック
`animation.json` を用意しない場合、あるいは特定のアニメーションが無い場合でも、以下の標準動作が自動的に適用されます：
* `walk` / `run` が無い場合: バニラ標準の腕・脚スイング歩行
* `sit` が無い場合: 脚を前に投げ出して腰を落とす座りポーズ
* `dead` が無い場合: 床面にあおむけに倒れる撃沈ポーズ

---

## 6. ゲーム内での確認・召喚

### 1. スポーンエッグ
* クリエイティブインベントリのタブに、追加したカスタム艦娘のスポーンエッグが自動的に登録されます。

### 2. コマンドでの召喚
`/summon` コマンドで直接召喚することも可能です：
```text
/summon shincolle:custom_ship ~ ~ ~ {CustomShipId:"my_ship"}
```
※ `CustomShipId` には `ship.json` の `id`（フォルダ名）を指定します。

### 3. 造船（Shipyard）
`shipyard.enable_build: true` に設定されている場合、小型または大型造船所で建造レシピとして出現します。

---