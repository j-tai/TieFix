# TieFix

A Fabric mod that fixes some annoying bugs in the Minecraft client.

Requires Fabric API.

**Download on [Modrinth](https://modrinth.com/mod/tiefix), [CurseForge](https://www.curseforge.com/minecraft/mc-mods/tiefix), or [GitHub Releases](https://github.com/j-tai/TieFix/releases)**

## Bugs fixed

### UI & HUD
* [**MC-2071**](https://bugs.mojang.com/browse/MC-2071) Pausing the Game or opening any GUI in a nether portal does not
  work
  * Fix: Allow opening GUIs in a nether portal
* [**MC-62997**](https://bugs.mojang.com/browse/MC-62997) Scoreboard overlaps debug screen
  * Fix: Hide the scoreboard when the F3 screen is shown
* [**MC-89242**](https://bugs.mojang.com/browse/MC-89242) Length of writing text on sign limited by Resource Pack
  * Fix: Limit the text based on the default font
* [**MC-122477**](https://bugs.mojang.com/browse/MC-122477) Linux/GNU: Opening chat sometimes writes 't'
  * Workaround: Ignore a character entered on the first input poll after opening chat
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

### Controls
* [**MC-122645**](https://bugs.mojang.com/browse/MC-122645) Narrator hotkey cannot be customized or disabled
  * Fix: Disable the narrator hotkey by default (can be re-enabled in the configuration)
* [**MC-203401**](https://bugs.mojang.com/browse/MC-203401) Double-tapping forward button to sprint cannot be disabled/reconfigured
  * Fix: Add option to disable double-tap-to-sprint

### Audio & Visual
* [**MC-4490**](https://bugs.mojang.com/browse/MC-4490) Fishing line not attached to fishing rod in third person while
  crouching
  * Fix: Render the fishing line in the correct place
* [**MC-53312**](https://bugs.mojang.com/browse/MC-53312) Illager/(zombie) villager/witch robes don't render the last
  two rows of pixels
  * Fix: Render the last two rows
* [**MC-127970**](https://bugs.mojang.com/browse/MC-127970) Using riptide on a trident with an item in your off-hand
  causes visual glitch with the item in your offhand
  * Fix: Render non-trident items in regular place while using riptide

### Gameplay & Movement
* [**MC-12829**](https://bugs.mojang.com/browse/MC-12829) Flying through ladders/vines/scaffolding in creative mode slows you down
  * Fix: Ignore climbable blocks when flying
* [**MC-136249**](https://bugs.mojang.com/browse/MC-136249) Depth strider decreases Riptide's launching effect when underwater
  * Fix: Ignore depth strider's increased drag while using riptide

### Miscellaneous
* [**MC-237493**](https://bugs.mojang.com/browse/MC-237493) Telemetry cannot be disabled
  * Fix: Disable telemetry by default (can be re-enabled in the configuration)

## Configuration

All fixes can be toggled on or off in the options menu.

The options menu can be opened if you have [Mod Menu](https://modrinth.com/mod/modmenu) installed. To access it, go to Mods > TieFix and click the settings button at the top-right.

## License

[LGPLv3.](https://github.com/j-tai/TieFix/blob/master/LICENSE)
