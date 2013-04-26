package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;

public class BroadcastCommand extends Command {

    public BroadcastCommand() {
        super("broadcast");
        setArgString("<message>");
        setHelpMessage("Broadcasts the message to every channel the bot is in. Requires the broadcast_tag to be in the message.");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        String broadcastMsg = message.substring("!broadcast".length());
        String broadcastTag = bot.getSettings().getBroadcastTag();
        if (broadcastMsg.contains(broadcastTag)) {
            for (String chan : bot.getChannels()) {
                bot.sendMessage(chan, "System Message: " + broadcastMsg.replace(broadcastTag, ""));
            }
        } else {
            bot.sendMessage(channel, executor, "Unable to broadcast message not containing the broadcast tag.", this);
        }
    }
}
