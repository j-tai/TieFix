# TieFix

A Fabric mod that fixes some annoying Minecraft bugs

## Bugs fixed

* [**MC-122477**](https://bugs.mojang.com/browse/MC-122477) Linux/GNU: Opening chat sometimes writes 't'
  * Workaround: Ignore a character entered on the first input poll after opening chat
* [**MC-127970**](https://bugs.mojang.com/browse/MC-127970) Using riptide on a trident with an item in your off-hand causes visual glitch with the item in your offhand
  * Workaround: Render items in hands in regular place while using riptide.
* [**MC-197616**](https://bugs.mojang.com/browse/MC-197616) Certain custom biome settings cause game to spam "Received invalid biome id: -1" in the console, causing major lag or freeze
  * Workaround: Do not log these warnings

## License

[GPLv3.](LICENSE)
