package ca.jtai.tiefix.config;

import net.minecraft.util.Util;

public class Config {
    private int version = 0;

    public boolean mc2071_fix = true;
    public boolean mc122477_fix = Util.getOperatingSystem() == Util.OperatingSystem.LINUX;
    public String mc122477_keys = "";
    public boolean mc127970_fix = true;
    public boolean mc140646_fix = true;
    public boolean mc147766_fix = true;
    public boolean mc145929_fix = true;
    public boolean mc151412_fix = true;
    public boolean mc233042_fix = true;
    public boolean mc237493_fix = true;

    /**
     * The default configuration. Do not mutate this object.
     */
    public static final Config DEFAULT = new Config();
}
