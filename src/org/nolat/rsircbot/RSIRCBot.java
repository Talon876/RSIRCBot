package org.nolat.rsircbot;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;
import org.nolat.rsircbot.tools.Greetings;

public class RSIRCBot extends PircBot {

    public RSIRCBot(String name, String hostname, int port, String channel) {
        setName(name);
        setAutoNickChange(true);
        setMessageDelay(0);

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
                    + ", type !help to view help for this bot.");
        }
    }
}
