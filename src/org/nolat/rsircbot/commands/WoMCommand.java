package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.tools.WitQuery;

public class WoMCommand extends Command {

    public WoMCommand() {
        super("?");
        setArgString("<query>");
        setHelpMessage("Evaluates runescape related queries.");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        String query = message.substring("?? ".length(), message.length());
        System.out.println(query);

        try {
            WitQuery answer = new WitQuery(query, bot.getSettings().getWitToken());
            bot.sendMessage(channel, executor, answer.getAnswer(), this);
        } catch (Exception ex) {
            bot.sendMessage(channel, executor, ex.getMessage(), this);
        }

        //        if (!answer.isEmpty()) {
        //            bot.sendMessage(channel, executor, answer, this);
        //        } else {
        //            bot.sendMessage(channel, executor, "Unable to evaluate expression.", this);
        //        }
    }

}
