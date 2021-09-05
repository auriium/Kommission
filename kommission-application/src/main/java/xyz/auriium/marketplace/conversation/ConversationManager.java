package xyz.auriium.marketplace.conversation;

import org.javacord.api.listener.message.MessageCreateListener;

public interface ConversationManager extends MessageCreateListener {

    /**
     * Submits and begins a conversation stack
     * @param senderUUID uuid of the user
     * @param channelUUID uuid of the channel
     * @param stack the stack
     * @return true if successful, false if the user is already in a conversation
     */
    boolean submit(Long senderUUID, Long channelUUID, StackMaker stack);


}
