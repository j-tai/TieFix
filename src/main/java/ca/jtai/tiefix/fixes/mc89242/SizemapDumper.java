package ca.jtai.tiefix.fixes.mc89242;

import com.google.gson.Gson;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

import java.io.File;
import java.io.FileWriter;

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
        player.sendMessage(new LiteralText("TieFix: Dumping the sizemap..."), false);

        var sizemap = new Sizemap();

        var renderer = client.textRenderer;
        for (var codepoint = 0; codepoint <= 0xFFFF; codepoint++) {
            var ch = (char) codepoint;
            var width = renderer.getWidth(String.valueOf(ch));
            // Sanity checks
            if (width <= 0) {
                player.sendMessage(new LiteralText(
                    "W: U+%04X '%c' has small or negative width %d".formatted(codepoint, ch, width)
                ), false);
            }
            sizemap.set(ch, width);
        }

        var gson = new Gson();
        var file = new File("sizemap.json");
        try (var writer = new FileWriter(file)) {
            gson.toJson(sizemap, writer);
            player.sendMessage(new LiteralText("Saved sizemap to sizemap.json"), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
