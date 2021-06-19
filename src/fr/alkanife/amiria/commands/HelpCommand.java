package fr.alkanife.amiria.commands;

import de.cerus.jdasc.JDASlashCommands;
import de.cerus.jdasc.command.CommandBuilder;
import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.lang.Lang;

public class HelpCommand {

    public HelpCommand() {
        JDASlashCommands.submitGuildCommand(new CommandBuilder()
                .name("help")
                .desc("Besoin d'aide ? — Get some help!")
                .build(), Amiria.getEnyxia().getIdLong(), interaction -> interaction.respond(false, Lang.tl(interaction, "help-command")));
    }

}
