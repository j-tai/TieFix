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
    /**
     * The size of each codepoint -- 4 bits each.
     * <p>
     * Each byte contains the size of two codepoints. The bottom four bits
     * contain the size of the first codepoint, and the top four bits contain
     * the size of the second codepoint.
     */
    private final byte[] sizes;

    public Sizemap() {
        // Yeah, this is 500KB of memory... but we all have 500KB to spare, right?
        sizes = new byte[ARRAY_SIZE];
    }

    /**
     * Construct a new Sizemap from the array returned from {@link #getBytes}.
     */
    public Sizemap(byte[] sizes) {
        if (sizes.length != ARRAY_SIZE) {
            throw new IllegalArgumentException(
                "Incorrect size: expected " + ARRAY_SIZE + ", got " + sizes.length
            );
        }
        this.sizes = sizes;
    }

    public int getWidth(int codepoint) {
        var shift = (codepoint & 1) * 4;
        return (sizes[codepoint >> 1] >> shift) & 0xF;
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
        if (size < 0 || size > 0xF) {
            throw new IllegalArgumentException("Size out of range: " + size);
        }
        var shift = (codepoint & 1) * 4;
        sizes[codepoint >> 1] &= ~(byte) (0xF << shift);
        sizes[codepoint >> 1] |= (byte) (size << shift);
    }

    /**
     * Get the raw bytes stored by this sizemap. The format of the bytes is
     * subject to change. Do not modify this array.
     */
    public byte[] getBytes() {
        return sizes;
    }

    private static final int ARRAY_SIZE = 0x110000 / 2;
}
