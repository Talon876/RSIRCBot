package org.nolat.rsircbot.commands;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.tools.Calculate;

public class CalcCommand extends Command {

    ScriptEngine engine;

    public CalcCommand() {
        super("calc");
        setArgString("<expression>");
        setHelpMessage("Evaluates simple math expressions");
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
        engine.put("calc", new Calculate());
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
            bot.sendMessage(channel, executor, "Result: " + answer, this);
        } else {
            bot.sendMessage(channel, executor, "Unable to evaluate expression.", this);
        }
    }

}
