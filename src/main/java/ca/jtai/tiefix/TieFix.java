package ca.jtai.tiefix;

import ca.jtai.tiefix.config.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

@Environment(EnvType.CLIENT)
public class TieFix implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        loadConfig();
    }

    public static final int MIXIN_PRIORITY = 2000;

    // Configuration

    private static Path configPath = FabricLoader.getInstance().getConfigDir().resolve("tiefix.json");
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private static Config config = null;

    public static Config getConfig() {
        return config;
    }

    public static void loadConfig() {
        config = readConfig();
        if (config == null)
            config = new Config();
        writeConfig(config);
    }

    private static Config readConfig() {
        try (FileReader reader = new FileReader(configPath.toFile())) {
            return gson.fromJson(reader, Config.class);
        } catch (FileNotFoundException e) {
            return null;
        } catch (JsonParseException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeConfig(Config config) {
        try (FileWriter writer = new FileWriter(configPath.toFile())) {
            gson.toJson(config, writer);
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
        }
    }
}
