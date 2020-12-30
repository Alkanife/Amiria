package fr.alkanife.amiria;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import okhttp3.OkHttpClient;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

public class Amiria {

    private static boolean snapshot;
    private static String version = "1.3";
    private static String jsonBase = "https://enyxia.alkanife.fr/data/";
    private static Date launch;

    private static Guild enyxia;
    private static TextChannel HRP;
    private static TextChannel panel;

    private static CommandHandler commandHandler;

    private static JDA jda;

    private static OkHttpClient okHttpClient;

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Logs.info("Amiria version " + version);

        launch = new Date();
        okHttpClient = new OkHttpClient();

        try {
            Thread.sleep(1000);

            /* READ TOKEN */
            File tokenFile = new File(Utils.absolutePath() + "/token");

            if (!tokenFile.exists()) {
                Logs.error("Je n'arrive pas a trouver le fichier token.");
                return;
            }

            List<String> tokenFileLines = Files.readAllLines(tokenFile.toPath());

            if (tokenFileLines.size() == 0) {
                Logs.error("Le token n'est pas valide.");
            }

            /* SETTING UP COMMANDS */
            commandHandler = new CommandHandler();
            commandHandler.registerCommands(new AdminCommands());
            commandHandler.registerCommands(new Commands());

            /* SETTING UP JDA */
            Logs.info("Connexion à Discord...");
            JDABuilder jdaBuilder = JDABuilder.createDefault(tokenFileLines.get(0));
            jdaBuilder.setStatus(OnlineStatus.ONLINE);
            jdaBuilder.setActivity(Activity.watching("Enyxia | @Amiria help pour avoir de l'aide"));
            jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
            jdaBuilder.addEventListeners(new Events());
            jda = jdaBuilder.build();

        } catch (Exception exception) {
            Logs.error("Echec du lancement !");
            exception.printStackTrace();
        }
    }

    public static boolean isSnapshot() {
        return snapshot;
    }

    public static void setSnapshot(boolean snapshot) {
        Amiria.snapshot = snapshot;
    }

    public static String getVersion() {
        return version + (snapshot ? "-SNAPSHOT" : "");
    }

    public static String getJsonBase() {
        return jsonBase;
    }

    public static Date getLaunch() {
        return launch;
    }

    public static Guild getEnyxia() {
        return enyxia;
    }

    public static void setEnyxia(Guild enyxia) {
        Amiria.enyxia = enyxia;
    }

    public static TextChannel getHRP() {
        return HRP;
    }

    public static void setHRP(TextChannel HRP) {
        Amiria.HRP = HRP;
    }

    public static TextChannel getPanel() {
        return panel;
    }

    public static void setPanel(TextChannel panel) {
        Amiria.panel = panel;
    }

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static void setCommandHandler(CommandHandler commandHandler) {
        Amiria.commandHandler = commandHandler;
    }

    public static JDA getJda() {
        return jda;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
