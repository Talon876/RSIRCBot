package org.nolat.rsircbot.commands.actions;

import org.nolat.rsircbot.RSIRCBot;
import org.pircbotx.Channel;

public class BotStatsAction extends Action {

    public BotStatsAction(RSIRCBot bot, ActionResponder responder) {
        super(responder);

        String formattedMessage = "";
        int numUsers = 0;
        for (Channel chan : bot.getBot().getUserChannelDao().getAllChannels()) {
            numUsers += chan.getUsers().size() - 1; //subtract 1 so it doesn't count itself
        }
        this.responder.onResponseSuccess(
                new BotStatsResponse(
                        bot.getBot().getUserChannelDao().getAllChannels().size(),
                        numUsers,
                        bot.getSettings().getMessageCount(),
                        bot.getSettings().getCommandCount()
                )
        );
    }
}
