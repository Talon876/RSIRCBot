package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.tools.Calculate;
import org.nolat.rsircbot.tools.RSFormatter;

public class XPForLevelCommand extends Command {

    public XPForLevelCommand() {
        super("xpforlevel");
        addAlternativeCommand("xfl");
        setHelpMessage("Calculates how much xp is needed for a certain level");
        setArgString("<level>");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        String[] args = message.split(" "); //0 is the command
        if (args.length != 2) {
            bot.sendMessage(channel, getUsageString());
        } else {
            try {
                int level = Integer.parseInt(args[1]);
                if (level > 1 && level < 121) {
                    int xpForLevel = Calculate.xpForLevel(level);

                    bot.sendMessage(channel, "Level " + level + " requires " + RSFormatter.format(xpForLevel) + " xp.");
                } else {
                    bot.sendMessage(channel, "That level is outside the valid range [2-120].");
                }
            } catch (NumberFormatException ex) {
                bot.sendMessage(channel, "That's not a number!");
            }
        }
    }

}
