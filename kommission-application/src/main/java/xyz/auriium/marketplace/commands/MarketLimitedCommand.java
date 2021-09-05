package xyz.auriium.marketplace.commands;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;
import xyz.auriium.marketplace.storage.DataCenter;
import xyz.auriium.marketplace.storage.ProviderMap;
import xyz.auriium.marketplace.storage.Transaction;
import xyz.auriium.marketplace.storage.keys.market.Market;

import java.awt.*;

public abstract class MarketLimitedCommand implements SlashCommandCreateListener {

    protected final ProviderMap<Long, Market> marketProvider;
    protected final DataCenter dataCenter;

    public MarketLimitedCommand(ProviderMap<Long, Market> marketProvider, DataCenter dataCenter) {
        this.marketProvider = marketProvider;
        this.dataCenter = dataCenter;
    }

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent slashCommandCreateEvent) {
        SlashCommandInteraction interaction = slashCommandCreateEvent.getSlashCommandInteraction();
        long id = interaction.getServer().orElseThrow().getId();

        dataCenter.run(tx -> {

            Market market = marketProvider.get(id, tx);

            if (!market.complete(tx)) {
                interaction.getChannel().orElseThrow().sendMessage(
                        new EmbedBuilder().setColor(Color.RED)
                                .setTitle("Error | Market setup incomplete!")
                                .setDescription("You must complete market setup before you are able to run this command!")
                );
            } else {
                onCommand(tx, market, interaction);
            }

        }).whenComplete((a,b) -> {
            if (b != null) {
                throw new IllegalStateException("aaa");
            }
        });
    }

    abstract void onCommand(Transaction transaction, Market market, SlashCommandInteraction interaction);
}
