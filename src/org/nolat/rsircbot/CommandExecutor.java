package org.nolat.rsircbot;

import org.nolat.rsircbot.commands.Command;

public class CommandExecutor implements Runnable {

    private final RSIRCBot bot;
    private final String target;
    private final String sender;
    private final String message;
    private final Command command;

    public CommandExecutor(final RSIRCBot bot, final String target, final String sender, final String message,
            final Command command) {
        this.bot = bot;
        this.target = target;
        this.sender = sender;
        this.message = message;
        this.command = command;
    }

    @Override
    public void run() {
        command.executeCommand(bot, target, sender, message);
    }

}
