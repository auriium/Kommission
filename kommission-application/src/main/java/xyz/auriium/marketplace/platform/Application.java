package xyz.auriium.marketplace.platform;

/**
 * Logic for handling application state of being
 */
public interface Application {

    /**
     * Method called on start
     */
    void onStart();

    /**
     * Method called on stop
     */
    void onStop();

}
