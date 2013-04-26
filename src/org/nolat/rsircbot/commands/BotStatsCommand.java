package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;

public class BotStatsCommand extends Command {

    public BotStatsCommand() {
        super("botstats");
        addAlternativeCommand("bs");
        setHelpMessage("Displays irc bot statistics");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {

        String formattedMessage = "";
        int numUsers = 0;
        for (String chan : bot.getChannels()) {
            numUsers += bot.getUsers(chan).length - 1; //subtract 1 so it doesn't count itself
        }
        formattedMessage += "I am operating in "
                + bot.getChannels().length
                +
                " channels which contain a total of "
                + numUsers
                + " users (~"
                + (numUsers / bot.getChannels().length)
                + " users/channel). I have seen "
                + bot.getSettings().getMessageCount()
                + " messages, "
                + bot.getSettings().getCommandCount()
                + " were commands (~"
                + String.format("%.1f", 100 * ((float) bot.getSettings().getCommandCount() / (float) bot.getSettings()
                        .getMessageCount()))
                        + "%).";

        bot.sendMessage(channel, executor, formattedMessage, this);

    }
}
