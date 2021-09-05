package xyz.auriium.marketplace.storage;

import org.jooq.DSLContext;

public interface Transaction {

    DSLContext ctx();

}
