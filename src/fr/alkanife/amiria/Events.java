package fr.alkanife.amiria;

import de.cerus.jdasc.JDASlashCommands;
import fr.alkanife.amiria.characters.Characters;
import fr.alkanife.amiria.commands.*;
import fr.alkanife.amiria.lang.Lang;
import fr.alkanife.amiria.lang.Locale;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.Random;

public class Events extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent readyEvent) {
        Logs.info("Connected to Discord");
        Logs.info("Fetching Discord informations...");

        JDA jda = readyEvent.getJDA();

        //search enyxia
        Guild enyxia = jda.getGuildById("341997146519633922");

        if (enyxia == null) {
            Errors.error("Enyxia was not found");
            jda.shutdown();
            return;
        }

        Amiria.setEnyxia(enyxia);
        Logs.info("Enyxia was found, " + enyxia.getMemberCount() + " members");

        //search megapolice role
        Object megapoliceID = Amiria.getConfigurationValues().get("megapolice-id");

        if (megapoliceID == null) {
            Errors.error("Invalid configuration: megapolice-id is null");
            jda.shutdown();
            return;
        }

        Role megapolice = enyxia.getRoleById(String.valueOf(megapoliceID));

        if (megapolice == null) {
            Errors.error("Enyxian Megapolice not found");
            jda.shutdown();
            return;
        }

        Amiria.setMegapolice(megapolice);
        Logs.info(megapolice.getName() + " role was found");

        //search english role
        Object englishID = Amiria.getConfigurationValues().get("english-id");

        if (englishID == null) {
            Errors.error("Invalid configuration: english-id is null");
            jda.shutdown();
            return;
        }

        Role english = enyxia.getRoleById(String.valueOf(englishID));

        if (english == null) {
            Errors.error("The cup of tea was not found");
            jda.shutdown();
            return;
        }

        Amiria.setEnglish(english);
        Logs.info(english.getName() + " role was found");

        //search people role
        Object peopleID = Amiria.getConfigurationValues().get("people-id");

        if (peopleID == null) {
            Errors.error("Invalid configuration: people-id is null");
            jda.shutdown();
            return;
        }

        Role people = enyxia.getRoleById(String.valueOf(peopleID));

        if (people == null) {
            Errors.error("People not found");
            jda.shutdown();
            return;
        }

        Amiria.setPeople(people);
        Logs.info(people.getName() + " role was found");

        //search #hrp channel
        Object hrpID = Amiria.getConfigurationValues().get("hrp-id");

        if (hrpID == null) {
            Errors.error("Invalid configuration: hrp-id is null");
            jda.shutdown();
            return;
        }

        TextChannel hrp = enyxia.getTextChannelById(String.valueOf(hrpID));

        if (hrp == null) {
            Errors.error("#hrp was not found");
            jda.shutdown();
            return;
        }

        Amiria.setHrp(hrp);
        Logs.info("#" + hrp.getName() + " channel was found");

        //load languages
        Logs.info("Loading languages");
        Lang.load();

        //load characters
        Logs.info("Loading characters");
        Characters.load(false);

        //load commands
        Logs.info("Setting up commands");
        JDASlashCommands.initialize(jda, Amiria.getToken(), jda.getSelfUser().getId());

        new CharacterCommand();
        new CharactersCommand();
        new EnyxiaCommand();
        new HelpCommand();
        new SudoCommand();

        Logs.info("Ready!");
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent guildMemberJoinEvent) {
        String[] welcomeMessages = Lang.tl(Locale.FRENCH, "welcome-messages", guildMemberJoinEvent.getMember().getAsMention()).split("\n");

        int random = new Random().nextInt(welcomeMessages.length);

        String message = welcomeMessages[random];

        Amiria.getHrp().sendMessage(message).queue();

        guildMemberJoinEvent.getGuild().modifyMemberRoles(guildMemberJoinEvent.getMember(), Amiria.getPeople()).queue(aVoid
                -> Logs.info("Added " + guildMemberJoinEvent.getMember().getEffectiveName() + " to " + Amiria.getPeople().getName()));
    }

}
