package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.data.BitcoinData;
import org.nolat.rsircbot.data.LookupException;

public class BitcoinCommand extends Command {

    public BitcoinCommand() {
        super("bitcoin");
        addAlternativeCommand("btc");
        setArgString("");
        setHelpMessage("Retrieves the current price of a bitcoin in USD");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        try {
            BitcoinData btc = new BitcoinData();
            bot.sendMessage(
                    channel,
                    executor,
                    "1 BitCoin sells for " + btc.getAvgValue() + " (Low: " + btc.getLowValue() + "; High: "
                            + btc.getHighValue() + ")", this);
        } catch (LookupException e) {
            bot.sendMessage(channel, executor, e.getMessage(), this);
        }
    }

}
