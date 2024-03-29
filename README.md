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
* [**MC-79545**](https://bugs.mojang.com/browse/MC-79545) The experience bar disappears when too many levels are given
  to the player
  * Fix: Show the experience bar
* [**MC-80859**](https://bugs.mojang.com/browse/MC-80859) Starting to drag item stacks over other compatible stacks
  makes the latter invisible until appearance change (stack size increases)
  * Fix: Display the item stack properly
* [**MC-89242**](https://bugs.mojang.com/browse/MC-89242) Length of writing text on sign limited by Resource Pack
  * Fix: Limit the text based on the default font
* [**MC-122477**](https://bugs.mojang.com/browse/MC-122477) Linux/GNU: Opening chat sometimes writes 't'
  * Workaround: Ignore a character entered on the first input poll after opening chat
* [**MC-140646**](https://bugs.mojang.com/browse/MC-140646) Text fields don't scroll while selecting text with Shift
  * Fix: Scroll the text properly
* [**MC-147766**](https://bugs.mojang.com/browse/MC-147766) Shift key stays pressed until press any other key
  * Fix: Handle mouse clicks properly

### Controls
* [**MC-122645**](https://bugs.mojang.com/browse/MC-122645) Narrator hotkey cannot be customized or disabled
  * Fix: Disable the narrator hotkey by default (can be re-enabled in the configuration)
* [**MC-203401**](https://bugs.mojang.com/browse/MC-203401) Double-tapping forward button to sprint cannot be disabled/reconfigured
  * Fix: Add option to disable double-tap-to-sprint

### Audio & Visual
* [**MC-4490**](https://bugs.mojang.com/browse/MC-4490) Fishing line not attached to fishing rod in third person while
  crouching
  * Fix: Render the fishing line in the correct place
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
