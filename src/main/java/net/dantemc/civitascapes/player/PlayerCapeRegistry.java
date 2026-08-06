package net.dantemc.civitascapes.player;

import java.util.*;

public class PlayerCapeRegistry {
    private static final Map<UUID, PlayerCapeData> PLAYERS = new HashMap<>();

    public static void register(PlayerCapeData player) {
        PLAYERS.put(player.getUuid(), player);
    }

    public static PlayerCapeData get(UUID uuid) {
        return PLAYERS.get(uuid);
    }

    public static Collection<PlayerCapeData> getAll() {
        return PLAYERS.values();
    }

    public static void clear() {
        PLAYERS.clear();
    }
}
