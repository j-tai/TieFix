package ca.jtai.tiefix.config;

import ca.jtai.tiefix.Fix;

public class ConfigV0 implements ConfigVersion {
    public boolean mc2071_fix = true;
    public boolean mc122477_fix = true;
    public String mc122477_keys = "";
    public boolean mc127970_fix = true;
    public boolean mc140646_fix = true;
    public boolean mc147766_fix = true;
    public boolean mc145929_fix = true;
    public boolean mc151412_fix = true;
    public boolean mc237493_fix = true;

    @Override
    public Config upgrade() {
        var config = new Config();
        config.setEnabled(Fix.MC2071, mc2071_fix);
        config.setEnabled(Fix.MC122477, mc122477_fix);
        config.setEnabled(Fix.MC127970, mc127970_fix);
        config.setEnabled(Fix.MC140646, mc140646_fix);
        config.setEnabled(Fix.MC147766, mc147766_fix);
        config.setEnabled(Fix.MC145929, mc145929_fix);
        config.setEnabled(Fix.MC151412, mc151412_fix);
        config.setEnabled(Fix.MC237493, mc237493_fix);
        config.mc122477_keys = mc122477_keys;
        return config;
    }
}
