package xyz.auriium.marketplace;

import xyz.auriium.marketplace.platform.Application;
import xyz.auriium.marketplace.platform.ApplicationInitializationDetails;
import xyz.auriium.marketplace.platform.Platform;
import xyz.auriium.marketplace.platform.AppServer;

public class ChatbayDetails implements ApplicationInitializationDetails {
    @Override
    public Application createApplication(Platform platform, AppServer appServer) {
        return new ChatbayApp(platform, appServer);
    }

    @Override
    public String applicationName() {
        return "chatbay";
    }
}
