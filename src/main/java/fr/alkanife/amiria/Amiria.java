package fr.alkanife.amiria;

import fr.alkanife.amiria.character.CharacterManager;
import fr.alkanife.amiria.command.CommandHandler;
import fr.alkanife.amiria.command.Commands;
import fr.alkanife.amiria.event.EventListener;
import net.dv8tion.jda.api.JDABuilder;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;

public class Amiria {

    private static CommandHandler commandHandler = new CommandHandler();
    private static CharacterManager characterManager = new CharacterManager();

    public static void main(String[] args) {
        try {
            String configurationName = "amiria.properties";

            File configurationFile = new File(configurationName);

            if (!configurationFile.exists()) {
                Log.log("Failed to find the configuration file.");
                return;
            }

            PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(configurationName);
            String token = propertiesConfiguration.getString("token");

            commandHandler.registerCommands(new Commands());

            JDABuilder jdaBuilder = JDABuilder.createDefault(token);
            jdaBuilder.addEventListeners(new EventListener());
            jdaBuilder.build();
        } catch (Exception exception) {
            Log.error(exception);
        }
    }

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static CharacterManager getCharacterManager() {
        return characterManager;
    }
}
