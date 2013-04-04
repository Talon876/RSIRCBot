package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.data.ItemSearch;
import org.nolat.rsircbot.data.LookupException;
import org.nolat.rsircbot.data.json.ItemData;
import org.nolat.rsircbot.tools.RSFormatter;

public class AlchCommand extends Command {

    public AlchCommand() {
        super("alch");
        addAlternativeCommand("lach");
        setArgString("<item name>");
        setHelpMessage("Retrieves the high alch value of an item.");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        if (message.length() == "!alch".length()) {
            bot.sendMessage(channel, executor, getUsageString(), this);
        } else {
            String item = message.substring("!alch ".length(), message.length()).replaceAll(" ", "_");

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
                    ItemSearch natureRuneS = null;
                    try {
                        natureRuneS = new ItemSearch("nature rune");
                    } catch (LookupException e) {
                        e.printStackTrace();
                    }

                    if (natureRuneS != null) {
                        int natPrice = natureRuneS.getMatchedItem().getPriceValue();
                        int itemPrice = matchedItem.getPriceValue();
                        int itemHighAlch = matchedItem.getHighAlchValue();

                        int costAll = (itemPrice + natPrice) - itemHighAlch;
                        int costJustNat = natPrice - itemHighAlch;
                        int costJustItem = itemPrice - itemHighAlch;
                        System.out.println("itemPrice is " + itemPrice + "; itemPriceString is "
                                + matchedItem.getPriceString());
                        bot.sendMessage(channel, executor,
                                matchedItem.getName() + " high alchs for " + matchedItem.getHighAlchString()
                                + " gp. (Assume ~" + RSFormatter.format(natPrice)
                                + "gp/nat and ~" + RSFormatter.format(itemPrice)
                                + "gp/" + matchedItem.getName() + ") Buying item & nat: "
                                + ((costAll > 0) ? "-" : "+")
                                + RSFormatter.format((costAll > 0) ? costAll : costAll * -1)
                                + " gp/alch. Buying just nat: "
                                + ((costJustNat > 0) ? "-" : "+")
                                + RSFormatter.format((costJustNat > 0) ? costJustNat : costJustNat * -1)
                                + " gp/alch.  Buying just item: "
                                + ((costJustItem > 0) ? "-" : "+")
                                + RSFormatter.format((costJustItem > 0) ? costJustItem : costJustItem * -1)
                                + " gp/alch.",
                                this);
                    } else {
                        bot.sendMessage(channel, executor,
                                matchedItem.getName() + " high alchs for " + matchedItem.getHighAlchString() + " gp.",
                                this);
                    }
                }
            }
        }
    }
}
