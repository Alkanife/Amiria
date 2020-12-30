package fr.alkanife.amiria;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Random;

public class Events extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent readyEvent) {
        Logs.info("Connectée");
        Logs.info("Vérification des salons...");

        JDA jda = readyEvent.getJDA();

        /* CHECKING FOR CHANNELS */
        String enyxiaID = "341997146519633922";
        String hrpID = "342148162972024844";
        String panelID = "774715531025711134";

        Guild enyxia = jda.getGuildById(enyxiaID);

        if (enyxia == null) {
            Logs.error("Je ne trouve pas Enyxia.");
            jda.shutdown();
        }

        Amiria.setEnyxia(enyxia);

        TextChannel hrp = jda.getTextChannelById(hrpID);

        if (hrp == null) {
            Logs.error("Je ne trouve pas le #hrp.");
            jda.shutdown();
        }

        Amiria.setHRP(hrp);

        TextChannel panel = jda.getTextChannelById(panelID);

        if (panel == null) {
            Logs.error("Je ne le trouve pas mon terminal.");
            jda.shutdown();
        }

        Amiria.setPanel(panel);

        /* CHECKING FOR SNAPSHOT ACCOUNT */
        if (jda.getSelfUser().getId().equals("774714094338179082")) {
            Logs.info("Mode snapshot !");
            Amiria.setSnapshot(true);
        }

        Logs.info("Prête, terminal Discord operationnel");
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent messageReceivedEvent) {
        Amiria.getCommandHandler().handle(messageReceivedEvent.getMessage());
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent guildMemberJoinEvent) {
        String[] welcomeMessages = {
                "Bienvenue mention ! Mets toi bien.",
                "Incroyable ! mention nous a rejoint.",
                "Bienvenue dans la réalité mention, prends une chaise.",
                "Nous vous acceptons dans la confrérie, mention.",
                "mention joined the game.",
                "Salut mention, dit-moi, comment va ta mère ?",
                "<:OMG:367670846438768640> UN NOUVEL ARRIVANT !! ACCUEILLEZ mention COMME IL SE DOIT !",
                "<Désolé mention, ce message a dû être supprimé pour droits d'auteur.>"
        };

        int random = new Random().nextInt(welcomeMessages.length);

        String message = welcomeMessages[random];

        Amiria.getHRP().sendMessage(message.replaceAll("mention", guildMemberJoinEvent.getMember().getAsMention())).queue();

        guildMemberJoinEvent.getGuild().modifyMemberRoles(guildMemberJoinEvent.getMember(), guildMemberJoinEvent.getJDA().getRoleById("342983397217402880")).queue(aVoid
                -> Logs.info("Ajout du rôle par défaut à " + guildMemberJoinEvent.getMember().getUser().getAsTag()));
    }
}
