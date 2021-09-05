package xyz.auriium.marketplace.platform;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommonAppAppServer implements AppServer {

    private final ApplicationInitializationDetails details;
    private final Platform platform;

    private final AtomicBoolean shutdownInProgress = new AtomicBoolean(false);
    private volatile boolean shutdown = false;
    private volatile Application application;

    public CommonAppAppServer(ApplicationInitializationDetails details, Platform platform) {
        this.details = details;
        this.platform = platform;
    }

    @Override
    public void startup() {
        long startTime = System.currentTimeMillis();

        application = details.createApplication(platform, this);
        application.onStart();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown(false)));

        double bootTime = (System.currentTimeMillis() - startTime) / 1000d;
        platform.startupLogger().info("Done ({}s)!", new DecimalFormat("#.##").format(bootTime));
    }

    @Override
    public void shutdown(boolean userCalled) {
        //should link to application
        if (!shutdownInProgress.compareAndSet(false, true)) {
            return; //shutdown is already in progress
        }

        Runnable shutdownExec = () -> {

            application.onStop();

            shutdown = true;

            if (userCalled) {
                AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                    System.exit(0);
                    return null;
                });
            }
        };



        if (userCalled) {
            new Thread(shutdownExec).start();
        } else {
            shutdownExec.run();
        }
    }

    @Override
    public boolean isShutdown() {
        return shutdown;
    }


}
