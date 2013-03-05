package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;

public class MapCommand extends Command {

    public MapCommand() {
        super("map");
        setHelpMessage("Returns a link to an interactive RuneScape map");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        bot.sendMessage(channel, "An interactive map can be found at http://rsmap.nyro.net/");
    }

}
