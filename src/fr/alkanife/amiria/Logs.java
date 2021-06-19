package fr.alkanife.amiria;

import java.util.Date;

public class Logs {

    public static void info(String message) {
        System.out.println("\u001B[36m[" + Utils.formatDate(new Date()) + "] " + message + "\u001B[0m");
    }

    public static void warning(String message) {
        System.out.println("\u001B[36m[" + Utils.formatDate(new Date()) + "] " + message + "\u001B[0m");
    }

    public static void error(String message) {
        System.out.println("\u001B[36m[" + Utils.formatDate(new Date()) + "] error --- " + message + "\u001B[0m");
    }

    public static void error(String message, Exception exception) {

    }

}
