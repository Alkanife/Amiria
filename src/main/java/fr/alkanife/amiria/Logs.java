package fr.alkanife.amiria;

import java.util.Date;

public class Logs {

    public static void info(String message) {
        System.out.println("\u001B[36m[" + Utils.formatDate(new Date()) + "] " + message + "\u001B[0m");
    }

    public static void warn(String message) {
        System.out.println("\u001B[33m[" + Utils.formatDate(new Date()) + "] " + message + "\u001B[0m");
    }

    public static void error(String message) {
        System.out.println("\u001B[31m[" + Utils.formatDate(new Date()) + "] " + message + "\u001B[0m");
    }

    public static void terminalInfo(String message) {
        Amiria.getPanel().sendMessage(">>> `" + message + "`").queue();
    }

    public static void terminalResponse(String message) {
        Amiria.getPanel().sendMessage(message).queue();
    }

    public static void terminalError(String error, boolean notification) {
        Amiria.getPanel().sendMessage(">>> " + (notification ? "@here • " : "") + "`Erreur ! Un rapport complet a été créé dans le répertoire` ```" + error + "```").queue();
    }

}
