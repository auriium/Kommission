package xyz.auriium.marketplace.storage;

import java.util.Map;

public class ProviderMap<K,T> {

    private final KeyedProvider<K,T> provider;
    private final Map<K,T> map;

    public ProviderMap(KeyedProvider<K, T> provider, Map<K, T> map) {
        this.provider = provider;
        this.map = map;
    }

    public T get(K key, Transaction transaction) {
        T val;
        if ((val = map.get(key)) != null) return val;
        return map.put(key, provider.provide(key, transaction));
    }
}
