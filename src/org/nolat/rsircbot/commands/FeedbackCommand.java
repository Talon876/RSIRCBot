package org.nolat.rsircbot.commands;

import java.util.HashMap;

import org.nolat.rsircbot.RSIRCBot;

public class FeedbackCommand extends Command {

    private static final long FIVE_MINUTES_MS = 5 * 60 * 1000; //5 minutes * 60 seconds * 1000 ms
    private HashMap<String, Long> recentHistory;

    public FeedbackCommand() {
        super("feedback");
        setArgString("<message>");
        setHelpMessage("Used to send comments/suggestions to the developer.");
        recentHistory = new HashMap<String, Long>();
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {

        if (message.length() == "!feedback".length()) {
            bot.sendMessage(channel, executor, getUsageString(), this);
        } else {
            if (canLeaveFeedback(executor)) {
                String feedback = message.substring("!feedback".length());
                bot.getSettings().addFeedback(executor + " @ " + channel + ": " + feedback);
                bot.sendMessage(channel, executor, "Message received.", this);
                recentHistory.put(executor, System.currentTimeMillis());
            } else {
                bot.sendMessage(channel, executor, "Please wait five minutes between messages.", this);
            }

        }
    }

    /**
     * Determines if the specified IRC user is allowed to leave feedback. <br>
     * Checks the current time and when they last left feedback. <br>
     * Allow the user to leave feedback if they last left feedback over 20 minutes ago. <br>
     * <br>
     * Note: Has potential to be abused if a user were to repeatedly change their nickname.
     * 
     * @param user
     *            the nick of the IRC user
     * @return true if the user is allowed to leave feedback
     */
    private boolean canLeaveFeedback(String user) {
        if (recentHistory.get(user) == null) {
            return true; //if the user isn't in the recent history, they are allowed to leave feedback
        }
        long lastFeedback = recentHistory.get(user);
        return (System.currentTimeMillis() - lastFeedback) > FIVE_MINUTES_MS;
    }
}
