package fr.alkanife.amiria.commands;

import de.cerus.jdasc.JDASlashCommands;
import de.cerus.jdasc.command.ApplicationCommandOption;
import de.cerus.jdasc.command.ApplicationCommandOptionType;
import de.cerus.jdasc.command.CommandBuilder;
import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.Errors;
import fr.alkanife.amiria.Logs;
import fr.alkanife.amiria.Utils;
import fr.alkanife.amiria.characters.Characters;
import fr.alkanife.amiria.lang.Lang;
import fr.alkanife.amiria.lang.Locale;

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

            Logs.command(interaction, command);

            switch (command.toLowerCase(java.util.Locale.ROOT)) {
                case "help":
                    interaction.respond(false, "`[help]- Commandes disponibles : 'help', 'shutdown', 'info', 'logs', 'reload translations', 'reload characters'.`");
                    break;

                case "shutdown":
                    interaction.respond(false, "`[shutdown]- Arrêt en cours...`");
                    Amiria.getJda().shutdownNow();
                    System.exit(0);
                    break;

                case "info":
                    interaction.respond(false, "`[info]- Amiria version " + Amiria.getVersion() + "`\n" +
                            "`[info]- En route depuis : " + Utils.getUpDays() + " jours`\n" +
                            "`[info]- Logs : " + Logs.logs.size() + "`\n" +
                            "`[info]- Erreurs : " + Errors.errors + "`\n" +
                            "`[info]- Traductions françaises : " + Locale.FRENCH.getTranslations().size() + "`\n" +
                            "`[info]- Traductions anglaises : " + Locale.ENGLISH.getTranslations().size() + "`\n" +
                            "`[info]- Fiches françaises : " + Locale.FRENCH.getCharacters().size() + "`\n" +
                            "`[info]- Fiches anglaises : " + Locale.ENGLISH.getCharacters().size() + "`");
                    break;

                case "logs":
                    int end = Logs.logs.size();
                    int start = Logs.logs.size() - 6;

                    StringBuilder response = new StringBuilder().append("```\n");

                    for (int i = start; i <= end; i++)
                        response.append(Logs.logs.get(i-1)).append("\n");

                    interaction.respond(false, response.append("```").toString());

                    break;

                case "reload translations":
                    if (Lang.load(true))
                        interaction.respond(false, "`[reload]- Les langues ont été rechargés avec succès.`");
                    else
                        interaction.respond(false, "`[reload]- Une erreur s'est produite durant le chargement des langues. Consultez les logs.`");
                    break;

                case "reload characters":
                    if (Characters.load(true))
                        interaction.respond(false, "`[reload]- Les personnages ont été rechargés avec succès.`");
                    else
                        interaction.respond(false, "`[reload]- Une erreur s'est produite durant le chargement des personnages. Consultez les logs.`");
                    break;

                default:
                    interaction.respond(false, "`[sudo]- Commande inconnue, tapez '/sudo help' pour avoir de l'aide.`");
                    break;
            }

        });
    }

}
