# 鼠鼠弹幕重置版 (MouseDanmu Remastered)

在《我的世界》中实时显示哔哩哔哩直播弹幕的客户端 Fabric 模组。

> 本模组为经典「鼠鼠弹幕」模组的重置版，本分支适配 Minecraft 1.21.8（Fabric）。

---

## ✨ 功能特性

- **实时弹幕**：在游戏内实时显示直播间弹幕、表情、礼物、醒目留言（SC）、大航海（舰长/提督/总督）与互动消息（进场、关注、点赞等）
- **自定义样式**：弹幕、粉丝团灯牌、礼物、SC、舰队均可独立设置开关与颜色
- **分级配色**：礼物按价格档位（5/10/50/100 元）自动配色；粉丝团按等级（4/8/12/16/20/24/28 级）变色；SC 按价格档位（50/100/500/1000/2000 元）变色
- **SC 横幅**：醒目留言支持顶部 BossBar 横幅展示
- **发送弹幕**：配置 Cookie 后可在游戏内直接发送弹幕
- **自动重连**：直播连接断开后自动重连（最多 5 次，间隔 3 秒）
- **自动连接**：进入世界后自动连接上次使用的直播间（可在配置中开启）
- **连接状态 HUD**：屏幕右下角实时显示「已连接：房间号」（绿色）或「已断开」（红色）
- **欢迎提示**：进入世界时显示模组欢迎语（模组名、游戏版本、模组版本、作者）
- **多语言**：内置简体中文与英文

---

## 📋 环境要求

| 项目 | 版本 |
| --- | --- |
| Minecraft | 1.21.8 |
| Fabric Loader | ≥ 0.20.1 |
| Java | ≥ 21 |
| Fabric API | 0.136.1+1.21.8 |
| Cloth Config API | ≥ 19.0.147 |
| Mod Menu | 15.0.2（可选） |


---

## 📦 安装

1. 安装 [Fabric Loader](https://fabricmc.net/use/) 并启动一次游戏生成 `mods` 文件夹
2. 将本模组 Jar 包放入 `.minecraft/mods` 目录
3. 同时放入以下前置模组：
   - [Fabric API](https://modrinth.com/mod/fabric-api)
   - [Cloth Config API](https://modrinth.com/mod/cloth-config)
   - [Mod Menu](https://modrinth.com/mod/modmenu)（可选，推荐）
4. 重新启动游戏

---

## 🎮 使用说明

### 指令

| 指令 | 说明 |
| --- | --- |
| `/mdmr help` | 查看本模组所有指令 |
| `/mdmr connect <房间号>` | 连接指定直播间（如 `/mdmr connect 24256088`） |
| `/mdmr close` | 断开直播连接 |
| `/mdmr status` | 查看当前连接状态 |
| `/mdmr test [延迟毫秒]` | 测试弹幕显示效果（默认延迟 500ms） |
| `/mdmr <消息>` | 发送弹幕（需要已配置 Cookie） |

### 配置

推荐通过 **Mod Menu → 配置** 打开图形化设置界面。配置保存在 `config/mousedanmu-remastered.json`，也可手动编辑。

配置分类：

- **直播房间（liveRoom）**
  - `autoConnect`：进入世界后是否自动连接
  - `roomId`：默认直播间号
  - `cookie`：B 站用户 Cookie（仅发送弹幕时需要）
  - `history`：历史连接过的房间号列表

- **弹幕（danmu）**
  - `isShow`：是否显示弹幕
  - `isShowEmoji` / `emojiColor`：表情显示与颜色
  - `isShowInteractive` / `isShowInteractiveToOverlayMessage` / `interactiveColor`：互动消息显示、是否叠加到顶部字幕、颜色
  - `isShowFansMedal` / `fansColor`：粉丝团灯牌显示与基础颜色
  - `fansLargerThan4/8/12/16/20/24/28`：粉丝团各等级对应的颜色

- **礼物（gift）**
  - `isShow`：是否显示礼物
  - `isShowFreeGiftToOverlayMessage`：免费礼物是否叠加到顶部字幕
  - `color`：默认礼物颜色
  - `price1~4` / `color1~4`：按价格档位设置颜色

- **醒目留言（superChat）**
  - `isShowChat`：是否在聊天框显示 SC
  - `isShowBossBar`：是否显示 SC 顶部横幅
  - `color` / `color50 / color100 / color500 / color1000 / color2000`：按价格档位设置颜色

- **大航海（guard）**
  - `isShow`：是否显示舰队消息
  - `color`：默认颜色
  - `level1 / level2 / level3`：舰长 / 提督 / 总督 颜色

---

## ⚠️ 免责声明

- 本模组通过哔哩哔哩公开接口获取直播弹幕，仅用于个人娱乐与学习交流
- 可选使用用户 Cookie 发送弹幕。**使用 Cookie 存在账号风险**，请谨慎使用；由此产生的账号或直播间封禁风险由使用者自行承担

---

## 🔧 构建

```bash
git clone <仓库地址>
cd mouse-danmu
./gradlew build
```

构建产物位于 `build/libs/`。

---

## 📄 License

本模组基于 [MIT](LICENSE) 协议开源。

作者：[XiaoLiang LiQing]
