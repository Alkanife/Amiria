package fr.alkanife.amiria;

import de.cerus.jdasc.interaction.Interaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logs {

    public static List<String> logs = new ArrayList<>();

    public static void info(String message) {
        System.out.println("\033[0;36m[amiria]- " + Utils.formatDate(new Date()) + "- " + message + "\033[0m");
        logs.add(Utils.formatDate(new Date()) + "- " + message);
    }

    public static void command(Interaction interaction) {
        command(interaction, null);
    }

    public static void command(Interaction interaction, String argument) {
        if (argument == null)
            info(interaction.getMember().getEffectiveName() + " executed the command '" + interaction.getCommandName() + "'");
        else
            info(interaction.getMember().getEffectiveName() + " executed the command '" + interaction.getCommandName() + " " + argument + "'");
    }

}
