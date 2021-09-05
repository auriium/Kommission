package xyz.auriium.marketplace.platform;

public interface AppServer {

    void startup();
    void shutdown(boolean userCalled);

    boolean isShutdown();

}
