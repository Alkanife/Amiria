package fr.alkanife.amiria.command;

import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.Log;
import fr.alkanife.amiria.character.Character;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Basics {

    @Command(name = "help",
            description = "Besoin d'aide ?")
    public void help(Message message) {
        StringBuilder stringBuilder = new StringBuilder();

        for (SimpleCommand simpleCommand : Amiria.getCommandHandler().getCommands())
            if (simpleCommand.isListed() && !simpleCommand.isAdmin())
                stringBuilder.append("  ■ <@439490584189599744> ").append(simpleCommand.getName()).append(" - ").append(simpleCommand.getDesription()).append("\n");

        message.getChannel().sendMessage("Pour vous aider je peux vous proposer une liste de mes commandes !\n"
                + stringBuilder.toString()).queue();
    }

    @Command(name = "about",
            description = "Qui suis-je ? Quelle est ma raison de vivre ?")
    public void about(Message message) {
        message.getChannel().sendMessage("Je suis Amiria, un bot créé pour vous aider ou tout simplement vous renseigner à propos d'Enyxia. J'ai été originalement créée et administrée le 3 avril 2018 par Sheele, puis re-créée sur de bonnes bases par Alkanife le 5 juillet 2020.").queue();
    }

    @Command(name = "restart",
            isListed = false,
            isAdmin = true)
    public void restart(Message message) {
        message.getChannel().sendMessage("\uD83D\uDC4D").queue();
        Log.log("Un redémarrage a été demandé par " + message.getAuthor().getName());
        message.getJDA().shutdown();
    }

    @Command(name = "info",
            isListed = false,
            isAdmin = true)
    public void info(Message message) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription("Version: `" + Amiria.getVersion() + "`\n" +
                "Gateway ping: `" + message.getJDA().getGatewayPing() + "`\n" +
                "Commands:  `" + Amiria.getCommandHandler().getCommands().size() + "`\n" +
                "Executed commands: `" + Amiria.getCommandHandler().getExecutedCommands() + "`\n" +
                "Handled errors: `" + Log.getHandledErrors() + "`");

        message.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
