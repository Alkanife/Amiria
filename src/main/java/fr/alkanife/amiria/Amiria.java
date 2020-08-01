package fr.alkanife.amiria;

import fr.alkanife.amiria.character.CharacterManager;
import fr.alkanife.amiria.command.CommandHandler;
import fr.alkanife.amiria.command.Basics;
import fr.alkanife.amiria.command.Enyxia;
import fr.alkanife.amiria.command.Fun;
import fr.alkanife.amiria.event.EventListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.util.Arrays;

public class Amiria {

    private static CommandHandler commandHandler = new CommandHandler();
    private static CharacterManager characterManager = new CharacterManager();

    private static String version = "1.2.1";

    private static String enyxia = "341997146519633922";
    private static String hrp = "342148162972024844";

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

            commandHandler.registerCommands(new Basics());
            commandHandler.registerCommands(new Enyxia());
            commandHandler.registerCommands(new Fun());

            JDABuilder jdaBuilder = JDABuilder.createDefault(token, Arrays.asList(GatewayIntent.GUILD_MEMBERS,
                    GatewayIntent.GUILD_BANS,
                    GatewayIntent.GUILD_EMOJIS,
                    GatewayIntent.GUILD_PRESENCES,
                    GatewayIntent.GUILD_MESSAGES));
            jdaBuilder.addEventListeners(new EventListener());
            jdaBuilder.setActivity(Activity.watching("Enyxia | @Amiria help pour avoir de l'aide"));
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

    public static String getVersion() {
        return version;
    }

    public static String getEnyxia() {
        return enyxia;
    }

    public static String getHrp() {
        return hrp;
    }
}
