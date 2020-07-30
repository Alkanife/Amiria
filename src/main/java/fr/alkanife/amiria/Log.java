package fr.alkanife.amiria;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Log {

    private static int handledErrors = 0;

    public static void log(String message) {
        System.out.println("\u001B[36m[" + dateAndHour() + "] " + message + "\u001B[0m");
    }

    public static void warn(String message) {
        System.out.println("\u001B[33m[" + dateAndHour() + "] " + message + "\u001B[0m");
    }

    public static void error(Exception exception) {
        handledErrors += 1;
        exception.printStackTrace();
    }

    public static int getHandledErrors() {
        return handledErrors;
    }

    private static String dateAndHour() {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
