package net.dantemc.civitascapes.player.wrapper;

import net.dantemc.civitascapes.player.PlayerCapeData;

import java.util.List;

public class PlayerFile {
    private List<PlayerCapeData> players;

    public List<PlayerCapeData> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerCapeData> players) {
        this.players = players;
    }
}
