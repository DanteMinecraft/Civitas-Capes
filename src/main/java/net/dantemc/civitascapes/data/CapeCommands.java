package net.dantemc.civitascapes.data;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.dantemc.civitascapes.CivitasCapes;
import net.dantemc.civitascapes.cape.CapeDefinition;
import net.dantemc.civitascapes.cape.CapeLoader;
import net.dantemc.civitascapes.cape.CapeRegistry;
import net.dantemc.civitascapes.player.PlayerCapeData;
import net.dantemc.civitascapes.player.PlayerCapeRegistry;
import net.dantemc.civitascapes.player.PlayerLoader;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = CivitasCapes.MODID)
public class CapeCommands {

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {

        event.getDispatcher().register(
                Commands.literal("civitascapes")

                        // /civitascapes reload
                        .then(Commands.literal("reload")
                                .requires(source -> source.hasPermission(2))
                                .executes(context -> {

                                    CapeLoader.load();
                                    PlayerLoader.load();

                                    context.getSource().sendSuccess(
                                            () -> Component.literal("Reloaded Civitas Capes"),
                                            true
                                    );

                                    return 1;
                                })
                        )

                        // /civitascapes active <player>
                        .then(Commands.literal("active")
                                .then(Commands.argument("player", EntityArgument.player())
                                        .executes(context -> {

                                            ServerPlayer player = EntityArgument.getPlayer(context, "player");

                                            PlayerCapeData playerData = PlayerCapeRegistry.get(player.getUUID());

                                            if (playerData == null) {
                                                context.getSource().sendFailure(
                                                        Component.literal("That player has no cape data")
                                                );
                                                return 0;
                                            }

                                            CapeDefinition cape = CapeRegistry.get(playerData.getActiveCape());

                                            if (cape == null) {
                                                context.getSource().sendFailure(
                                                        Component.literal("The player's active cape does not exist")
                                                );
                                                return 0;
                                            }

                                            context.getSource().sendSuccess(
                                                    () -> Component.literal(player + "'s active cape: " + cape.getDisplayName()),
                                                    false
                                            );

                                            return 1;
                                        })
                                )
                        )

                        // /civitascapes unlocked <player>
                        .then(Commands.literal("unlocked")
                                .then(Commands.argument("player", EntityArgument.player())
                                        .executes(context -> {

                                            ServerPlayer player = EntityArgument.getPlayer(context, "player");

                                            PlayerCapeData playerData = PlayerCapeRegistry.get(player.getUUID());

                                            if (playerData == null) {
                                                context.getSource().sendFailure(
                                                        Component.literal("That player has no cape data")
                                                );
                                                return 0;
                                            }

                                            context.getSource().sendSuccess(
                                                    () -> Component.literal(player + "'s unlocked capes:"),
                                                    false
                                            );

                                            for (String capeId : playerData.getUnlockedCapes()) {

                                                CapeDefinition cape = CapeRegistry.get(capeId);

                                                if (cape != null) {
                                                    context.getSource().sendSuccess(
                                                            () -> Component.literal("- " + cape.getDisplayName()),
                                                            false
                                                    );
                                                }
                                            }

                                            return 1;
                                        })
                                )
                        )
                        // /civitascapes set <player> <cape>
                        .then(Commands.literal("set")
                                .requires(source -> source.hasPermission(2))
                                .then(
                                        Commands.argument("player", EntityArgument.player())
                                                .then(
                                                        Commands.argument("cape", StringArgumentType.word())
                                                                .suggests((context, builder) -> {

                                                                    for (CapeDefinition cape : CapeRegistry.getAll()) {
                                                                        builder.suggest(cape.getId());
                                                                    }

                                                                    return builder.buildFuture();
                                                                })
                                                                .executes(context -> {

                                                                    ServerPlayer player = EntityArgument.getPlayer(context, "player");
                                                                    String capeId = StringArgumentType.getString(context, "cape");

                                                                    PlayerCapeData playerData = PlayerCapeRegistry.get(player.getUUID());

                                                                    if (playerData == null) {
                                                                        context.getSource().sendFailure(
                                                                                Component.literal("That player has no cape data")
                                                                        );
                                                                        return 0;
                                                                    }

                                                                    CapeDefinition cape = CapeRegistry.get(capeId);

                                                                    if (cape == null) {
                                                                        context.getSource().sendFailure(
                                                                                Component.literal("That cape does not exist")
                                                                        );
                                                                        return 0;
                                                                    }

                                                                    if (!playerData.getUnlockedCapes().contains(capeId)) {
                                                                        context.getSource().sendFailure(
                                                                                Component.literal("That player has not unlocked this cape")
                                                                        );
                                                                        return 0;
                                                                    }

                                                                    playerData.setActiveCape(capeId);

                                                                    context.getSource().sendSuccess(
                                                                            () -> Component.literal(
                                                                                    "Set " + player.getName().getString() +
                                                                                            "'s active cape to " + cape.getDisplayName()
                                                                            ),
                                                                            true
                                                                    );

                                                                    return 1;
                                                                })
                                                )
                                )
                        )
                        // /civitascapes clear <player>
                        .then(Commands.literal("clear")
                                .requires(source -> source.hasPermission(2))
                                .then(
                                        Commands.argument("player", EntityArgument.player())
                                                .executes(context -> {

                                                    ServerPlayer player = EntityArgument.getPlayer(context, "player");

                                                    PlayerCapeData playerData = PlayerCapeRegistry.get(player.getUUID());

                                                    if (playerData == null) {
                                                        context.getSource().sendFailure(
                                                                Component.literal("That player has no cape data")
                                                        );
                                                        return 0;
                                                    }

                                                    playerData.setActiveCape(null);

                                                    context.getSource().sendSuccess(
                                                            () -> Component.literal(
                                                                    "Cleared " + player.getName().getString() + "'s active cape"),
                                                            true
                                                    );

                                                    return 1;
                                                })
                                ))
        );
    }
}