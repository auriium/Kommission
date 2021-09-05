package xyz.auriium.marketplace.listener;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.listener.server.ServerJoinListener;

public class FirstJoinListener implements ServerJoinListener {
    @Override
    public void onServerJoin(ServerJoinEvent serverJoinEvent) {
        serverJoinEvent.getServer().getSystemChannel().ifPresent(channel -> {
            channel.sendMessage(
                    new EmbedBuilder()
                            .setTitle("Thanks for adding MarketplaceMessenger!")
                            .setDescription("TODO")
                            .setDescription("blah blah")
            );
        });
    }
}
