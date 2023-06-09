gradle := "./gradlew"
open := "xdg-open"

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
