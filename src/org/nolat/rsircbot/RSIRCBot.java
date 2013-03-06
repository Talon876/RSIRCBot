package org.nolat.rsircbot;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;
import org.nolat.rsircbot.commands.Command;
import org.nolat.rsircbot.tools.Greetings;

public class RSIRCBot extends PircBot {

    private String qotd = "No QOTD has been set, use !qotd to set it.";

    public RSIRCBot(String name, String hostname, int port, String channel) {
        setName(name);
        setAutoNickChange(true);
        setMessageDelay(50);

        try {
            connect(hostname, port);
        } catch (NickAlreadyInUseException e) {
            System.out.println("Nick was in use.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        }
        joinChannel(channel);
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {
        super.onJoin(channel, sender, login, hostname);
        System.out.println("sender: " + sender);
        System.out.println("nick: " + getNick());
        if (!sender.equalsIgnoreCase(getNick())) {
            sendMessage(channel, Greetings.getRandomGreeting() + " " + sender
                    + ", type !help to view help for this bot. Send commands directly to the bot with /msg "
                    + getNick() + " !help");
        } else {
            sendMessage(channel, Greetings.getRandomGreeting()
                    + " everybody! type !help to view help for this bot. Send commands directly to the bot with /msg "
                    + getNick() + " !help");
        }
        sendMessage(channel, "QOTD: " + qotd);
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        super.onMessage(channel, sender, login, hostname, message);
        process(channel, sender, message);
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {
        super.onPrivateMessage(sender, login, hostname, message);
        process(sender, sender, message);
    }

    private void process(String target, String sender, String message) {
        Command command = Command.getCommand(message);
        if (command != null) {
            command.executeCommand(this, target, sender, message);
        } else {
            System.out.println(message + " wasn't a command");
        }
    }

    public String getQotd() {
        return qotd;
    }

    public void setQotd(String qotd) {
        this.qotd = qotd;
    }

}
