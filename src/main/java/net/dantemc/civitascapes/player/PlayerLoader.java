package net.dantemc.civitascapes.player;

import com.google.gson.Gson;
import net.dantemc.civitascapes.data.CapeDataInitializer;
import net.dantemc.civitascapes.player.wrapper.PlayerFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PlayerLoader {

    private static final Gson GSON = new Gson();

    public static void load() {

        //Read json
        try (Reader reader = new FileReader(CapeDataInitializer.PLAYERS_FILE.toFile())) {

            PlayerFile file = GSON.fromJson(reader, PlayerFile.class);
            PlayerCapeRegistry.clear();

            for (PlayerCapeData player : file.getPlayers()) {
                PlayerCapeRegistry.register(player);
            }

            System.out.println("Loaded " + file.getPlayers().size() + " players.");

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
