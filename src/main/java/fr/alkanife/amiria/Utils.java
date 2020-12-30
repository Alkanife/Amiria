package fr.alkanife.amiria;

import net.dv8tion.jda.api.entities.Message;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    public static String formatDate(Date date) {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String formatDateFile(Date date) {
        String pattern = "dd-MM-yyyy-HH-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String absolutePath() {
        return Paths.get("").toAbsolutePath().toString();
    }

    public static String limit(String value, int length) {
        StringBuilder buf = new StringBuilder(value);
        if (buf.length() > length) {
            buf.setLength(length - 3);
            buf.append("...");
        }

        return buf.toString();
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
