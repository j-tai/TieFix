gradle := "./gradlew"
open := "xdg-open"
today := `date +%Y-%m-%d`

# Display this help message
help:
    @just --list

# Build the project
build:
    {{gradle}} build

# Run the mod
run: build
    {{gradle}} runClient --console=plain

# Decompile Minecraft
decompile:
    {{gradle}} genSources

# Publish to Modrinth and CurseForge
publish: build
    {{gradle}} modrinth
    -{{open}} 'https://modrinth.com/mod/tiefix/versions'
    {{gradle}} curseforge
    -{{open}} 'https://legacy.curseforge.com/minecraft/mc-mods/tiefix/files'

# Bump the version number and commit
bump-version version:
    sed -i~ 's/^## master$/## v{{version}} ({{today}})/' CHANGELOG.md
    grep '^## v{{version}} ({{today}})$' CHANGELOG.md
    sed -i~ 's/^mod_version=.*$/mod_version={{version}}/' gradle.properties
    grep '^mod_version={{version}}$' gradle.properties
    @just build
    git add .
    git commit -m 'Bump version to {{version}}'
    git tag -s -m 'v{{version}}' 'v{{version}}'
