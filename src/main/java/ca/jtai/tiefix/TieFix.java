package ca.jtai.tiefix;

import ca.jtai.tiefix.config.Config;
import ca.jtai.tiefix.config.ConfigHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class TieFix implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        config = ConfigHelper.readConfig();
    }

    public static final int MIXIN_PRIORITY = 500;

    private static Config config = null;

    public static Config getConfig() {
        return config;
    }
}
