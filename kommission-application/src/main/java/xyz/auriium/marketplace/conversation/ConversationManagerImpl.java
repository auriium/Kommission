package xyz.auriium.marketplace.conversation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.*;

public class ConversationManagerImpl implements ConversationManager {

    private final Map<Long, Collection<ConversationStack>> stages = new HashMap<>();
    private final static Logger logger = LogManager.getLogger("ConversationBusImpl");

    @SuppressWarnings("unchecked")
    <T> void onEvent(Object event, Long id) {
        logger.error("OnEvent called with user ID: " + id + " and event: " + event);
        var stackCollection = stages.get(id);
        logger.info("after");

        if (stages.get(id) == null) return;

        logger.info("calling removeIf!");

        boolean removed = stackCollection.removeIf(stack -> {
            logger.info("start");

            Queue<ConversationStage<?>> stages = stack.stages();

            if (stages.isEmpty()) {
                logger.warn("Stages is empty, which means queue is done!");
                return true; //cancel
            }

            ConversationStage<T> stage = (ConversationStage<T>) stages.peek();

            if (!stage.event().isInstance(event)) {
                logger.warn("Not an event, so do nothing");
                return false;
            }

            logger.info("is instance");
            ConversationResult result = stage.onAccept((T)event, stack.getData());
            logger.info("completed stage with result: " + result.name());


            switch (result) {
                case CANCEL:
                    logger.info("somehow cancel called?");
                    return true;
                case NEXT:
                    stages.remove();

                    return stages.isEmpty();
                default: return false;
            }
        });

        logger.info(removed + ": elements removed");
    }

    @Override
    public boolean submit(Long senderUUID, Long channelID, StackMaker maker) {

        ConversationStack makedStack = new ConversationStack(maker.identifier(), new ArrayDeque<>(maker.make()));
        //maked is not a real word
        Collection<ConversationStack> stacks;
        if ((stacks = stages.get(senderUUID)) != null) {
            for (ConversationStack stack : stacks) {
                if (stack.getConversationID().equals(maker.identifier())) {
                    return false;
                }
            }
        } else {
            stacks = stages.computeIfAbsent(senderUUID, a -> new HashSet<>());
        }

        stacks.add(makedStack);

        onEvent(new StartEvent(senderUUID, channelID), senderUUID);

        return true;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {

        if (messageCreateEvent.getMessageAuthor().isBotUser()) return;

        onEvent(messageCreateEvent, messageCreateEvent.getMessageAuthor().getId());
    }
}
