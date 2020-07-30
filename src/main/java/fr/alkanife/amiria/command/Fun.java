package fr.alkanife.amiria.command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class Fun {

    @Command(name = "echo",
            isAdmin = true,
            isListed = false)
    public void echo(Message message) {
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
