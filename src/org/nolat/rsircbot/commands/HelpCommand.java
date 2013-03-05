package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
        setHelpMessage("Displays this help menu.");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        bot.sendMessage(channel, "-----Available Commands-----");
        for (Command c : Command.commands) {
            bot.sendMessage(channel, "!" + c.getCommand() + c.getArgString() + " - " + c.getHelpMessage());
        }
        bot.sendMessage(channel, "--------End Commands--------");
    }

}
