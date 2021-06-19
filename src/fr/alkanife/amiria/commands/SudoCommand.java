package fr.alkanife.amiria.commands;

import de.cerus.jdasc.JDASlashCommands;
import de.cerus.jdasc.command.ApplicationCommandOption;
import de.cerus.jdasc.command.ApplicationCommandOptionType;
import de.cerus.jdasc.command.CommandBuilder;
import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.lang.Lang;

import java.util.Locale;

public class SudoCommand {

    public SudoCommand() {
        JDASlashCommands.submitGuildCommand(new CommandBuilder()
                .name("sudo")
                .desc("Commande administrateur — Administrator only command")
                .option(new ApplicationCommandOption(ApplicationCommandOptionType.STRING, "command", "sudo command", true))
                .build(), Amiria.getEnyxia().getIdLong(), interaction -> {

            String command = interaction.getOption("command").getValue();

            if (command == null)
                return;

            switch (command.toLowerCase(Locale.ROOT)) {
                case "help":
                    interaction.respond(false, "`help`, `reload translations`, `shutdown`");
                    break;

                case "reload translations":
                    Lang.load(true);
                    interaction.respond(false, "\uD83D\uDC4D");
                    break;

                case "shutdown":
                    interaction.respond(false, "\uD83D\uDC4D");
                    Amiria.getJda().shutdownNow();
                    System.exit(0);
                    break;

                default:
                    interaction.respond(false, "Unknown command.");
                    break;
            }

        });
    }

}
