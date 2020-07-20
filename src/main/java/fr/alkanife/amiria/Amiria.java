package fr.alkanife.amiria;

import fr.alkanife.amiria.character.CharacterManager;
import fr.alkanife.amiria.command.CommandHandler;
import fr.alkanife.amiria.command.Commands;
import fr.alkanife.amiria.event.EventListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class Amiria {

    private static CommandHandler commandHandler = new CommandHandler();
    private static CharacterManager characterManager = new CharacterManager();

    private static String enyxia = "341997146519633922";
    private static String hrp = "468442309311070252";

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

            JDABuilder jdaBuilder = JDABuilder.createDefault(token, Arrays.asList(GatewayIntent.GUILD_MEMBERS,
                    GatewayIntent.GUILD_BANS,
                    GatewayIntent.GUILD_EMOJIS,
                    GatewayIntent.GUILD_PRESENCES,
                    GatewayIntent.GUILD_MESSAGES));
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

    public static String getEnyxia() {
        return enyxia;
    }

    public static String getHrp() {
        return hrp;
    }
}
