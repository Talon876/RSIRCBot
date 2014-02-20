package org.nolat.rsircbot.commands;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.tools.Names;
import org.nolat.rsircbot.tools.WitQuery;
import org.nolat.rsircbot.tools.json.WitResponse;

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
            WitQuery question = new WitQuery(query, bot.getSettings().getWitToken());
            WitResponse answer = question.getWitResponse();
            switch(answer.getOutcome().getIntent()) {
                case "help_query":
                    bot.sendMessage(channel, executor, "Try asking any question related to runescape! e.g. How many maple logs from 60 to 70 wc? What are the rewards for desert treasure? How many pc points does full void cost?", this);
                    break;
                case "hiscore_cmd":
                    Gson gson = new Gson();
                    System.out.println("getEntities(): " + gson.toJson(answer.getOutcome().getEntities()));
                    System.out.println("get(rsn): " + gson.toJson(answer.getOutcome().getEntities().get("rsn")));

                    String username = Names.processUsername(bot, answer.getOutcome().getFirstEntityValueOr("rsn", "me"), executor);

                    String skill = answer.getOutcome().getEntities().get("skill_name").get("value").getAsString();
                    String newCommand = String.format(".hiscore %s %s", username.replaceAll(" ", "_"), skill);
                    System.out.println("Command: " + newCommand);
                    bot.process(channel, executor, newCommand);
                    break;
                case "compare_cmd":
                    username = answer.getOutcome().getFirstEntityValueOr("rsn", "me");
                    gson = new Gson();
                    System.out.println("getEntities(): " + gson.toJson(answer.getOutcome().getEntities()));
                    System.out.println("get(rsn): " + gson.toJson(answer.getOutcome().getEntities().get("rsn")));
                    System.out.println("Username: " + username);
//                    String username2 = Names.processUsername(bot, answer.getOutcome().getEntityValueOr(1, "rsn", "me"), executor);
//                    skill = answer.getOutcome().getFirstEntity("skill_name").getValue();
//                    newCommand = String.format(".compare %s %s %s", username.replaceAll(" ", "_"), username2.replaceAll(" ", "_"), skill);
//                    System.out.println("Command: " + newCommand);
//                    bot.process(channel, executor, newCommand);
                    break;
                default:
                    bot.sendMessage(channel, executor, "Unknown Intent: " + question.getShortAnswer(), this);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            bot.sendMessage(channel, executor, ex.getMessage(), this);
        }
    }

}
