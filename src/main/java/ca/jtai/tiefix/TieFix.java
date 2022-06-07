package ca.jtai.tiefix;

import ca.jtai.tiefix.config.Config;
import ca.jtai.tiefix.config.ConfigHelper;
import ca.jtai.tiefix.fixes.mc89242.Sizemap;
import com.google.gson.Gson;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;

@Environment(EnvType.CLIENT)
public class TieFix implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        config = ConfigHelper.readConfig();

        // Sizemap loader
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(
            new SimpleSynchronousResourceReloadListener() {
                @Override
                public Identifier getFabricId() {
                    return new Identifier("tiefix", "sizemap");
                }

                @Override
                public void reload(ResourceManager manager) {
                    var id = new Identifier("tiefix", "sizemap.json");
                    try (var reader = new InputStreamReader(manager.getResource(id).get().getInputStream())) {
                        var gson = new Gson();
                        sizemap = gson.fromJson(reader, Sizemap.class);
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
        return config;
    }

    public static Sizemap getSizemap() {
        return sizemap;
    }
}
