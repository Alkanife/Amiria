package fr.alkanife.amiria;

import java.util.Date;

public class Errors {

    public static int errors = 0;

    public static void error(String message) {
        error(message, true);
    }

    public static void error(String message, boolean log) {
        System.out.println("\033[0;33m[amiria]- " + Utils.formatDate(new Date()) + "- error- " + message + "\033[0m");

        if (log)
            Logs.logs.add(Utils.formatDate(new Date()) + "- error- " + message);

        errors++;
    }

    public static void error(String message, Exception exception) {
        error(message);
        error("Trace:", false);
        exception.printStackTrace();
    }

}
