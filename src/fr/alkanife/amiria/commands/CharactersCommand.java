package fr.alkanife.amiria.commands;

import de.cerus.jdasc.JDASlashCommands;
import de.cerus.jdasc.command.ApplicationCommandOption;
import de.cerus.jdasc.command.ApplicationCommandOptionType;
import de.cerus.jdasc.command.CommandBuilder;
import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.Logs;
import fr.alkanife.amiria.characters.Character;
import fr.alkanife.amiria.characters.Characters;
import fr.alkanife.amiria.lang.Lang;
import fr.alkanife.amiria.lang.Locale;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;

import java.util.List;

public class CharactersCommand {

    public CharactersCommand() {
        JDASlashCommands.submitGuildCommand(new CommandBuilder()
                .name("characters")
                .desc("Liste des personnages — List of characters")
                //.option(new ApplicationCommandOption(ApplicationCommandOptionType.STRING, "creator", "Précisez l'auteur — Specify the author", false))
                .build(), Amiria.getEnyxia().getIdLong(), interaction -> {

            Logs.command(interaction);

            if (Lang.findLocale(interaction).getCharacters().size() == 0) {
                interaction.respond(false, Lang.tl(interaction, "characters-command-no-characters"));
                return;
            }

            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setDescription(Lang.tl(interaction, "characters-command-title"));

            String[] authors = {
                    "Sheele",
                    "FateHard",
                    "Hypix",
                    "KuYuDo",
                    "RelGainSlide",
                    "Sully",
                    "Wellarkel",
                    "Jeffaria",
                    "Mathsky",
                    "Izzumaki"
            };

            for (String author : authors) {
                List<Character> characters = Characters.searchByAuthor(Lang.findLocale(interaction), author);

                String characterList = "";

                if (characters != null) {

                    int i = 0;

                    for (Character character : characters) {

                        if (i > 0)
                            characterList += " ";

                        characterList += "`" + character.getFull_name() + "`";

                        i++;

                        if (i != characters.size())
                            characterList += ",";
                    }
                }

                embedBuilder.addField(Lang.tl(interaction, "characters-command-list", author) + " (" + characters.size() + ")", characterList, false);
            }

            interaction.respond(false, embedBuilder.build());
        });
    }

}
