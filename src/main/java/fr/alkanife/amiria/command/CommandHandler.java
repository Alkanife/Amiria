package fr.alkanife.amiria.command;

import fr.alkanife.amiria.Log;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CommandHandler {

    private final Map<String, SimpleCommand> commandMap;

    public CommandHandler() {
        commandMap = new HashMap<>();
    }

    public Collection<SimpleCommand> getCommands() {
        return commandMap.values();
    }

    public int executedCommands = 0;

    public void registerCommands(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);

                method.setAccessible(true);

                SimpleCommand simpleCommand = new SimpleCommand(command.name(), command.description(), command.isAdmin(), object, method);

                commandMap.put(command.name(), simpleCommand);
            }
        }
    }

    public void handle(Message message) {
        try {
            String messageContent = message.getContentRaw();
            String[] splittedContent = messageContent.split(" ");

            if (message.getAuthor().isBot())
                return;

            if (message.getMentionedUsers().size() == 0)
                return;

            if (!message.getMentionedUsers().get(0).getId().equalsIgnoreCase(message.getJDA().getSelfUser().getId()))
                return;

            SimpleCommand simpleCommand = getCommand(splittedContent[1].toLowerCase());

            if (simpleCommand == null)
                return;

            if (simpleCommand.isAdmin()) {
                boolean allow = false;

                if (message.getMember() != null)
                    for (Role role : message.getMember().getRoles())
                        if (role.getId().equalsIgnoreCase("342968953011830784"))
                            allow = true;

                if (!allow) {
                    message.getChannel().sendMessage("Cette commande est réservée à la megapolice enyxienne.").queue();
                    return;
                }
            }

            Parameter[] parameters = simpleCommand.getMethod().getParameters();

            if (parameters.length != 1)
                return;

            if (parameters[0].getType().equals(Message.class)) {
                executedCommands += 1;
                simpleCommand.getMethod().invoke(simpleCommand.getObject(), message);
            }
        } catch (Exception exception) {
            Log.error(exception);
        }
    }

    private SimpleCommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }

}
