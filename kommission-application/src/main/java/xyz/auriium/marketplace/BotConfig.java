package xyz.auriium.marketplace;

import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface BotConfig {

    @ConfDefault.DefaultString("null")
    @ConfKey("login.token")
    String token();

}
