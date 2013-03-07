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
        String qotd = message.substring("!qotd ".length(), message.length());
        if (executor.equals("KingKarthas")) {
            bot.setQotd(qotd);
            bot.sendMessage(channel, "QOTD updated.");
        } else {
            bot.sendMessage(channel, "You can't update the QOTD.");
        }
    }

}
