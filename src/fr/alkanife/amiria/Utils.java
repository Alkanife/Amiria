package fr.alkanife.amiria;

import net.dv8tion.jda.api.entities.Message;

import java.lang.management.ManagementFactory;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utils {

    // convert date into pretty terminal date
    // Fri 5 Feb 2021 05:46:58 PM
    public static String formatDate(Date date) {
        String pattern = "EEE d MMM yyyy hh:mm:ss aaa";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    // same, but without spaces and for file names
    public static String formatDate(Date date, boolean noSpaces) {
        if (!noSpaces)
            return formatDate(date);

        String pattern = "d-MMM-yyyy-hh:mm:ss-aaa";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    // get the absolute path to locate files on linux or windows
    public static String absolutePath() {
        return Paths.get("").toAbsolutePath().toString();
    }

    // limit string values
    public static String limit(String value, int length) {
        StringBuilder buf = new StringBuilder(value);
        if (buf.length() > length) {
            buf.setLength(length - 3);
            buf.append("...");
        }

        return buf.toString();
    }

    // return the uptime in days
    public static long getUpDays(){
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        return TimeUnit.MILLISECONDS.toDays(uptime);
        /*return String.format("%02d hours, %02d minutes, and %02d seconds", TimeUnit.MILLISECONDS.toHours(uptime),
                TimeUnit.MILLISECONDS.toMinutes(uptime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(uptime)),
                TimeUnit.MILLISECONDS.toSeconds(uptime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(uptime)));*/
    }

    public static void sendErrorGif(Message message) {
        String[] errorGifs = {
                "https://static.alkanife.fr/error0.gif",
                "https://static.alkanife.fr/error1.gif",
                "https://static.alkanife.fr/error2.gif",
                "https://static.alkanife.fr/error3.gif",
                "https://static.alkanife.fr/error4.gif",
                "https://static.alkanife.fr/error5.gif"
        };

        int random = new Random().nextInt(errorGifs.length);

        String gif = errorGifs[random];

        message.getChannel().sendMessage(gif).queue();
    }
}
