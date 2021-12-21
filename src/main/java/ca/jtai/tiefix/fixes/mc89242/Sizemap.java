package ca.jtai.tiefix.fixes.mc89242;

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
    private byte[] sizes = new byte[0x10000];

    public int getWidth(char ch) {
        return sizes[ch];
    }

    public int getWidth(String text) {
        var result = 0;
        for (var ch : text.toCharArray()) {
            result += getWidth(ch);
        }
        return (int) Math.ceil(result);
    }

    public void set(char ch, int size) {
        assert size >= 0 && size <= Byte.MAX_VALUE;
        sizes[ch] = (byte) size;
    }
}
