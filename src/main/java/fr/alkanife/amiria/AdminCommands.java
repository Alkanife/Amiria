package fr.alkanife.amiria;

import net.dv8tion.jda.api.entities.Message;

import java.time.temporal.ChronoUnit;

public class AdminCommands {

    @Command(name = "shutdown", isAdmin = true)
    public void shutdown(Message message) {
        Logs.terminalResponse("> `Arrêt en cours, à plus !`");
        Logs.info("Arrêt commandé par " + message.getAuthor().getAsTag());
        Amiria.getJda().shutdown();
    }

    @Command(name = "globalreport", isAdmin = true)
    public void report(Message message) {
        message.getChannel().sendMessage(">>> `Création du rapport...`").queue(msg -> {
            long ping = message.getTimeCreated().until(msg.getTimeCreated(), ChronoUnit.MILLIS);

            StringBuilder responseBuilder = new StringBuilder();
            responseBuilder.append("NOM DU BUILD.....................: AMIRIA " + Amiria.getVersion()).append("\n");
            responseBuilder.append("EN FONCTIONNEMENT DEPUIS LE......: " + Utils.formatDate(Amiria.getLaunch())).append("\n");
            responseBuilder.append("LATENCE..........................: " + ping + " MS").append("\n");
            responseBuilder.append("LATENCE (GATEWAY)................: " + message.getJDA().getGatewayPing() + " MS").append("\n");
            responseBuilder.append(" ").append("\n");
            responseBuilder.append("COMMANDES ACTIVES................: " + Amiria.getCommandHandler().getCommands().size()).append("\n");
            responseBuilder.append("COMMANDE(S) EXÉCUTÉE(S)..........: " + Amiria.getCommandHandler().getExecutedCommands()).append("\n");
            responseBuilder.append(" ").append("\n");
            responseBuilder.append("ERREUR(S) SURVENUE(S)............: " + Errors.errors).append("\n");
            responseBuilder.append(" ").append("\n");
            responseBuilder.append("BASE JSON........................: " + Amiria.getJsonBase());

            msg.editMessage(">>> ```" + responseBuilder.toString() + "```").queue();
        });
    }

}
