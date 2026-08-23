package com.xiaoliang.mousedanmu.command;

import com.xiaoliang.mousedanmu.MouseDanmuRemastered;
import com.xiaoliang.mousedanmu.command.argument.RoomIdArgumentType;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.argument.MessageArgumentType;

public class MouseDanmuCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        var literalCommandNode = dispatcher.register(ClientCommandManager.literal("mousedanmu-remastered")
                .then(ClientCommandManager.literal("connect")
                        .then(ClientCommandManager.argument("roomId", RoomIdArgumentType.roomId())
                                .executes(context -> connect(context.getArgument("roomId", Integer.class)))))
                .then(ClientCommandManager.literal("close")
                        .executes(context -> close()))
                .then(ClientCommandManager.literal("status")
                        .executes(context -> status()))
                .then(ClientCommandManager.literal("test")
                        .then(ClientCommandManager.argument("delay", IntegerArgumentType.integer(0, 10000))
                                .executes(context -> test(context.getArgument("delay", Integer.class))))
                        .executes(context -> test(500)))
                .then(ClientCommandManager.argument("message", MessageArgumentType.message())
                        .executes(context -> send(context.getArgument("message", MessageArgumentType.MessageFormat.class).contents()))));

        dispatcher.register(ClientCommandManager.literal("dm").redirect(literalCommandNode));
    }

    public static int connect(int roomId) {
        MouseDanmuRemastered.client.close();
        MouseDanmuRemastered.client.connect(roomId);
        MouseDanmuRemastered.save();
        return 1;
    }

    public static int close() {
        MouseDanmuRemastered.client.close();
        return 1;
    }

    public static int status() {
        MouseDanmuRemastered.client.status();
        return 1;
    }

    public static int test(int delay) {
        new Thread(() -> {
            try {
                MouseDanmuRemastered.client.test(delay);
            } catch (InterruptedException ignored) {
            }
        }).start();
        return 1;
    }

    public static int send(String message) {
        new Thread(() -> MouseDanmuRemastered.client.sendDanmu(message)).start();
        return 1;
    }
}
