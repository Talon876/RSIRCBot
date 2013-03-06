package org.nolat.rsircbot.commands;

import java.io.IOException;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.data.HiscoreData;
import org.nolat.rsircbot.data.RankLevelXp;
import org.nolat.rsircbot.tools.Calculate;
import org.nolat.rsircbot.tools.Names;
import org.nolat.rsircbot.tools.RSFormatter;
import org.nolat.rsircbot.tools.Spellcheck;

public class HiscoreCommand extends Command {

    public HiscoreCommand() {
        super("hiscore");
        addAlternativeCommand("highscore");
        setArgString("<username> <skill>");
        setHelpMessage("Retrieves the hiscore data for the given username and skill");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        String[] args = message.split(" "); //0 is the command
        if (args.length != 3) {
            bot.sendMessage(channel, getUsageString());
        } else {
            String username = Names.processUsername(args[1], executor);
            String skill = Spellcheck.correctSpelling(args[2]);

            try {
                HiscoreData hiscores = new HiscoreData(username);
                RankLevelXp rlx = hiscores.getDataForSkill(skill);
                if (rlx == null) {
                    bot.sendMessage(channel, "Unable to parse " + skill + " information for " + username);
                } else {
                    if (skill.equalsIgnoreCase("overall")) {
                        bot.sendMessage(channel,
                                username + " has level " + rlx.getLevelString() + " " + skill + " (XP: "
                                        + rlx.getXpString() + "; Rank: "
                                        + rlx.getRankString() + ")");
                    } else {
                        bot.sendMessage(channel,
                                username + " has level " + rlx.getLevelString() + " " + skill + " (XP: "
                                        + rlx.getXpString() + "; Rank: "
                                        + rlx.getRankString() + ") +"
                                        + RSFormatter.format(Calculate.xpUntilLevelUp(rlx.getXp()))
                                        + " xp until level up.");
                    }
                }
            } catch (IOException e) {
                bot.sendMessage(channel, "Unable to retrieve hiscore information for " + username);
            }

        }
    }
}
