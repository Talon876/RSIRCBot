package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.tools.Names;

public class RSNCommand extends Command {

    public RSNCommand() {
        super("rsn");
        addAlternativeCommand("runescapename");
        addAlternativeCommand("defname");
        setArgString("<name>");
        setHelpMessage("Assigns a RSN to your irc nickname. Allows you to use 'me' as a substitute for your RSN in commands that require a RSN.");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        String[] args = message.split(" ");
        if (args.length != 2) {
            bot.sendMessage(channel, executor, getUsageString(), this); //send the command's usage string
        } else {
            String rsn = args[1];
            Names.addName(bot, executor, rsn);
            bot.sendMessage(channel, executor, "Registered '" + rsn + "' to " + executor
                    + ". You may now use 'me' in place of RSN's in other commands.", this);
        }
    }
}
