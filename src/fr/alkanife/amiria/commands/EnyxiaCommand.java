package fr.alkanife.amiria.commands;

import de.cerus.jdasc.JDASlashCommands;
import de.cerus.jdasc.command.CommandBuilder;
import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.Logs;
import fr.alkanife.amiria.lang.Lang;

public class EnyxiaCommand {

    public EnyxiaCommand() {
        JDASlashCommands.submitGuildCommand(new CommandBuilder()
                .name("enyxia")
                .desc("Qu'est-ce qu'Enyxia ? — What is Enyxia?")
                .build(), Amiria.getEnyxia().getIdLong(), interaction -> {

            Logs.command(interaction);

            interaction.respond(false, Lang.tl(interaction, "enyxia-command"));
        });
    }

}
