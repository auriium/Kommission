package xyz.auriium.marketplace.conversation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ConversationData {

    private final Map<String, Object> map = new HashMap<>();

    public void put(String string, Object object) {
        this.map.put(string, object);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> get(String key, Class<T> clazz) {
        Object val = map.get(key);

        if (!clazz.isInstance(val)) return Optional.empty();

        return Optional.of((T) val);
    }
}
