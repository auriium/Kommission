package xyz.auriium.marketplace.conversation;

public class StartEvent {

    private final long userID;
    private final long channelID;

    public StartEvent(long userID, long channelID) {
        this.userID = userID;
        this.channelID = channelID;
    }

    public long getUserID() {
        return userID;
    }

    public long getChannelID() {
        return channelID;
    }
}
