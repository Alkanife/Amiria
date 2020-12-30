package fr.alkanife.amiria;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Commands {

    @Command(name = "help",
            description = "Besoin d'aide ? C'est la commande que tu utilises actuellement.")
    public void help(Message message) {
        StringBuilder helpBuilder = new StringBuilder();

        for (AmiriaCommand amiriaCommand : Amiria.getCommandHandler().getCommands())
            if (!amiriaCommand.isAdmin())
                helpBuilder.append("  ■ <@439490584189599744> ").append(amiriaCommand.getName()).append(" - ").append(amiriaCommand.getDesription()).append("\n");

        message.getChannel().sendMessage("Voici une liste de mes commandes pour t'aider :\n"
                + helpBuilder.toString()).queue();
    }

    @Command(name = "about",
            description = "Qui suis-je ? Quelle est ma raison de vivre ?")
    public void about(Message message) {
        message.getChannel().sendMessage("Je suis Amiria, un bot créé pour vous aider ou tout simplement vous renseigner à propos d'Enyxia. J'ai été originalement créée et administrée le 3 avril 2018 par Sheele, puis re-créée sur de bonnes bases par Alkanife le 5 juillet 2020.").queue();
    }

    @Command(name = "enyxia",
            description = "Qu'est-ce qu'Enyxia ?")
    public void enyxia(Message message) {
        message.getChannel().sendMessage("Le roleplay Enyxia est un projet créé par Jeffaria le 1 août 2017. " +
                "Il a été dirigé par KuYuDo jusqu'en septembre 2017, jusqu'à ce que ce dernier se retire pour des raisons personnelles. " +
                "Depuis ce jour, Sheele repris le projet. Le projet en tant que roleplay s'achève le 29 juillet 2018, il n'est maintenant plus qu'un projet littéraire, toujours dirigé par Sheele.\n \n" +
                "Les participants au roleplay au file des 8 saisons ont été:\n" +
                "  ■ Jeffaria\n" +
                "  ■ Sheele\n" +
                "  ■ Hypix\n" +
                "  ■ Fatehard\n" +
                "  ■ Mathsky\n" +
                "  ■ RelGainSliDe\n" +
                "  ■ Sully\n" +
                "  ■ Wellarkel\n" +
                "  ■ KuYuDo\n" +
                "  ■ Izzumaki\n \n" +
                "Les noms respectifs des 8 saisons sont:\n" +
                "  ■ Saison 1 - \"L'Enyxia\" (août 2017, septembre 2017)\n" +
                "  ■ Saison 2 - \"Les larmes du passé\" (septembre 2017, octobre 2017)\n" +
                "  ■ Saison 3 - \"Carnage\" (octobre 2017)\n" +
                "  ■ Saison 4 - \"La mort et la gloire\" (octobre 2017, décembre 2017)\n" +
                "  ■ Saison 5 - \"Retour\" (décembre 2017, mars 2018)\n" +
                "  ■ Saison 6 - \"Destinée\" (mars 2018, juin 2018)\n" +
                "  ■ Saison 7 - \"La fin de L'Enyxia\" (juin 2018)\n" +
                "  ■ Saison 8 - \"Tour Infinie\" (juillet 2018, en cours)\n").queue();
    }

    @Command(name = "summaries", description = "Résumés des 7 saisons")
    public void summaries(Message message) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription("  ■ [Saison 1 - \"L'Enyxia\"](https://docs.google.com/document/d/107JWs2WCHT8vn8eX7p302eV0HTN4PySlLVSBrItLnwM/edit?usp=sharing)\n" +
                "  ■ [Saison 2 - \"Les larmes du passé\"](https://docs.google.com/document/d/1SksJhaallGrdqRLkGkwEFnIZiyRzjugiScyF9oCGAJA/edit?usp=sharing)\n" +
                "  ■ [Saison 3 - \"Carnage\"](https://docs.google.com/document/d/1O3clMG7KCZsyYP9g3vwCjq57UIXCn-P6fa3BZ2W04-U/edit?usp=sharing)\n" +
                "  ■ [Saison 4 - \"La mort et la gloire\"](https://docs.google.com/document/d/16v3dZXqwdFImESEXvURt2vOLBGiO28zGsjMGd7UNS8Q/edit?usp=sharing)\n" +
                "  ■ [Saison 5 - \"Retour\"](https://docs.google.com/document/d/1GouqZanz43qfKrfUgd1yQLM3J8JOLpWajIUpPKjCp3w/edit?usp=sharing)\n" +
                "  ■ [Saison 6 - \"Destinée\"](https://docs.google.com/document/d/1UWQMyWmG2FG7JPn3eomK-dTqlprFWTnMzFpkmlHctQg/edit?usp=sharing)\n" +
                "  ■ [Saison 7 - \"La fin de L'Enyxia\"](https://docs.google.com/document/d/1Sk_93WXGzda3kSsSzY_R-f5xn3NiViQstFNoDL5sOgM/edit?usp=sharing)");

        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setContent("*Cliquez sur une saison, et vous serez redirigé vers le résumé (Google Docs).*");
        messageBuilder.setEmbed(embedBuilder.build());

        message.getChannel().sendMessage(messageBuilder.build()).queue();
    }

    @Command(name = "characters",
            description = "Liste de tous les personnages & leurs créateurs")
    public void characters(Message message) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.addField("Personnages de Sheele (29)", "`Akuma Horton`, `Alexia Cavalies`, `Andrea Ada`, `Bimaï Hitch`, `Darken Sheele`, `Endal Mao`, `Enyxia Cavalies`, " +
                "`Extasia Cavalies`, `Hanyûu Thatameth`, `Kanna Cavalies`, `Kashya Hyrenya`, `Kei Hyrenya`, `KuYu Cavalies`, `Maskaï Hitch`, `Maureen Mojito`, `Morgiana Cavalies`, " +
                "`Nagoramatio Ariana-Fergonez`, `Nikolaï Hitch`, `Ravenn Cavalies`, `Rika Atalante`, `Sheele Cavalies`, `Veldo Cavalies`, `Yoshisuu Cavalies`, `Yuu Cavalies`, " +
                "`William Cavalies`, `Wrong Sheele`, `Amalia Hyvernn`", false);

        embedBuilder.addField("Personnages de Fatehard (2)", "`Math Michelle`, `Aliédad Cavalies`", false);

        embedBuilder.addField("Personnages de Hypix (3)", "`Kass Horton`, `Asarim Horton`, `Terry Touère`", false);

        embedBuilder.addField("Personnages de KuYuDo (3)", "`Kudo`, `Yudo`, `Gaia`", false);

        embedBuilder.addField("Personnages de RelGainSliDe (1)", "`Christopher Terrones`", false);

        embedBuilder.addField("Personnages de Sully (1)", "`Sully Albatrum`", false);

        embedBuilder.addField("Personnages de Wellarkel (1)", "`Anoriel Aqualis`", false);

        embedBuilder.addField("Personnages de Jeffaria (1)", "`Raizan Dymen`", false);

        embedBuilder.addField("Personnages de Mathsky (6)", "`MathSpirit`, `Nerias`, `Mahito Sukeruton`, `Gōsutookami`, `Jessica Grey`, `Trinity Grey`", false);

        embedBuilder.addField("Personnages de Izzumaki (2)", "`Tidus Ragnarok`, `Matthias Garcia`", false);

        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setContent("*Voici une liste complète des personnages triés par leurs créateur d'origine.*");
        messageBuilder.setEmbed(embedBuilder.build());

        message.getChannel().sendMessage(messageBuilder.build()).queue();
    }

    @Command(name = "character",
            description = "Donne la fiche d'un personnage")
    public void character(Message message) {
        try {
            String content = message.getContentRaw();
            String[] splittedContent = content.split(" ");

            if (splittedContent.length <= 2) {
                message.getChannel().sendMessage("Utilisation de la commande : `@Amiria character <prénom du personnage>`.").queue();
                return;
            }

            message.getChannel().sendTyping().queue();

            String receivedCharacterName = splittedContent[2];

            String formattedCharacterName = receivedCharacterName.toLowerCase();
            formattedCharacterName = formattedCharacterName.replaceAll("ï", "i");
            formattedCharacterName = formattedCharacterName.replaceAll("ō", "o");
            formattedCharacterName = formattedCharacterName.replaceAll("û", "u");

            final String characterName = formattedCharacterName;

            Request request = new Request.Builder()
                    .url("https://enyxia.alkanife.fr/data/characters/" + characterName + ".json")
                    .addHeader("User-Agent", "AMIRIA-" + Amiria.getVersion())
                    .get()
                    .build();

            Response response = Amiria.getOkHttpClient().newCall(request).execute();

            if (response.body() == null) {
                message.getChannel().sendMessage("Il semble que l'API ai un petit souci...").queue(Utils::sendErrorGif);
                return;
            }

            String body = response.body().string();
            response.close();

            Gson gson = new GsonBuilder().serializeNulls().create();

            CharacterSheet characterSheet = gson.fromJson(body, CharacterSheet.class);

            StringBuilder sheetBuilder = new StringBuilder();

            if (characterSheet.getFull_name() != null)
                sheetBuilder.append("**NOM COMPLET CONNU :** ").append(characterSheet.getFull_name()).append("\n");

            if (characterSheet.getTitle() != null)
                sheetBuilder.append("**TITRE :** ").append(characterSheet.getTitle()).append("\n");

            if (characterSheet.getRace() != null)
                sheetBuilder.append("**RACE :** ").append(characterSheet.getRace()).append("\n");

            if (characterSheet.getAge() != null)
                sheetBuilder.append("**ÂGE :** ").append(characterSheet.getAge()).append("\n");

            if (characterSheet.getSex() != null)
                sheetBuilder.append("**SEXE :** ").append(characterSheet.getSex()).append("\n");

            if (characterSheet.getFloor() != null)
                sheetBuilder.append("**ÉTAGE :** ").append(characterSheet.getFloor()).append("\n");

            if (characterSheet.getSpecializations() != null)
                sheetBuilder.append("**SPÉCIALISATION(S) :** ").append(characterSheet.getSpecializations()).append("\n");

            if (characterSheet.getWeapons() != null)
                sheetBuilder.append("**ARME(S) :** ").append(characterSheet.getWeapons()).append("\n");

            if (characterSheet.getCharacter_traits() != null)
                sheetBuilder.append("**TRAITS DE CARACTÈRE :** ").append(characterSheet.getCharacter_traits()).append("\n");

            message.getChannel().sendMessage(sheetBuilder.toString()).queue(msg -> {
                final StringBuilder part2 = new StringBuilder();

                if (characterSheet.getAspect() != null)
                    part2.append("**ASPECT PHYSIQUE :** ").append(characterSheet.getAspect()).append("\n");

                if (characterSheet.getOwn_power() != null)
                    part2.append("**POUVOIR PROPRE :** ").append(characterSheet.getOwn_power()).append("\n");

                if (characterSheet.getState() != null)
                    part2.append("**STATUT :** ").append(characterSheet.getState()).append("\n");

                if (characterSheet.getOriginal_owner() != null)
                    part2.append("**PROPRIÉTAIRE ORIGINEL :** ").append(characterSheet.getOriginal_owner()).append("\n");

                if (characterSheet.getOwner_inspirations() != null)
                    part2.append("**INSPIRATION(S) :** ").append(characterSheet.getOwner_inspirations()).append("\n");

                if (characterSheet.getOwner_notes() != null)
                    part2.append("**NOTES :** ").append(characterSheet.getOwner_notes()).append("\n");

                if (characterSheet.isRepresentation_image())
                    part2.append("**IMAGE REPRÉSENTATIVE :** ").append("https://enyxia.alkanife.fr/data/characters/images/").append(characterName).append(".png");

                msg.getChannel().sendMessage(part2.toString()).queue();
            });
        } catch (JsonSyntaxException exception) {
            message.getChannel().sendMessage("Mhhhh, je ne trouve pas la fiche. As-tu bien tapé entièrement le __prénom__ du personnage bien orthographié ?").queue(Utils::sendErrorGif);
        } catch (IllegalArgumentException exception) {
            message.getChannel().sendMessage("J'aimerais bien donner cette fiche mais elle fait plus de 2 000 charactères. J'ai pas envie de me faire gronder par Discord.").queue(Utils::sendErrorGif);
        } catch (IOException exception) {
            Errors.handle(exception, message, true);
        }
    }

    @Command(name = "echo")
    public void echo(Message message) {//for fun
        if (!message.getAuthor().getId().equals("437284414519640065")
                && !message.getAuthor().getId().equals("274627973255135242"))
            return;

        String[] args = message.getContentRaw().split(" ");

        if (args.length == 2)
            return;

        String channelID = args[2];
        TextChannel textChannel = null;

        try {
            textChannel = message.getGuild().getTextChannelById(channelID);
        } catch (Exception ignore){}

        StringBuilder stringBuilder = new StringBuilder();
        if (textChannel == null) {
            for (int i = 2; i < args.length; i++)
                stringBuilder.append(args[i]).append(" ");

            message.getChannel().sendMessage(stringBuilder.toString()).queue();
        } else {
            for (int i = 3; i < args.length; i++)
                stringBuilder.append(args[i]).append(" ");

            textChannel.sendMessage(stringBuilder.toString()).queue();
        }

        message.delete().queue();
    }
}
