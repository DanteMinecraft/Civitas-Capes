package net.dantemc.civitascapes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = CivitasCapes.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = CivitasCapes.MODID, value = Dist.CLIENT)
public class CivitasCapesClient {
    public CivitasCapesClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        CivitasCapes.LOGGER.info("HELLO FROM CLIENT SETUP");
        CivitasCapes.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void addPlayerLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model model : event.getSkins()) {
            PlayerRenderer renderer = event.getSkin(model);

            if (renderer != null) {
                renderer.addLayer(new CivitasCapeLayer(renderer));
            }
        }
    }
}
