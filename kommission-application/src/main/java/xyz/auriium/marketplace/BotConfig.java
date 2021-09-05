package xyz.auriium.marketplace;

import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface BotConfig {

    @ConfDefault.DefaultString("ODY2MTEyNDU4Njc0NDA1Mzk2.YPN0FQ.7ah7tkzM5Vh2o29EIclLigNj-Wo")
    @ConfKey("login.token")
    String token();

}
