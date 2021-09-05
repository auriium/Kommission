package xyz.auriium.marketplace.storage;

public interface KeyedProvider<K,T> {

    T provide(K uuid, Transaction transaction);

}
