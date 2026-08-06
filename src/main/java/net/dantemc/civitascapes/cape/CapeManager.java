package net.dantemc.civitascapes.cape;

import net.dantemc.civitascapes.player.PlayerCapeData;
import net.dantemc.civitascapes.player.PlayerCapeRegistry;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class CapeManager {

    public static ResourceLocation getCape(UUID uuid) {

        PlayerCapeData player = PlayerCapeRegistry.get(uuid);

        if (player == null) {
            return null;
        }

        CapeDefinition cape = CapeRegistry.get(player.getActiveCape());

        if (cape == null) {
            return null;
        }

        return ResourceLocation.parse(cape.getLocation()); //TODO: Add support for urls
    }
}
