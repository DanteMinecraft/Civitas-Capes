package net.dantemc.civitascapes.player;

import java.util.List;
import java.util.UUID;

public class PlayerLoader {
    //read players.json and fill PlayerCapeRegistry class

    public static void load() {
        PlayerCapeData player = new PlayerCapeData(
                UUID.fromString("2e714d0e-4b93-402c-8ff0-b477d2fa9004"),
                "test",
                List.of("test")
        );

        PlayerCapeRegistry.register(player);
    }
}
