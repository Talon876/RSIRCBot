package org.nolat.rsircbot;

import org.nolat.rsircbot.settings.Settings;
import org.nolat.rsircbot.settings.SettingsException;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;

public class Main {

    public static void main(String[] args) {

        try {
            JSAP jsap = new JSAP();

            Switch switchOpt = new Switch("help");
            switchOpt.setShortFlag('h');
            switchOpt.setLongFlag("help");
            switchOpt.setHelp("Displays this help message");
            jsap.registerParameter(switchOpt);

            FlaggedOption fileOpt = new FlaggedOption("file");
            fileOpt.setStringParser(JSAP.STRING_PARSER);
            fileOpt.setShortFlag('f');
            fileOpt.setLongFlag("file");
            fileOpt.setHelp("The path to the file to load settings from");
            fileOpt.setDefault("settings.json");
            jsap.registerParameter(fileOpt);

            JSAPResult config = jsap.parse(args);
            if (config.success()) {

                if (config.getBoolean("help")) {
                    help(jsap);
                } else {
                    String filePath = config.getString("file");
                    Settings settings = new Settings(filePath);
                    RSIRCBot bot = new RSIRCBot(settings);
                    bot.setVerbose(settings.isDebugMode());
                    settings.save();
                }
            } else {
                help(jsap);
            }
        } catch (JSAPException e) {
            e.printStackTrace();
        } catch (SettingsException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void help(JSAP jsap) {
        System.out.println("Usage: java -jar RSIRCBot.jar [options]");
        System.out.println("Example: java -jar RSIRCBot.jar -f settings.json");
        System.out.println("\nHelp:");
        System.out.println(jsap.getHelp());
    }
}
