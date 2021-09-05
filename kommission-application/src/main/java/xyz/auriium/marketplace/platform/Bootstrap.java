package xyz.auriium.marketplace.platform;

public class Bootstrap {

    public static void start(ApplicationInitializationDetails details) {
        Platform platform = new CommonPlatform(details.applicationName());
        AppServer appServer = new CommonAppAppServer(details, platform);

        appServer.startup();
    }

}
