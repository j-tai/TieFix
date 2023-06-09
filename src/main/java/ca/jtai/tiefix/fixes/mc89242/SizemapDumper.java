package ca.jtai.tiefix.fixes.mc89242;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.nio.file.Files;
import java.nio.file.Path;

public class SizemapDumper {
    /**
     * Create a sizemap for the currently loaded font, and write it to {@code sizemap.json}.
     * <p>
     * This method prints debug messages to the client's chat window. The player must be in-game.
     */
    public static void dump(MinecraftClient client) {
        var player = client.player;
        if (player == null) {
            return;
        }
        player.sendMessage(Text.of("TieFix: ENSURE RESOURCE PACKS ARE DISABLED!"));
        player.sendMessage(Text.of("TieFix: Dumping the sizemap..."));

        var sizemap = new Sizemap();

        var renderer = client.textRenderer;
        for (var codepoint = 0; codepoint <= 0x10FFFF; ++codepoint) {
            var text = new String(Character.toChars(codepoint));
            var width = renderer.getTextHandler().getWidth(text);
            // Sanity checks
            if (width < 0.5) {
                player.sendMessage(Text.of(
                    "W: U+%04X '%s' has small or negative width %f".formatted(codepoint, text, width)
                ));
            }
            var roundedWidth = Math.ceil(width);
            var offset = Math.abs(roundedWidth - width);
            if (offset > 0.01) {
                player.sendMessage(Text.of(
                    "W: U+%04X '%s' has non-integer width %f".formatted(codepoint, text, width)
                ));
            }
            sizemap.set(codepoint, (int) roundedWidth);
        }

        try {
            Files.write(Path.of("sizemap.bin"), sizemap.getBytes());
            player.sendMessage(Text.of("Saved sizemap to sizemap.bin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
