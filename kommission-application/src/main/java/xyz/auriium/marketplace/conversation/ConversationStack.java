package xyz.auriium.marketplace.conversation;

import java.util.*;

public class ConversationStack {

    private final String conversationID;
    private final Queue<ConversationStage<?>> stages;
    private final ConversationData data = new ConversationData();

    public ConversationStack(String conversationID, Queue<ConversationStage<?>> stages) {
        this.conversationID = conversationID;
        this.stages = stages;
    }

    public Queue<ConversationStage<?>> stages() {
        return stages;
    }

    public ConversationData getData() {
        return data;
    }

    public String getConversationID() {
        return conversationID;
    }
}
