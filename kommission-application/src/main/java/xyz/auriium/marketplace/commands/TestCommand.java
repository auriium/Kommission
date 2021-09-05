package xyz.auriium.marketplace.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import xyz.auriium.marketplace.commands.setup.SetupMaker;
import xyz.auriium.marketplace.conversation.ConversationManager;

import java.awt.*;

public class TestCommand implements MessageCreateListener {

    private final ConversationManager bus;
    private final SetupMaker maker;

    private static final Logger logger = LogManager.getLogger("TestCommand");

    public TestCommand(ConversationManager bus, DiscordApi api) {
        this.bus = bus;
        this.maker = new SetupMaker(api);
    }

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if (messageCreateEvent.getMessage().getContent().equalsIgnoreCase("!testCommand")) {
            boolean succ = bus.submit(messageCreateEvent.getMessageAuthor().getId(), messageCreateEvent.getChannel().getId(), maker);

            if (!succ) {
                messageCreateEvent.getChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle("Error").setDescription("You are already in the testCommand interface!"));
            }
        }
    }


}
