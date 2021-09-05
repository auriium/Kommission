package xyz.auriium.marketplace.storage.keys.market;

import xyz.auriium.marketplace.storage.Transaction;

public class Market {

    private final long id;

    private volatile boolean complete;

    public Market(long id, boolean complete) {
        this.id = id;
        this.complete = complete;
    }

    public boolean completeCurrent() {
        return complete;
    }

    public boolean complete(Transaction transaction) {
        return false; //TODO
    }
}
