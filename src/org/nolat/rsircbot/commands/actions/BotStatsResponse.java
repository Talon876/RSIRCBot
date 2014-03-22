package org.nolat.rsircbot.commands.actions;

public class BotStatsResponse extends ActionResponse {

    public final int numChannels;
    public final int numUsers;
    public final int messageCount;
    public final int commandCount;

    public BotStatsResponse(int numChannels, int numUsers, int messageCount, int commandCount) {
        this.numChannels = numChannels;
        this.numUsers = numUsers;
        this.messageCount = messageCount;
        this.commandCount = commandCount;
    }

    public float getNumberOfUsersPerChannel() {
        return (numUsers / numChannels);
    }

    public String getCommandPercentage() {
        return String.format("%.1f", 100 * ((float) commandCount / (float) messageCount));
    }
}
