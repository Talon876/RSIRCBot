package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
        addAlternativeCommand("lolping");
        setHelpMessage("Tests if the bot is working. If it is, it will reply with 'pong!'");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        bot.sendMessage(channel, executor, "pong!", this);
    }
}
