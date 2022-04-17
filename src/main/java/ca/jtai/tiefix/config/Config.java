package ca.jtai.tiefix.config;

import com.google.gson.annotations.SerializedName;
import net.minecraft.util.Util;

public class Config {
    private int version = 0;

    /**
     * Whether to enable debug and development features. Off by default.
     * <p>
     * This option is not shown in the settings screen. It can only be changed by editing the JSON config file.
     */
    public boolean debug = false;

    /**
     * Enable gameplay fixes in multiplayer
     */
    @SerializedName("mc136249_allowMultiplayer") // Compatibility with old config files
    public boolean gameplayAllowMultiplayer = false;

    public boolean mc2071_fix = true;
    public boolean mc4490_fix = true;
    public boolean mc12829_fix = true;
    public boolean mc53312_fix = true;
    public boolean mc62997_fix = true;
    public boolean mc79545_fix = true;
    public boolean mc89242_fix = true;
    public boolean mc122477_fix = Util.getOperatingSystem() == Util.OperatingSystem.LINUX;
    public String mc122477_keys = "";
    public boolean mc122645_fix = true;
    public boolean mc127970_fix = true;
    public boolean mc136249_fix = true;
    public boolean mc140646_fix = true;
    public boolean mc147766_fix = true;
    public boolean mc145929_fix = true;
    public boolean mc151412_fix = true;
    public boolean mc203401_fix = false;
    public boolean mc233042_fix = true;
    public boolean mc237493_fix = true;

    /**
     * The default configuration. Do not mutate this object.
     */
    public static final Config DEFAULT = new Config();
}
