package org.nolat.rsircbot.commands;

import java.util.List;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.data.ItemSearch;
import org.nolat.rsircbot.data.LookupException;
import org.nolat.rsircbot.data.json.ItemData;
import org.nolat.rsircbot.data.json.OfferData;
import org.nolat.rsircbot.tools.TimeUtils;

public class WTSCommand extends Command {

    public WTSCommand() {
        super("wts");
        setArgString("<item name>");
        setHelpMessage("Returns a list of people buying the specified item");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        if (message.length() == "!wts".length()) {
            bot.sendMessage(channel, executor, getUsageString(), this);
        } else {
            String item = message.substring("!wts ".length(), message.length()).replaceAll(" ", "_");

            ItemSearch results = null;
            try {
                results = new ItemSearch(item);
            } catch (LookupException e) {
                bot.sendMessage(channel, executor, e.getMessage(), this);
            }

            if (results != null) {
                ItemData matchedItem = results.getMatchedItem();
                if (matchedItem == null) {
                    bot.sendMessage(channel, executor,
                            "Search was too broad, try one of these: " + results.getSuggestionString(), this);
                } else {
                    List<OfferData> offers = matchedItem.getRecentBuyers(12);
                    if (offers.size() > 1) {

                        String formattedMessage = offers.size() + " people buying " + matchedItem.getName()
                                + ". Most recent: ";
                        for (int i = 0; i < Math.min(5, offers.size()); i++) {
                            formattedMessage += "[" + offers.get(i).getRSN() + " ~ Qty: "
                                    + offers.get(i).getQuantityString() + " ~ " + offers.get(i).getPriceString()
                                    + " gp/ea ~ " + TimeUtils.millisToLongDHMS(offers.get(i).msSincePosted())
                                    + " ago]" + ((i == 4 || i == offers.size()) ? "" : " | ");
                        }

                        bot.sendMessage(channel, executor, formattedMessage, this);
                    } else {
                        bot.sendMessage(channel, executor, "Could not find anyone buying " + matchedItem.getName(),
                                this);
                    }
                }
            }
        }
    }
}
