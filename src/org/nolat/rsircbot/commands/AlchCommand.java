package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.commands.actions.ActionResponder;
import org.nolat.rsircbot.commands.actions.AlchAction;
import org.nolat.rsircbot.commands.actions.AlchResponse;
import org.nolat.rsircbot.data.ItemSearch;
import org.nolat.rsircbot.data.LookupException;
import org.nolat.rsircbot.data.json.ItemData;
import org.nolat.rsircbot.tools.RSFormatter;

public class AlchCommand extends Command implements ActionResponder<AlchResponse>{

    private RSIRCBot bot;
    private String channel;
    private String executor;

    public AlchCommand() {
        super("alch");
        addAlternativeCommand("lach");
        setArgString("<item name>");
        setHelpMessage("Retrieves the high alch value of an item.");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        this.bot = bot;
        this.channel = channel;
        this.executor = executor;

        if (message.length() == "!alch".length()) {
            bot.sendMessage(channel, executor, getUsageString(), this);
        } else {
            String item = message.substring("!alch ".length(), message.length()).replaceAll(" ", "_");
            AlchAction alcher = new AlchAction(item, this);
        }
    }

    @Override
    public void onResponseSuccess(AlchResponse viewData) {
        //TODO replace with Velocity template
        bot.sendMessage(channel, executor, "" + viewData.itemName + " alchs for " + viewData.alchPrice + "gp", this);
    }

    @Override
    public void onResponseFailure(String error) {
        bot.sendMessage(channel, executor, error, this);
    }
}
