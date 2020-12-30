package fr.alkanife.amiria;

import net.dv8tion.jda.api.entities.Message;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class Errors {

    public static int errors = 0;

    public static void handle(Exception exception, Message message, boolean notification) {
        try {
            //Build logfile content
            StringBuilder logfileContent = new StringBuilder();

            if (message != null) {
                logfileContent.append("Un message dans le salon #")
                        .append(message.getChannel().getName()).append(" envoyé par ").append(message.getAuthor().getAsTag()).append(" a généré une erreur").append("\n");
                logfileContent.append("Message : \"").append(message.getContentRaw()).append("\"\n\n");
            }

            if (exception.getMessage() != null)
                if (!exception.getMessage().equalsIgnoreCase("null"))
                    logfileContent.append(exception.getMessage()).append("\n");

            for (StackTraceElement stackTraceElement : exception.getStackTrace())
                logfileContent.append("   at ").append(stackTraceElement).append("\n");

            //Make file
            String filePath = Utils.absolutePath() + "/erreur-" + Utils.formatDateFile(new Date());
            File newFile = new File(filePath);

            if (!newFile.createNewFile())
                return;

            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(logfileContent.toString());
            fileWriter.close();

            //Send response
            if (message != null)
                message.getChannel().sendMessage("Coudur ! Une erreur s'est produite durant l'execution de ta commande.").queue(Utils::sendErrorGif);

            String limitedError = Utils.limit(logfileContent.toString(), 1500);

            Logs.terminalError(limitedError, notification);

            Logs.warn("Nouvelle erreur loggée dans " + filePath);
        } catch (Exception exception1) {
            Logs.error("Echec du traitement d'une erreur :");
            exception1.printStackTrace();
        }
    }
}
