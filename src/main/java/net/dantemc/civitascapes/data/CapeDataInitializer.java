package net.dantemc.civitascapes.data;

import net.neoforged.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CapeDataInitializer {

    private static final String DEFAULT_CAPES = """
            {
              "capes": [
                {
                  "id": "default",
                  "displayName": "Default Cape",
                  "type": "RESOURCEPACK",
                  "location": "civitascapes:textures/capes/default.png"
                }
              ]
            }
            """;

    private static final String DEFAULT_PLAYERS = """
            {
              "players": []
            }
            """;

    private static final Path CONFIG_FOLDER = FMLPaths.CONFIGDIR.get();

    public static final Path DATA_FOLDER = CONFIG_FOLDER.resolve("civitascapes");
    public static final Path CAPES_FILE = DATA_FOLDER.resolve("capes.json");
    public static final Path PLAYERS_FILE = DATA_FOLDER.resolve("players.json");

    public static void initialize() {
        try {

            Files.createDirectories(DATA_FOLDER);

            if (Files.notExists(CAPES_FILE)) {
                Files.writeString(CAPES_FILE, DEFAULT_CAPES);
                System.out.println("[Civitas Capes] Created capes.json");
            }

            if (Files.notExists(PLAYERS_FILE)) {
                Files.writeString(PLAYERS_FILE, DEFAULT_PLAYERS);
                System.out.println("[Civitas Capes] Created players.json");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Civitas Capes data files.", e);
        }
    }

}
