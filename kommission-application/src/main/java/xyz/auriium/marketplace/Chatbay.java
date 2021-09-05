package xyz.auriium.marketplace;

import xyz.auriium.marketplace.platform.Bootstrap;

/**
 * Launcher for chatbay application
 */
public class Chatbay {

    public static void main(String[] args) {
        Bootstrap.start(new ChatbayDetails());
    }

}
