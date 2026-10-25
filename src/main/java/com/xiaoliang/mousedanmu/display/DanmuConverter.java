package com.xiaoliang.mousedanmu.display;

import com.xiaoliang.bili.live.danmu.User;
import com.xiaoliang.bili.live.danmu.model.*;
import com.xiaoliang.mousedanmu.config.ModConfig;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.net.URI;

public class DanmuConverter {
    final ModConfig config;
    final DanmuColorPicker colorPicker;

    public DanmuConverter(ModConfig config, DanmuColorPicker colorPicker) {
        this.config = config;
        this.colorPicker = colorPicker;
    }

    public MutableText convertUser(@NotNull User user) {
        boolean hasUid = user.uid != null && !user.uid.isBlank() && !"0".equals(user.uid);
        var mutableText = Text.literal(user.name)
                .append(": ")
                .styled(style -> {
                    style = style.withColor(colorPicker.user(user.guardLevel));
                    if (hasUid) {
                        style = style
                                .withClickEvent(new ClickEvent.OpenUrl(URI.create(
                                        "https://space.bilibili.com/%s".formatted(user.uid))))
                                .withHoverEvent(new HoverEvent.ShowText(
                                        Text.translatable("text.mousedanmu-remastered.click-to-user-space")));
                    }
                    return style;
                });

        if (config.danmu.isShowFansMedal && user.fansMedal != null && !user.fansMedal.name.isEmpty()) {
            var fansMedalText = Texts.bracketed(Text.literal(user.fansMedal.name)
                            .append("|")
                            .append(String.valueOf(user.fansMedal.level)))
                    .styled(style -> style.withColor(colorPicker.fans(user.fansMedal.level)));
            mutableText = fansMedalText.append(mutableText);
        }
        return mutableText;
    }

    public MutableText convert(@NotNull Danmu danmu) {
        MutableText text = Text.literal(danmu.body).formatted(Formatting.WHITE);
        if (danmu.user.guardLevel > 0) {
            text = Texts.bracketed(text).styled(style ->
                    style.withColor(colorPicker.user(danmu.user.guardLevel)));
        }
        return convertUser(danmu.user).append(text);

    }

    public MutableText convert(@NotNull Gift gift) {
        var mutableText = Texts.bracketed(Text.literal(gift.name))
                .styled(style -> style.withColor(colorPicker.gift(gift.price)))
                .append(" × ").append(String.valueOf(gift.num));
        if (gift.price > 0) {
            mutableText = mutableText.append(" ￥").append(String.valueOf(gift.price));
        }
        return convertUser(gift.user).append(mutableText);
    }

    public MutableText convert(@NotNull Emoji emoji) {
        var mutableText = Texts.bracketed(Text.literal(emoji.body))
                .styled(style -> style.withColor(config.danmu.emojiColor)
                        .withClickEvent(new ClickEvent.OpenUrl(URI.create(emoji.uri)))
                        .withHoverEvent(new HoverEvent.ShowText(
                                Text.translatable("text.mousedanmu-remastered.click-to-view-emoji"))));
        return convertUser(emoji.user).append(mutableText);
    }

    public MutableText convert(@NotNull SuperChat sc) {
        var mutableText = Texts.bracketed(Text.literal(String.valueOf(sc.price)))
                .append(Text.literal(sc.body))
                .styled(style -> style
                        .withColor(colorPicker.superChat(sc.price))
                        .withBold(true));
        return convertUser(sc.user).append(mutableText);
    }

    public MutableText convert(@NotNull Guard guard) {
        var mutableText = Texts.bracketed(Text.literal(guard.name))
                .styled(style -> style.withColor(colorPicker.user(guard.level)))
                .append(" × ").append(String.valueOf(guard.num)).append(guard.unit);
        if (guard.price > 0) {
            mutableText = mutableText.append(" ￥").append(String.valueOf(guard.price));
        }
        return convertUser(guard.user).append(mutableText);
    }

    public MutableText convert(@NotNull Interactive interactive) {
        var msg = switch (interactive.type) {
            case Interactive.ENTER -> Text.translatable("text.mousedanmu-remastered.entry-live-room");
            case Interactive.FOLLOW -> Text.translatable("text.mousedanmu-remastered.attention-live-room");
            case Interactive.SHARE -> Text.translatable("text.mousedanmu-remastered.share-live-room");
            case Interactive.SPECIAL_FOLLOW -> Text.translatable("text.mousedanmu-remastered.special-attention-live-room");
            case Interactive.MUTUAL_FOLLOW -> Text.translatable("text.mousedanmu-remastered.mutual-attention-live-room");
            case Interactive.LIKE -> interactive.text == null || interactive.text.isBlank()
                    ? Text.translatable("text.mousedanmu-remastered.like-live-room")
                    : Text.literal(interactive.text);
            default -> Text.translatable("text.mousedanmu-remastered.unknown-interactive", interactive.type);
        };
        var mutableText = Texts.bracketed(msg)
                .styled(style -> style.withColor(config.danmu.interactiveColor));
        return convertUser(interactive.user).append(mutableText);
    }
}
