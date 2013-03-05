package org.nolat.rsircbot;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

public class Main {

    public static void main(String[] args) {

        try {
            JSAP jsap = new JSAP();

            FlaggedOption nameOpt = new FlaggedOption("name");
            nameOpt.setStringParser(JSAP.STRING_PARSER);
            nameOpt.setDefault("Guthiiiiiiiix");
            nameOpt.setShortFlag('n');
            nameOpt.setLongFlag("name");
            nameOpt.setHelp("The name of the bot");
            jsap.registerParameter(nameOpt);

            FlaggedOption channelOpt = new FlaggedOption("channel");
            channelOpt.setStringParser(JSAP.STRING_PARSER);
            channelOpt.setShortFlag('c');
            channelOpt.setLongFlag("channel");
            channelOpt.setHelp("The name of the channel the bot should join");
            channelOpt.setRequired(true);
            jsap.registerParameter(channelOpt);

            FlaggedOption serverOpt = new FlaggedOption("server");
            serverOpt.setStringParser(JSAP.STRING_PARSER);
            serverOpt.setShortFlag('s');
            serverOpt.setLongFlag("server");
            serverOpt.setHelp("The server to connect to");
            serverOpt.setDefault("irc.swiftirc.net");
            jsap.registerParameter(serverOpt);

            FlaggedOption portOpt = new FlaggedOption("port");
            portOpt.setStringParser(JSAP.INTEGER_PARSER);
            portOpt.setShortFlag('p');
            portOpt.setLongFlag("port");
            portOpt.setHelp("The port number to connect to the server with");
            portOpt.setDefault("6667");
            jsap.registerParameter(portOpt);

            JSAPResult config = jsap.parse(args);
            if (config.success()) {
                String name = config.getString("name").replaceAll(" ", "_");
                String channel = config.getString("channel");
                String server = config.getString("server");
                int port = config.getInt("port");

                RSIRCBot bot = new RSIRCBot(name, server, port, channel);
                bot.setVerbose(true);
            } else {
                System.out.println("Usage: java -jar RSIRCBot.jar [options]");
                System.out.println("Example: java -jar RSIRCBot.jar --name \"Guthix\" -c #some_channel ");
                System.out.println("\nList of options:");
                System.out.println(jsap.getHelp());
            }
        } catch (JSAPException e) {
            e.printStackTrace();
        }
    }
}
