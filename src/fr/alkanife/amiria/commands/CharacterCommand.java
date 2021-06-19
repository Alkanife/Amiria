package fr.alkanife.amiria.commands;

import de.cerus.jdasc.JDASlashCommands;
import de.cerus.jdasc.command.ApplicationCommandOption;
import de.cerus.jdasc.command.ApplicationCommandOptionType;
import de.cerus.jdasc.command.CommandBuilder;
import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.characters.Character;
import fr.alkanife.amiria.characters.Characters;
import fr.alkanife.amiria.lang.Lang;
import fr.alkanife.amiria.lang.Locale;

public class CharacterCommand {

    public CharacterCommand() {
        JDASlashCommands.submitGuildCommand(new CommandBuilder()
                .name("character")
                .desc("Donne la fiche d'un personnage — Give the card of a character")
                .option(new ApplicationCommandOption(ApplicationCommandOptionType.STRING, "name", "Nom du personnage — Character's name", true))
                .build(), Amiria.getEnyxia().getIdLong(), interaction -> {

            String characterName = interaction.getOption("name").getValue();

            if (characterName == null)
                return;

            Locale locale = Lang.findLocale(interaction);

            Character character = Characters.search(locale, characterName);

            if (character == null) {
                interaction.respond(false, Lang.tl(locale, "character-command-not-found"));
                return;
            }

            interaction.respond(false, Lang.tl(locale, "character-command-success"));

            StringBuilder sheetBuilder = new StringBuilder();

            if (character.getFull_name() != null)
                sheetBuilder.append(Lang.tl(locale, "character-command-full-name", character.getFull_name())).append("\n");

            if (character.getTitle() != null)
                sheetBuilder.append(Lang.tl(locale, "character-command-title", character.getTitle())).append("\n");

            if (character.getRace() != null)
                sheetBuilder.append(Lang.tl(locale, "character-command-race", character.getRace())).append("\n");

            if (character.getAge() != null)
                sheetBuilder.append(Lang.tl(locale, "character-command-age", character.getAge())).append("\n");

            if (character.getSex() != null)
                sheetBuilder.append(Lang.tl(locale, "character-command-sex", character.getSex())).append("\n");

            if (character.getFloor() != null)
                sheetBuilder.append(Lang.tl(locale, "character-command-floor", character.getFloor())).append("\n");

            if (character.getSpecializations() != null)
                sheetBuilder.append(Lang.tl(locale, "character-command-specialisations", character.getSpecializations())).append("\n");

            if (character.getWeapons() != null)
                sheetBuilder.append(Lang.tl(locale, "character-command-weapons", character.getWeapons())).append("\n");

            if (character.getCharacter_traits() != null)
                sheetBuilder.append(Lang.tl(locale, "character-command-character-traits", character.getCharacter_traits())).append("\n");

            interaction.getChannel().sendMessage(sheetBuilder.toString()).queue(msg -> {
                final StringBuilder part2 = new StringBuilder();

                if (character.getAspect() != null)
                    part2.append(Lang.tl(locale, "character-command-aspect", character.getAspect())).append("\n");

                if (character.getOwn_power() != null)
                    part2.append(Lang.tl(locale, "character-command-own-power", character.getOwn_power())).append("\n");

                if (character.getState() != null)
                    part2.append(Lang.tl(locale, "character-command-state", character.getState())).append("\n");

                if (character.getOriginal_owner() != null)
                    part2.append(Lang.tl(locale, "character-command-original-owner", character.getOriginal_owner())).append("\n");

                if (character.getOwner_inspirations() != null)
                    part2.append(Lang.tl(locale, "character-command-owner-inspirations", character.getOwner_inspirations())).append("\n");

                if (character.getOwner_notes() != null)
                    part2.append(Lang.tl(locale, "character-command-owner-notes", character.getOwner_notes())).append("\n");

                if (character.isRepresentation_image() && character.getFull_name() != null)
                    part2.append(Lang.tl(locale, "character-command-representation-image", "https://enyxia.alkanife.fr/amiria/images/"
                            + character.getFull_name().split(" ")[0]
                            .toLowerCase(java.util.Locale.ROOT)
                            .replaceAll("é", "e")
                            .replaceAll("ï", "i")
                            .replaceAll("ō", "o")
                            .replaceAll("û", "u"))).append(".png\n");

                msg.getChannel().sendMessage(part2.toString()).queue();
            });

        });
    }

}
