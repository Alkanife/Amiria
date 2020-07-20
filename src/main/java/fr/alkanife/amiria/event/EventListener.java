package fr.alkanife.amiria.event;

import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.Log;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.Random;

public class EventListener implements net.dv8tion.jda.api.hooks.EventListener {

    @Override
    public void onEvent(@Nonnull GenericEvent genericEvent) {
        if (genericEvent instanceof ReadyEvent) {
            ReadyEvent event = (ReadyEvent) genericEvent;

            if (event.getGuildTotalCount() > 1) {
                Log.log("Je suis faite pour aider un seul et unique discord.");
                event.getJDA().shutdown();
                return;
            }

            if (event.getJDA().getGuildById(Amiria.getEnyxia()) == null) {
                Log.log("Je ne trouve pas ma maison...");
                event.getJDA().shutdown();
                return;
            }

            if (event.getJDA().getTextChannelById(Amiria.getHrp()) == null) {
                Log.log("Euh... Je trouve pas le salon #hrp !?");
                event.getJDA().shutdown();
                return;
            }

            Log.log("Connectée !");

        } else if (genericEvent instanceof GuildMessageReceivedEvent) {
            Amiria.getCommandHandler().handle(((GuildMessageReceivedEvent) genericEvent).getMessage());

        } else if (genericEvent instanceof GuildMemberJoinEvent) {
            GuildMemberJoinEvent event = (GuildMemberJoinEvent) genericEvent;

            if (!event.getGuild().getId().equals(Amiria.getEnyxia()))
                return;

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

            TextChannel hrp = event.getGuild().getTextChannelById(Amiria.getHrp());

            if (hrp != null)
                hrp.sendMessage(message.replaceAll("mention", event.getMember().getAsMention())).queue();

            event.getGuild().modifyMemberRoles(event.getMember(), event.getJDA().getRoleById("342983397217402880")).queue(aVoid
                    -> Log.log("Ajout du rôle par défaut à " + event.getMember().getUser().getName()));
        }
    }
}
