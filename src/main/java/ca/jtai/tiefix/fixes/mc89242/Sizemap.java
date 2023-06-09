package ca.jtai.tiefix.fixes.mc89242;

import net.minecraft.text.Style;
import net.minecraft.text.TextVisitFactory;

/**
 * A mapping of characters to their widths when rendered.
 * <p>
 * A sizemap helps calculate the width of text when rendered. This
 * functionality is already provided by Minecraft's {@code TextRenderer};
 * however, that implementation depends on the currently loaded font, which can
 * be changed by resource packs. This class is independent from
 * {@code TextRenderer}, so it can be used to calculate the width of text
 * <em>if</em> it were rendered in the default font.
 */
public class Sizemap {
    // Yeah, this is 1MB of memory... but we all have 1MB to spare, right?
    private byte[] sizes = new byte[0x110000];

    public int getWidth(int codepoint) {
        return sizes[codepoint];
    }

    public int getWidth(String text) {
        int[] total = {0};
        TextVisitFactory.visitForwards(text, Style.EMPTY, (index, style, codepoint) -> {
            total[0] += getWidth(codepoint);
            return true;
        });
        return total[0];
    }

    public void set(int codepoint, int size) {
        assert size >= 0 && size <= Byte.MAX_VALUE;
        sizes[codepoint] = (byte) size;
    }
}
