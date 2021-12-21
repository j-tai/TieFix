package ca.jtai.tiefix.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class ConfigHelper {
    private static Path configPath = FabricLoader.getInstance().getConfigDir().resolve("tiefix.json");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Read the configuration from disk.
     */
    public static Config readConfig() {
        try {
            var contents = Files.readString(configPath);
            // Get the version of the config
            var version = gson.fromJson(contents, VersionChecker.class);
            if (version != null) {
                // Figure out which class to deserialize as
                if (version.version != 0) {
                    throw new IllegalArgumentException("Unknown config version " + version.version);
                }
                // Deserialize using that class
                return gson.fromJson(contents, Config.class);
            }
        } catch (FileNotFoundException | NoSuchFileException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Something didn't go right -- create a new config and save it
        var config = new Config();
        writeConfig(config);
        return config;
    }

    private static class VersionChecker {
        public int version = 0;
    }

    /**
     * Write the configuration to disk.
     */
    public static void writeConfig(Config config) {
        // Create the config directory if it doesn't exist
        try {
            Files.createDirectories(configPath.getParent());
        } catch (IOException ignored) {
        }
        // Save the config
        try (var writer = new FileWriter(configPath.toFile())) {
            gson.toJson(config, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
