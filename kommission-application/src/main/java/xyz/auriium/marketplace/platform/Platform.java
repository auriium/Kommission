package xyz.auriium.marketplace.platform;

import org.apache.logging.log4j.Logger;
import xyz.auriium.marketplace.event.EventBus;

public interface Platform {

    Logger logger();
    Logger startupLogger();
    EventBus bus();

}
