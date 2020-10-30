# TieFix

A Fabric mod that fixes some annoying bugs in the Minecraft client.

## Bugs fixed

* [**MC-122477**](https://bugs.mojang.com/browse/MC-122477) Linux/GNU: Opening chat sometimes writes 't'
  * Workaround: Ignore a character entered on the first input poll after opening chat
* [**MC-127970**](https://bugs.mojang.com/browse/MC-127970) Using riptide on a trident with an item in your off-hand causes visual glitch with the item in your offhand
  * Workaround: Render held items in regular place while using riptide
* [**MC-197616**](https://bugs.mojang.com/browse/MC-197616) Certain custom biome settings cause game to spam "Received invalid biome id: -1" in the console, causing major lag or freeze
  * Workaround: Do not log these warnings

## Configuration

The configuration file can be found in `.minecraft/config/tiefix.json`. A default configuration file is created when you first open Minecraft with this mod installed.

The configuration is in JSON format. Note that comments are not allowed. If the configuration file is invalid, then an error will be logged, and the configuration will be reset.

The `*_fix` options can be changed from `true` to `false` to disable fixes. You are recommended to leave all of them on, except where noted below.

* `mc122477_fix`: whether to enable the fix for MC-122477. This is enabled by default, but since the bug only occurs on Linux, you may turn this off if you are running Windows or macOS.
* `mc122477_keys`: which keys to ignore. When a character is typed on the first poll after opening the chat, it will be ignored only if it is found in this string. If this string is empty, then all characters will be ignored. You are recommended to leave this empty.
* `mc127970_fix`: whether to enable the fix for MC-127970.
* `mc197616_fix`: whether to enable the fix for MC-197616.

## License

[GPLv3.](LICENSE)
