package fr.alkanife.amiria;

import de.cerus.jdasc.JDASlashCommands;
import fr.alkanife.amiria.characters.Characters;
import fr.alkanife.amiria.commands.*;
import fr.alkanife.amiria.lang.Lang;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class Events extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent readyEvent) {
        Logs.info("Connected");
        Logs.info("Fetching Discord informations...");

        JDA jda = readyEvent.getJDA();

        //search enyxia
        Guild enyxia = jda.getGuildById("341997146519633922");

        if (enyxia == null) {
            Logs.error("Enyxia was not found");
            jda.shutdown();
            return;
        }

        Amiria.setEnyxia(enyxia);
        Logs.info("Enyxia was found, " + enyxia.getMemberCount() + " members");

        //search megapolice role
        Object megapoliceID = Amiria.getConfigurationValues().get("megapolice-id");

        if (megapoliceID == null) {
            Logs.error("Invalid configuration: megapolice-id is null");
            jda.shutdown();
            return;
        }

        Role megapolice = enyxia.getRoleById(String.valueOf(megapoliceID));

        if (megapolice == null) {
            Logs.error("Enyxian Megapolice not found");
            jda.shutdown();
            return;
        }

        Amiria.setMegapolice(megapolice);
        Logs.info(megapolice.getName() + " role was found");

        //search english role
        Object englishID = Amiria.getConfigurationValues().get("english-id");

        if (englishID == null) {
            Logs.error("Invalid configuration: english-id is null");
            jda.shutdown();
            return;
        }

        Role english = enyxia.getRoleById(String.valueOf(englishID));

        if (english == null) {
            Logs.error("The cup of tea was not found");
            jda.shutdown();
            return;
        }

        Amiria.setEnglish(english);
        Logs.info(english.getName() + " role was found");

        //search #hrp channel
        Object hrpID = Amiria.getConfigurationValues().get("hrp-id");

        if (hrpID == null) {
            Logs.error("Invalid configuration: hrp-id is null");
            jda.shutdown();
            return;
        }

        TextChannel hrp = enyxia.getTextChannelById(String.valueOf(hrpID));

        if (hrp == null) {
            Logs.error("#hrp was not found");
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
        try {
            Characters.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

}
