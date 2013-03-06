package org.nolat.rsircbot.commands;

import java.util.ArrayList;

import org.nolat.rsircbot.RSIRCBot;

public abstract class Command {
    public static ArrayList<Command> commands = new ArrayList<Command>();
    static {
        new PingCommand();
        new HelpCommand();
        new HiscoreCommand();
        new CombatCommand();
        new XPForLevelCommand();
        new MapCommand();
        new QotdCommand();
    }

    String command;

    String argString;

    ArrayList<String> alternativeCommands;

    String helpMessage;

    public Command(String command) {
        this.command = command;
        Command.commands.add(this);
        System.out.println("Registered command: " + command);
        alternativeCommands = new ArrayList<String>();
        argString = "";
    }

    public void addAlternativeCommand(String altCommand) {
        alternativeCommands.add(altCommand);
    }

    public String getCommand() {
        return command;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public void setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public String getArgString() {
        return argString;
    }

    public String getUsageString() {
        return "Usage: " + command + " " + argString;
    }

    public void setArgString(String argString) {
        this.argString = argString;
    }

    /**
     * Check if a string matches this command's string or an alternative string.
     * 
     * @param msg
     *            The message to check
     * @return true if this string matches the command
     */
    public boolean matchesString(String msg) {
        boolean matches = false;
        matches = matches || msg.equalsIgnoreCase(command);
        for (String cmd : alternativeCommands) {
            matches = matches || msg.equalsIgnoreCase(cmd);
        }
        return matches;
    }

    /**
     * Executes the command with the given arguments
     * 
     * @param bot
     *            the bot used to send messages
     * @param channel
     *            the channel the request came from
     * @param executor
     *            The name of the person who executed the command
     * @param message
     *            the unmodified message containing the command
     */
    public abstract void executeCommand(RSIRCBot bot, String channel, String executor, String message);

    /**
     * Finds the Command object
     * 
     * @param message
     * @return
     */
    public static Command getCommand(String message) {
        Command cmd = null;
        if (message.startsWith("!")) {
            String[] tokens = message.split(" ");
            String commandString = tokens[0].replace("!", "");
            for (Command c : commands) {
                if (c.matchesString(commandString)) {
                    cmd = c;
                    break;
                }
            }
        }
        return cmd;
    }
}
