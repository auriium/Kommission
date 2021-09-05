package xyz.auriium.marketplace;

import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;
import xyz.auriium.marketplace.commands.TestCommand;
import xyz.auriium.marketplace.console.ChatbayConsole;
import xyz.auriium.marketplace.conversation.ConversationManager;
import xyz.auriium.marketplace.conversation.ConversationManagerImpl;
import xyz.auriium.marketplace.platform.Application;
import xyz.auriium.marketplace.platform.Platform;
import xyz.auriium.marketplace.platform.AppServer;
import xyz.auriium.marketplace.storage.StorageManager;

import java.io.IOException;
import java.nio.file.Paths;

public class ChatbayApp implements Application {

    private final Platform platform;
    private final AppServer appServer;
    private final Logger logger;

    private volatile StorageManager manager;

    public ChatbayApp(Platform platform, AppServer appServer) {
        this.platform = platform;
        this.appServer = appServer;
        this.logger = platform.logger();
    }

    @Override
    public void onStart() {
        ConversationManager bus = new ConversationManagerImpl();

        logger.info("Starting up ChatBay!");

        logger.info("Loading storage...");

        logger.info("Loading configuration...");

        BotConfig config;
        try {
            config = new ConfigurationHelper<>(
                    Paths.get(""),
                    "config.yml",
                    SnakeYamlConfigurationFactory.create(BotConfig.class, ConfigurationOptions.defaults(), new SnakeYamlOptions.Builder().build()))

                    .reloadConfigData();
        } catch (IOException | InvalidConfigException e) {
            platform.logger().error("An error occurred starting the application: ");
            throw new StartupException(e);
        }

        logger.info("Loading discord api...");

        DiscordApi api = new DiscordApiBuilder().setToken(config.token()).login().join();

        logger.info("Loading discord api registrations...");

        api.addMessageCreateListener(bus);
        api.addMessageCreateListener(new TestCommand(bus, api));

        logger.info("Loading console...");

        ChatbayConsole console = new ChatbayConsole(appServer);

        logger.info("ChatBay launched and ready for battle!");

        console.start();
    }

    @Override
    public void onStop() {
        manager.shutdown();
    }

}
