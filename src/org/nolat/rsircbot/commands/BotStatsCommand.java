package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;
import org.pircbotx.Channel;

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
        for (Channel chan : bot.getBot().getUserChannelDao().getAllChannels()) {
            numUsers += chan.getUsers().size() - 1; //subtract 1 so it doesn't count itself
        }
        formattedMessage += "I am operating in "
                + bot.getBot().getUserChannelDao().getAllChannels().size()
                +
                " channels which contain a total of "
                + numUsers
                + " users (~"
                + (numUsers / bot.getBot().getUserChannelDao().getAllChannels().size())
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
