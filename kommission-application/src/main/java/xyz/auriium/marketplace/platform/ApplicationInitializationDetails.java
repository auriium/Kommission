package xyz.auriium.marketplace.platform;

public interface ApplicationInitializationDetails {

    Application createApplication(Platform platform, AppServer appServer);
    String applicationName();

}
