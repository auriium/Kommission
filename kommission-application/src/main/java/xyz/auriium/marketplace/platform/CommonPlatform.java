package xyz.auriium.marketplace.platform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.auriium.marketplace.event.CommonEventBus;
import xyz.auriium.marketplace.event.EventBus;

public class CommonPlatform implements Platform {

    private final Logger logger;
    private final Logger startup;
    private final EventBus bus;

    public CommonPlatform(String name) {
        this.logger = LogManager.getLogger(name);
        this.startup = LogManager.getLogger(name + "-platform");
        this.bus = new CommonEventBus();
    }

    @Override
    public Logger logger() {
        return logger;
    }

    @Override
    public Logger startupLogger() {
        return startup;
    }

    @Override
    public EventBus bus() {
        return bus;
    }
}
