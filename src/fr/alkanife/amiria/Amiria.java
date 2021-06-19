package fr.alkanife.amiria;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.HashMap;

public class Amiria {

    private static String version = "2.0";

    private static HashMap<String, Object> configurationValues;

    private static String token;

    private static Guild enyxia;

    private static Role megapolice;
    private static Role english;

    private static TextChannel hrp;
    private static TextChannel notifications;

    private static JDA jda;

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Logs.info("Starting Amiria " + version);

        try {
            Logs.info("Reading configuration.yml");

            configurationValues = YmlReader.read("configuration");

            if (configurationValues == null) {
                Logs.error("configuration.yml not found");
                return;
            }

            Object token = configurationValues.get("token");

            if (token == null) {
                Logs.error("Invalid configuration: token is null");
                return;
            }

            Amiria.token = String.valueOf(token);

            Logs.info("Connecting to Discord");
            JDABuilder jdaBuilder = JDABuilder.createDefault(Amiria.token);
            jdaBuilder.setRawEventsEnabled(true);
            jdaBuilder.setStatus(OnlineStatus.ONLINE);
            jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
            jdaBuilder.addEventListeners(new Events());
            jda = jdaBuilder.build();

        } catch (Exception exception) {
            Logs.error("Failed to start!");
            exception.printStackTrace();
        }
    }

    public static String getVersion() {
        return version;
    }

    public static HashMap<String, Object> getConfigurationValues() {
        return configurationValues;
    }

    public static String getToken() {
        return token;
    }

    public static Guild getEnyxia() {
        return enyxia;
    }

    public static void setEnyxia(Guild enyxia) {
        Amiria.enyxia = enyxia;
    }

    public static JDA getJda() {
        return jda;
    }

    public static Role getMegapolice() {
        return megapolice;
    }

    public static void setMegapolice(Role megapolice) {
        Amiria.megapolice = megapolice;
    }

    public static Role getEnglish() {
        return english;
    }

    public static void setEnglish(Role english) {
        Amiria.english = english;
    }

    public static TextChannel getHrp() {
        return hrp;
    }

    public static void setHrp(TextChannel hrp) {
        Amiria.hrp = hrp;
    }

    public static TextChannel getNotifications() {
        return notifications;
    }

    public static void setNotifications(TextChannel notifications) {
        Amiria.notifications = notifications;
    }
}
