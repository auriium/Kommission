package xyz.auriium.marketplace.conversation;

import java.util.List;

public interface StackMaker {

    List<ConversationStage<?>> make();
    String identifier();
}
