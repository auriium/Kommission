package xyz.auriium.marketplace.storage;

import java.util.concurrent.CompletableFuture;

public interface DataCenter {

    CompletableFuture<?> run(DataRunnable runnable);
    <T> CompletableFuture<T> runSupply(DataSupplier<T> supplier);

    interface DataRunnable {
        void consume(Transaction transaction);
    }

    interface DataSupplier<T> {
        T supply(Transaction transaction);
    }
}
