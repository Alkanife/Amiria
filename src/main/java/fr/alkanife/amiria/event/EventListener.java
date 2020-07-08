package fr.alkanife.amiria.event;

import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.Log;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {

        if (event.getGuildTotalCount() > 1) {
            Log.log("Je suis faite pour aider un seul et unique discord.");
            event.getJDA().shutdown();
            return;
        }

        Guild home = event.getJDA().getGuildById("341997146519633922");

        if (home == null) {
            Log.log("Je ne trouve pas ma maison...");
            event.getJDA().shutdown();
            return;
        }

        Log.log("Connectée !");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Amiria.getCommandHandler().handle(event.getMessage());
    }
}
