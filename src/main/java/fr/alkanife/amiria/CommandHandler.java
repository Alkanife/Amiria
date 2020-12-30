package fr.alkanife.amiria;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CommandHandler {

    private final Map<String, AmiriaCommand> commandMap;

    public CommandHandler() {
        commandMap = new HashMap<>();
    }

    public Collection<AmiriaCommand> getCommands() {
        return commandMap.values();
    }

    private int executedCommands = 0;

    public void registerCommands(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);

                method.setAccessible(true);

                AmiriaCommand simpleCommand = new AmiriaCommand(command.name(), command.description(), command.isAdmin(), object, method);

                commandMap.put(command.name(), simpleCommand);
            }
        }
    }

    public void handle(Message message) {
        if (message.getAuthor().isBot())
            return;

        if (message.getChannel().getId().equals(Amiria.getPanel().getId())) {
            executePanel(message);
            return;
        }

        execute(message);
    }

    public void execute(Message message) {
        try {
            String messageContent = message.getContentRaw();
            String[] splittedContent = messageContent.split(" ");

            if (message.getMentionedUsers().size() == 0)
                return;

            if (!message.getMentionedUsers().get(0).getId().equalsIgnoreCase(message.getJDA().getSelfUser().getId()))
                return;

            AmiriaCommand simpleCommand = getCommand(splittedContent[1].toLowerCase());

            if (simpleCommand == null)
                return;

            if (simpleCommand.isAdmin())
                return;

            Parameter[] parameters = simpleCommand.getMethod().getParameters();

            if (parameters.length != 1)
                return;

            if (parameters[0].getType().equals(Message.class)) {
                executedCommands += 1;
                simpleCommand.getMethod().invoke(simpleCommand.getObject(), message);
            }
        } catch (Exception exception) {
            Errors.handle(exception, message, true);
        }
    }

    public void executePanel(Message message) {
        try {
            String messageContent = message.getContentRaw();
            String[] splittedContent = messageContent.split(" ");

            AmiriaCommand simpleCommand = getCommand(splittedContent[0].toLowerCase());

            if (simpleCommand == null) {
                Logs.terminalResponse("> `Commande inconnue ! Rappel : ce salon est réservé aux rapports et aux commandes administratives, pour executer une commandes lambda, va dans un autre salon`");
                return;
            }

            if (!simpleCommand.isAdmin())
                return;

            Parameter[] parameters = simpleCommand.getMethod().getParameters();

            if (parameters.length != 1)
                return;

            if (parameters[0].getType().equals(Message.class)) {
                executedCommands += 1;
                simpleCommand.getMethod().invoke(simpleCommand.getObject(), message);
            }
        } catch (Exception exception) {
            Errors.handle(exception, null, true);
        }
    }

    private AmiriaCommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }

    public int getExecutedCommands() {
        return executedCommands;
    }
}