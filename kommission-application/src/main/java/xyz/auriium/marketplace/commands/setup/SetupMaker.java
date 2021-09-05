package xyz.auriium.marketplace.commands.setup;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import xyz.auriium.marketplace.conversation.*;

import java.util.List;

public class SetupMaker implements StackMaker {

    private final DiscordApi api;

    public SetupMaker(DiscordApi api) {
        this.api = api;
    }

    @Override
    public List<ConversationStage<?>> make() {
        return List.of(new SetupStage1(api), new SetupStage2(), new SetupStageFinal());
    }

    @Override
    public String identifier() {
        return "setup";
    }

    static class SetupStage1 implements StartStage {

        private final DiscordApi api;

        public SetupStage1(DiscordApi api) {
            this.api = api;
        }

        @Override
        public ConversationResult onAccept(StartEvent event, ConversationData data) {
            api.getTextChannelById(event.getChannelID()).orElseThrow().sendMessage(new EmbedBuilder().setTitle("Message 1").setDescription("What is your favorite color?"));

            return ConversationResult.NEXT;
        }
    }

    static class SetupStage2 implements MessageStage {
        @Override
        public ConversationResult onAccept(MessageCreateEvent event, ConversationData data) {

            String content1 = event.getMessage().getContent();

            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Message 1")
                    .setDescription("You answered: " + content1));

            data.put("content1", content1);

            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Message 2")
                    .setDescription("What is your favorite cow!"));

            return ConversationResult.NEXT;
        }
    }

    static class SetupStageFinal implements MessageStage {
        @Override
        public ConversationResult onAccept(MessageCreateEvent event, ConversationData data) {


            String content2 = event.getMessage().getContent();

            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Message 2")
                    .setDescription("You answered: " + content2));

            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Congrats! Your final answer is: ")
                    .setDescription(String.format("Color: %s, Cow: %s", data.get("content1", String.class), content2)));

            return ConversationResult.NEXT;
        }
    }
}
