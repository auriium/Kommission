package xyz.auriium.marketplace.storage.keys.market;

import xyz.auriium.marketplace.storage.KeyedProvider;
import xyz.auriium.marketplace.storage.Transaction;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

import static xyz.auriium.marketplace.schema

public class MarketProvider implements KeyedProvider<Long, Market> {

    @Override
    public Market provide(Long uuid, Transaction transaction) {

        var result = transaction.ctx()
                .select(field("complete", Boolean.class))
                .from(table("mm_servers"))
                .where(field("server_id").eq(uuid))
                .fetchOne();

        if (result != null) {
            return new Market(uuid, result.value1());
        }

        transaction.ctx()
                .insertInto(table("mm_servers"))
                .values(field("server_id",uuid), field("complete", false))
                .execute();

        return new Market(uuid, false);
    }
}
