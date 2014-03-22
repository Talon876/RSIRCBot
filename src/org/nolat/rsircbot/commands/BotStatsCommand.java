package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.commands.actions.ActionResponder;
import org.nolat.rsircbot.commands.actions.BotStatsAction;
import org.nolat.rsircbot.commands.actions.BotStatsResponse;
import org.pircbotx.Channel;

public class BotStatsCommand extends Command implements ActionResponder<BotStatsResponse> {

    private RSIRCBot bot;
    private String channel;
    private String executor;

    public BotStatsCommand() {
        super("botstats");
        addAlternativeCommand("bs");
        setHelpMessage("Displays irc bot statistics");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        this.bot = bot;
        this.channel = channel;
        this.executor = executor;

        BotStatsAction botstats = new BotStatsAction(bot, this);

    }

    @Override
    public void onResponseSuccess(BotStatsResponse response) {
        //TODO replace with Velocity template
        bot.sendMessage(channel, executor,
                "I am operating in "
                        + response.numChannels
                        + " channels which contain a total of "
                        + response.numUsers
                        + " users (~"
                        + response.numChannels / response.messageCount
                        + " users/channel). I have seen "
                        + response.messageCount
                        + " messages, "
                        + response.commandCount
                        + " were commands (~"
                        + response.getCommandPercentage()
                        + "%).",
                this);
    }

    @Override
    public void onResponseFailure(String error) {
        bot.sendMessage(channel, executor, error, this);
    }
}
