package net.dantemc.civitascapes.player;

import java.util.List;
import java.util.UUID;

public class PlayerCapeData {
    UUID uuid;
    String activeCape;

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

    public List<String> getUnlockedCapes() {
        return unlockedCapes;
    }

    public void setUnlockedCapes(List<String> unlockedCapes) {
        this.unlockedCapes = unlockedCapes;
    }

    List<String> unlockedCapes;
}
