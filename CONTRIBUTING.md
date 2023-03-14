# Contributing to TieFix

## Adding a fix

1. Add a boolean field to `ca.jtai.tiefix.Config` that toggles the fix on or off.
2. Create the mixin(s) that fix the bug in `ca.jtai.tiefix.mixin`. Remember to set the mixin `priority` to `TieFix.MIXIN_PRIORITY`. Add the mixin(s) to `TieFix.mixins.json`.
3. Add the config option(s) to the options screen in `ca.jtai.tiefix.config.ConfigScreenBuilder`.
4. Add the name of the fix and the bug tracker ID to the language file, `assets/tiefix/lang/en_us.json`.
5. Add the fix to the README and changelog.

## Updating to a new Minecraft version

1. Edit `gradle.properties` and update the fields. There is a link provided in that file to get the updated values.
2. Update the Minecraft version in `fabric.mod.json`.

## Releasing a new version

1. Update the mod version in `gradle.properties` and change the `master` section to the version number in `CHANGELOG.md`.
2. `./gradlew modrinth`
3. `./gradlew curseforge`
4. Check that the publishing went well, and fix anything if needed
5. Copy the README into the mod description in Modrinth and CurseForge
6. Publish to GitHub releases
