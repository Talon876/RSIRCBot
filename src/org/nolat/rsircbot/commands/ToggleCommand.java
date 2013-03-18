package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle");
        setArgString("<service name>");
        setHelpMessage("Used to toggle services (greeting, qotd) on or off");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        String[] args = message.split(" ");
        if (args.length != 2) {
            bot.sendMessage(channel, getUsageString());
        } else {
            String service = args[1].trim();

            if (service.equalsIgnoreCase("qotd")) {
                bot.getSettings().toggleQotd(channel);
                bot.sendMessage(channel, "Toggled qotd.");
            } else if (service.equalsIgnoreCase("greeting")) {
                bot.getSettings().toggleGreeting(channel);
                bot.sendMessage(channel, "Toggled greeting.");
            } else {
                bot.sendMessage(channel, "Service name not recognized. Recognized services: {qotd, greeting}");
            }
        }
    }
}
