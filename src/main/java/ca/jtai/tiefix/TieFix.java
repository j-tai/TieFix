package ca.jtai.tiefix;

import ca.jtai.tiefix.config.Config;
import ca.jtai.tiefix.config.ConfigHelper;
import ca.jtai.tiefix.fixes.mc89242.Sizemap;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.commons.io.IOUtils;

@Environment(EnvType.CLIENT)
public class TieFix implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Sizemap loader
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(
            new SimpleSynchronousResourceReloadListener() {
                @Override
                public Identifier getFabricId() {
                    return new Identifier("tiefix", "sizemap");
                }

                @Override
                public void reload(ResourceManager manager) {
                    var id = new Identifier("tiefix", "sizemap.bin");
                    try (var stream = manager.getResource(id).get().getInputStream()) {
                        sizemap = new Sizemap(IOUtils.toByteArray(stream));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
    }

    public static final int MIXIN_PRIORITY = 500;

    private static Config config = null;
    private static Sizemap sizemap = null;

    public static Config getConfig() {
        // We can't initialize this in onInitializeClient, because the config is
        // needed before the client is initialized.
        if (config == null) {
            config = ConfigHelper.readConfig();
        }
        return config;
    }

    public static Sizemap getSizemap() {
        return sizemap;
    }
}
