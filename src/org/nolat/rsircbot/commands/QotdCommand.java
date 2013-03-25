package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;

public class QotdCommand extends Command {

    public QotdCommand() {
        super("qotd");
        setArgString("<message>");
        setHelpMessage("Sets the bot QOTD that is displayed to everyone who joins");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        if (message.length() > 7) {
            String qotd = message.substring("!qotd ".length(), message.length());
            bot.getSettings().setQotd(channel, qotd);
            bot.sendMessage(channel, executor, "QOTD updated.", this);
        } else {
            bot.sendMessage(channel, executor, "QOTD not long enough.", this);
        }
    }
}
