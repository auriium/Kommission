package xyz.auriium.marketplace.conversation;

import org.javacord.api.event.message.MessageCreateEvent;

public interface MessageStage extends ConversationStage<MessageCreateEvent> {

    @Override
    default Class<MessageCreateEvent> event() {
        return MessageCreateEvent.class;
    }
}
