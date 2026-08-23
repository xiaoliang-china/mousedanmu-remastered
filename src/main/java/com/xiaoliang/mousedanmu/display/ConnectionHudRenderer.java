package com.xiaoliang.mousedanmu.display;

import com.xiaoliang.mousedanmu.MouseDanmuRemastered;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;

/**
 * 在屏幕右下角显示直播连接状态：
 * 已连接时显示绿色“已连接：房间号”，断开后显示红色“已断开”。
 */
public class ConnectionHudRenderer {
    private static final int GREEN = 0xFF55FF55;
    private static final int RED = 0xFFFF5555;

    public static void register() {
        HudRenderCallback.EVENT.register(ConnectionHudRenderer::render);
    }

    private static void render(DrawContext drawContext, RenderTickCounter tickCounter) {
        var danmu = MouseDanmuRemastered.client;
        MinecraftClient mc = MinecraftClient.getInstance();
        // 尚未连接过任何直播间时不显示
        if (danmu == null || mc.player == null || danmu.getRoomId() == 0) {
            return;
        }

        boolean connected = danmu.isConnected();
        Text text = connected
                ? Text.translatable("text.mousedanmu-remastered.live-room-connected", danmu.getRoomId())
                : Text.translatable("text.mousedanmu-remastered.live-room-closed");
        int color = connected ? GREEN : RED;

        int x = drawContext.getScaledWindowWidth() - mc.textRenderer.getWidth(text) - 2;
        int y = drawContext.getScaledWindowHeight() - mc.textRenderer.fontHeight - 2;
        drawContext.drawTextWithShadow(mc.textRenderer, text, x, y, color);
    }
}
