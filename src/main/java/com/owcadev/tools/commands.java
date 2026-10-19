package com.owcadev.tools;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.owcadev.tools.utils.config;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;

import java.io.IOException;
import java.lang.reflect.Field;

import static com.owcadev.tools.SheepTools.LOGGER;
import static com.owcadev.tools.SheepTools.SheepConfig;

public class commands {

    public void InitialCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("sheepconfig").then(
                    Commands.argument("option", StringArgumentType.word())
                            .suggests((context, builder) -> {
                                for (Field field : config.class.getFields()) {
                                    builder.suggest(field.getName());
                                }
                                return builder.buildFuture();
                            })
                            .then(Commands.argument("value", StringArgumentType.greedyString())
                                    .executes(commands::executeCommandWithSuggestions)
                            )
            ).requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR)));
        });
    }

    private static int executeCommandWithSuggestions(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String option = StringArgumentType.getString(context, "option");
        String value = StringArgumentType.getString(context, "value");

        try {
            SheepConfig.ChangeConfig(option, value);
        } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
            e.printStackTrace();
            throw new CommandSyntaxException(null, () -> "Failed Save Config");
        }

        context.getSource().sendSuccess(() -> Component.literal("Succesfully Updated Config %s".formatted(option)), false);
        return 1;
    }
}
