package xyz.auriium.marketplace.console;

import net.minecrell.terminalconsole.SimpleTerminalConsole;
import xyz.auriium.marketplace.platform.AppServer;

public class ChatbayConsole extends SimpleTerminalConsole {

    private final AppServer appServer;

    public ChatbayConsole(AppServer appServer) {
        this.appServer = appServer;
    }

    @Override
    protected boolean isRunning() {
        return !appServer.isShutdown();
    }

    @Override
    protected void runCommand(String s) {

    }

    @Override
    protected void shutdown() {
        appServer.shutdown(true);
    }
}
