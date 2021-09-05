package xyz.auriium.marketplace.conversation;

public interface ConversationStage<E> {

    ConversationResult onAccept(E event, ConversationData data);

    Class<E> event();

}
