# TieFix

A Fabric mod that fixes some annoying bugs in the Minecraft client.

[**Download on Modrinth**](https://modrinth.com/mod/tiefix)

## Bugs fixed

* [**MC-2071**](https://bugs.mojang.com/browse/MC-2071) Pausing the Game or opening any GUI in a nether portal does not work
  * Fix: Allow opening GUIs in a nether portal
* [**MC-89242**](https://bugs.mojang.com/browse/MC-89242) Length of writing text on sign limited by Resource Pack
  * Fix: Limit the text based on the default font
* [**MC-122477**](https://bugs.mojang.com/browse/MC-122477) Linux/GNU: Opening chat sometimes writes 't'
  * Workaround: Ignore a character entered on the first input poll after opening chat
* [**MC-127970**](https://bugs.mojang.com/browse/MC-127970) Using riptide on a trident with an item in your off-hand causes visual glitch with the item in your offhand
  * Fix: Render non-trident items in regular place while using riptide
* [**MC-140646**](https://bugs.mojang.com/browse/MC-140646) Text fields don't scroll while selecting text with Shift
  * Fix: Scroll the text properly
* [**MC-145929**](https://bugs.mojang.com/browse/MC-145929) Actionbar text may be difficult to read without text background enabled
  * Fix: Add a shadow to the text
* [**MC-147766**](https://bugs.mojang.com/browse/MC-147766) Shift key stays pressed until press any other key
  * Fix: Handle mouse clicks properly
* [**MC-151412**](https://bugs.mojang.com/browse/MC-151412) "Edit Server Info" window does not focus "Server Name" text field automatically
  * Fix: Make the "Server Name" text field focused
* [**MC-233042**](https://bugs.mojang.com/browse/MC-233042) Server Address field isn't focused when Direct Connection menu is opened
  * Fix: Make the "Server Address" text field focused
* [**MC-237493**](https://bugs.mojang.com/browse/MC-237493) Telemetry cannot be disabled
  * Fix: Disable telemetry by default (can be re-enabled in the configuration)

## Configuration

All fixes can be toggled on or off in the options menu.

The options menu can be opened if you have [Mod Menu](https://modrinth.com/mod/modmenu) installed. To access it, go to Mods > TieFix and click the settings button at the top-right.

## License

[LGPLv3.](LICENSE)
