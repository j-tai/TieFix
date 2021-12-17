package ca.jtai.tiefix.config;

import ca.jtai.tiefix.Fix;

import java.util.HashMap;

public class Config implements ConfigVersion {
    private int version = 1;

    private HashMap<String, Boolean> fixToggles = new HashMap<>();
    public String mc122477_keys = "";

    public boolean isEnabled(Fix fix) {
        if (!fix.isNeeded()) {
            return false;
        }
        return fixToggles.getOrDefault(fix.name(), true);
    }

    public void setEnabled(Fix fix, boolean enabled) {
        fixToggles.put(fix.name(), enabled);
    }

    @Override
    public ConfigVersion upgrade() {
        throw new UnsupportedOperationException();
    }
}
