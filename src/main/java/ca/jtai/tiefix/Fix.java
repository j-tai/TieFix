package ca.jtai.tiefix;

import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

public enum Fix {
    TEST_NOT_NEEDED(false),
    MC2071,
    MC122477(Util.getOperatingSystem() == Util.OperatingSystem.LINUX),
    MC127970,
    MC140646,
    MC145929,
    MC147766,
    MC151412,
    MC237493;

    private final boolean needed;

    Fix() {
        this(true);
    }

    Fix(boolean needed) {
        this.needed = needed;
    }

    /**
     * Get whether this fix is needed in the current environment.
     */
    public boolean isNeeded() {
        return needed;
    }

    /**
     * Get the title of the fix.
     */
    public TranslatableText getTitle() {
        return new TranslatableText("options.tiefix." + name().toLowerCase() + ".title");
    }

    /**
     * Get the description of the bug.
     */
    public TranslatableText getDescription() {
        return new TranslatableText("options.tiefix." + name().toLowerCase() + ".description");
    }
}
