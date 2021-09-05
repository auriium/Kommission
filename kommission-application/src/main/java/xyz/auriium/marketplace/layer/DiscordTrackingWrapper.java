package xyz.auriium.marketplace.layer;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public interface DiscordTrackingWrapper {

    void sendMessage(TextChannel textChannel, EmbedBuilder builder);

}
