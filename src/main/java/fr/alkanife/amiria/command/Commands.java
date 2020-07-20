package fr.alkanife.amiria.command;

import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.Log;
import fr.alkanife.amiria.character.Character;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Commands {

    @Command(name = "help",
            description = "Besoin d'aide ?")
    public void help(Message message) {
        StringBuilder stringBuilder = new StringBuilder();

        for (SimpleCommand simpleCommand : Amiria.getCommandHandler().getCommands())
            stringBuilder.append("  ■ <@439490584189599744> ").append(simpleCommand.getName()).append(" - ").append(simpleCommand.getDesription()).append("\n");

        message.getChannel().sendMessage("Pour vous aider je peux vous proposer une liste de mes commandes !\n"
                + stringBuilder.toString()).queue();
    }

    @Command(name = "about",
            description = "Qui suis-je ? Quelle est ma raison de vivre ?")
    public void about(Message message) {
        message.getChannel().sendMessage("Je suis Amiria, un bot créé pour vous aider ou tout simplement vous renseigner à propos d'Enyxia. J'ai été originalement créée et administrée le 3 avril 2018 par Sheele, puis re-créée sur de bonnes bases par Alkanife le 5 juillet 2020.").queue();
    }

    @Command(name = "status",
            description = "T'inquiète je pète la forme")
    public void status(Message message) {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();

        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        String uptimeString = String.format("%02d heures, %02d minutes, et %02d secondes", TimeUnit.MILLISECONDS.toHours(uptime),
                TimeUnit.MILLISECONDS.toMinutes(uptime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(uptime)),
                TimeUnit.MILLISECONDS.toSeconds(uptime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(uptime)));

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Status");
        embedBuilder.setDescription("Uptime: `" + uptimeString + "`\n" +
                "Ping: `" + message.getJDA().getGatewayPing() + " ms`\n" +
                "\n" +
                "Detected commands: `" + Amiria.getCommandHandler().getCommands().size() + "`\n" +
                "Executed commands: `" + Amiria.getCommandHandler().executedCommands + "`\n" +
                "Handled errors: `" + Log.errors.size() + "`");

        message.getChannel().sendMessage(embedBuilder.build()).queue();

    }

    @Command(name = "admin",
            description = "Commande réservée au développeur",
            isAdmin = true)
    public void admin(Message message) {
        String content = message.getContentStripped();
        String[] args = content.split(" ");

        if (args.length == 2) {
            message.getChannel().sendMessage("Mmh, ce n'est pas comme ça que fonctionne cette commande. Essayez `.. admin errors/restart`.").queue();
            return;
        }

        String arg = args[2].toLowerCase();

        switch (arg) {
            case "errors":
                if (Log.errors.size() == 0) {
                    message.getChannel().sendMessage("Je me porte parfaitement bien, aucune erreur !").queue();
                    return;
                }

                if (Log.errors.size() > 5) {
                    message.getChannel().sendMessage("Le nombre d'erreur est trop grand, regardez la console.").queue();
                    return;
                }

                StringBuilder errors = new StringBuilder();
                errors.append(Log.errors.size()).append(" erreur(s):\n");

                for (String error : Log.errors)
                    errors.append("  ■ `").append(error).append("`\n");

                message.getChannel().sendMessage(errors.toString()).queue();
                break;

            case "restart":
                message.getChannel().sendMessage("\uD83D\uDC4C").queue();
                message.getJDA().shutdown();
                break;

            default:
                message.getChannel().sendMessage("Mmh, ce n'est pas comme ça que fonctionne cette commande. Essayez `.. admin errors/restart`.").queue();
                break;
        }

    }

    @Command(name = "enyxia",
            description = "À propos d'Enyxia")
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
        embedBuilder.setTitle("Résumés");
        embedBuilder.setDescription("*Cliquez sur une saison, et vous serez redirigé vers le résumé (Google Docs).*\n \n" +
                "  ■ [Saison 1 - \"L'Enyxia\"](https://docs.google.com/document/d/107JWs2WCHT8vn8eX7p302eV0HTN4PySlLVSBrItLnwM/edit?usp=sharing)\n" +
                "  ■ [Saison 2 - \"Les larmes du passé\"](https://docs.google.com/document/d/1SksJhaallGrdqRLkGkwEFnIZiyRzjugiScyF9oCGAJA/edit?usp=sharing)\n" +
                "  ■ [Saison 3 - \"Carnage\"](https://docs.google.com/document/d/1O3clMG7KCZsyYP9g3vwCjq57UIXCn-P6fa3BZ2W04-U/edit?usp=sharing)\n" +
                "  ■ [Saison 4 - \"La mort et la gloire\"](https://docs.google.com/document/d/16v3dZXqwdFImESEXvURt2vOLBGiO28zGsjMGd7UNS8Q/edit?usp=sharing)\n" +
                "  ■ [Saison 5 - \"Retour\"](https://docs.google.com/document/d/1GouqZanz43qfKrfUgd1yQLM3J8JOLpWajIUpPKjCp3w/edit?usp=sharing)\n" +
                "  ■ [Saison 6 - \"Destinée\"](https://docs.google.com/document/d/1UWQMyWmG2FG7JPn3eomK-dTqlprFWTnMzFpkmlHctQg/edit?usp=sharing)\n" +
                "  ■ [Saison 7 - \"La fin de L'Enyxia\"](https://docs.google.com/document/d/1Sk_93WXGzda3kSsSzY_R-f5xn3NiViQstFNoDL5sOgM/edit?usp=sharing)");

        message.getChannel().sendMessage(embedBuilder.build()).queue();
    }

    @Command(name = "characters",
            description = "Liste de tous les personnages & leurs créateurs")
    public void characters(Message message) {
        message.getChannel().sendMessage("Voici une liste complète des personnages triés par leurs créateur.\n \n" +
                "Personnages de Sheele (29):\n" +
                "  ■ Akuma Horton\n" +
                "  ■ Alexia Cavalies\n" +
                "  ■ Andrea Ada\n" +
                "  ■ Bimaï Hitch\n" +
                "  ■ Darken Sheele\n" +
                "  ■ Endal Mao\n" +
                "  ■ Enyxia Cavalies\n" +
                "  ■ Extasia Cavalies\n" +
                "  ■ Hanyûu Thatameth\n" +
                "  ■ Kanna Cavalies\n" +
                "  ■ Kashya Hyrenya\n" +
                "  ■ Kei Hyrenya\n" +
                "  ■ KuYu Cavalies\n" +
                "  ■ Maskaï Hitch\n" +
                "  ■ Maureen Mojito\n" +
                "  ■ Midoraï Hitch\n" +
                "  ■ Morgiana Cavalies\n" +
                "  ■ Nagoramatio Ariana-Fergonez\n" +
                "  ■ Nikolaï Hitch\n" +
                "  ■ Ravenn Cavalies\n" +
                "  ■ Rika Atalante\n" +
                "  ■ Sheele Cavalies\n" +
                "  ■ Veldo Cavalies\n" +
                "  ■ Yoshisuu Cavalies\n" +
                "  ■ Yuu Cavalies\n" +
                "  ■ William Cavalies\n" +
                "  ■ Wrong Sheele\n" +
                "  ■ Amalia Hyvernn\n \n" +

                "Personnages de Fatehard (2):\n" +
                "  ■ Math Michelle\n" +
                "  ■ Aliédad Cavalies\n \n" +

                "Personnages de Hypix (3):\n" +
                "  ■ Kass Horton\n" +
                "  ■ Asarim Horton\n" +
                "  ■ Terry Touère\n \n" + //

                "Personnages de KuYuDo (3):\n" +
                "  ■ Kudo\n" +
                "  ■ Yudo\n" +
                "  ■ Gaia\n \n" +

                "Personnages de RelGainSliDe (1):\n" +
                "  ■ Christopher Terrones\n \n" +

                "Personnages de Sully (1):\n" +
                "  ■ Sully Albatrum\n \n" +

                "Personnages de Wellarkel (1):\n" +
                "  ■ Anoriel Aqualis\n \n" +

                "Personnages de Jeffaria (1):\n" +
                "  ■ Raizan Dymen\n \n" +

                "Personnages de Mathsky (6):\n" +
                "  ■ MathSpirit\n" +
                "  ■ Nerias\n" +
                "  ■ Mahito Sukeruton\n" +
                "  ■ Gōsutookami\n" +
                "  ■ Jessica Grey\n" +
                "  ■ Trinity Grey\n \n" +

                "Personnages de Izzumaki (2):\n" +
                "  ■ Tidus Ragnarok\n" +
                "  ■ Matthias Garcia").queue();
    }

    @Command(name = "character",
            description = "Affiche la fiche d'un personnage")
    public void character(Message message) {
        String content = message.getContentStripped();
        String[] args = content.split(" ");

        if (args.length == 2) {
            message.getChannel().sendMessage("Mmh, ce n'est pas comme ça que fonctionne cette commande. Essayez `.. character <prénom du personnage>`.").queue();
            return;
        }

        StringBuilder charName = new StringBuilder();

        for (int i = 2; i < args.length; i++) {
            charName.append(args[i]);

            if (i > 2)
                charName.append(" ");
        }

        Character character = Amiria.getCharacterManager().getCharacter(charName.toString());

        if (character == null) {
            message.getChannel().sendMessage("Je ne parviens a trouver le personnage '" + charName.toString() + "'.").queue();
            return;
        }

        StringBuilder fiche = new StringBuilder();

        if (character.lastName() != null)
            fiche.append("**NOM FAMILIAL:** ").append(character.lastName()).append("\n");

        if (character.name() != null)
            fiche.append("**PRÉNOM:** ").append(character.name()).append("\n");

        if (character.title() != null)
            fiche.append("**TITRE:** ").append(character.title()).append("\n");

        if (character.race() != null)
            fiche.append("**RACE:** ").append(character.race()).append("\n");

        if (character.birth() != null && character.age() != null) {
            fiche.append("**ÂGE:** ").append(character.age()).append(" (").append(character.birth()).append(")\n");
        } else {
            if (character.birth() != null)
                fiche.append("**ANNIVERSAIRE:** ").append(character.birth()).append("\n");

            if (character.age() != null)
                fiche.append("**ÂGE:** ").append(character.age()).append("\n");
        }

        if (character.sex() != null)
            fiche.append("**SEXE:** ").append(character.sex()).append("\n");

        if (character.floor() != -1)
            fiche.append("**ÉTAGE:** ").append(character.floor()).append("\n");

        if (character.specialization() != null)
            fiche.append("**SPÉCIALISATION:** ").append(character.specialization()).append("\n");

        if (character.weapon() != null)
            fiche.append("**ARME:** ").append(character.weapon()).append("\n");

        if (character.secondaryWeapon() != null)
            fiche.append("**ARME SECONDAIRE:** ").append(character.secondaryWeapon()).append("\n");

        if (character.characterTraits() != null)
            fiche.append("**TRAITS DE CARACTÈRES:** ").append(character.characterTraits()).append("\n");

        if (character.aspect() != null)
            fiche.append("**ASPECT PHYSIQUE:** ").append(character.aspect()).append("\n");

        if (character.ownPower() != null)
            fiche.append("**POUVOIR PROPRE:** ").append(character.ownPower()).append("\n");

        if (character.state() != null)
            fiche.append("**STATUT:** ").append(character.state()).append("\n");

        if (character.owner() != null)
            fiche.append("**PROPRIÉTAIRE:** ").append(character.owner()).append("\n");

        if (character.ownerInspiration() != null)
            fiche.append("**INSPIRATIONS:** ").append(character.ownerInspiration()).append("\n");

        if (character.image() != null)
            fiche.append("**IMAGE REPRÉSENTATIVE:** ").append("https://share.alkanife.fr/amiria/").append(character.image());

        //TODO
        /*if (fiche.length() > 2000) {

        }*/

        message.getChannel().sendMessage(fiche).queue();
    }
}
