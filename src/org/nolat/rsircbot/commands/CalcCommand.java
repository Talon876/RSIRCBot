package org.nolat.rsircbot.commands;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.nolat.rsircbot.RSIRCBot;

public class CalcCommand extends Command {

    ScriptEngine engine;

    public CalcCommand() {
        super("calc");
        setArgString("<expression>");
        setHelpMessage("Evaluates simple math expressions");
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
    }

    @Override
    public void executeCommand(RSIRCBot bot, String channel, String executor, String message) {
        String expression = message.substring("!calc ".length(), message.length());
        String answer = "";
        try {
            answer = engine.eval(expression).toString();
        } catch (Exception ex) {

        }
        if (!answer.isEmpty()) {
            bot.sendMessage(channel, "Result: " + answer);
        } else {
            bot.sendMessage(channel, "Unable to evaluate expression.");
        }
    }

}
