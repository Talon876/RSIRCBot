package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
        setHelpMessage("Displays this help menu.");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        bot.sendMessage(channel, executor, "-----Available Commands-----", this);
        for (Command c : Command.commands) {
            if (c.getArgString().equals("")) {
                bot.sendMessage(channel, executor,
                        "!" + c.getCommand() + c.getArgString() + " - " + c.getHelpMessage(), this);
            } else {
                bot.sendMessage(channel, executor,
                        "!" + c.getCommand() + " " + c.getArgString() + " - " + c.getHelpMessage(), this);
            }
        }
        bot.sendMessage(channel, executor, "--------End Commands--------", this);
    }

}
