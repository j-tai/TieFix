# Contributing to TieFix

## Adding a fix

1. Add a boolean field to `ca.jtai.tiefix.Config` that toggles the fix on or off.
2. Create the mixin(s) that fix the bug in `ca.jtai.tiefix.mixin`. Remember to set the mixin `priority` to `TieFix.MIXIN_PRIORITY`. Add the mixin(s) to `TieFix.mixins.json`.
3. Add the config option(s) to the options screen in `ca.jtai.tiefix.config.ConfigScreenBuilder`.
4. Add the name of the fix and the bug tracker ID to the language file, `assets/tiefix/lang/en_us.json`.
5. Add the fix to the README.
