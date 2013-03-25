package org.nolat.rsircbot.commands;

import org.nolat.rsircbot.RSIRCBot;

/**
 * This command is only for reference/template purposes. It is not loaded in to the bot.
 */
public class SampleCommand extends Command {

    //NOTE! Be sure to add an instance of your command in the Command.java static constructor or the bot won't recognize it

    public SampleCommand() {
        super("sample"); //super call contains main command name
        addAlternativeCommand("samp"); //add alternative commands that will also activate this command
        setArgString("<required arg> [optional arg]"); //set the arg string displayed in the help menu
        setHelpMessage("A sample command."); //brief description of the command that's displayed in the help message
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        String[] args = message.split(" "); //common to split the message up to parse args. args[0] is the command itself including the '!'
        if (args.length != 3) { // check if the argument list isn't the right size
            bot.sendMessage(channel, executor, getUsageString(), this); //send the command's usage string
        } else {
            //do the command here
            bot.sendMessage(channel, executor, "Message", this); //send messages back to the channel
        }
    }

}
