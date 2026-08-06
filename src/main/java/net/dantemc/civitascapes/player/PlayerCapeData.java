package net.dantemc.civitascapes.player;

import java.util.List;
import java.util.UUID;

public class PlayerCapeData {
    private UUID uuid;
    private String activeCape;
    private List<String> unlockedCapes;

    public PlayerCapeData(UUID uuid, String activeCape, List<String> unlockedCapes) {
        this.uuid = uuid;
        this.activeCape = activeCape;
        this.unlockedCapes = unlockedCapes;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getActiveCape() {
        return activeCape;
    }

    public void setActiveCape(String activeCape) {
        this.activeCape = activeCape;
    }
}
