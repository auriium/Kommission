package xyz.auriium.marketplace.conversation;

public interface StartStage extends ConversationStage<StartEvent> {

    @Override
    default Class<StartEvent> event() {
        return StartEvent.class;
    }
}
