package org.nolat.rsircbot.tools;

import org.nolat.rsircbot.RSIRCBot;
import org.nolat.rsircbot.settings.json.User;

public class Names {

    /**
     * Assigns a rsn to a irc nickname so they can use 'me' in commands that require a RSN.
     * 
     * @param bot
     *            the bot with the settings to save the rsn to
     * 
     * @param nickname
     *            the irc nickname
     * @param rsn
     *            the rsn
     */
    public static void addName(RSIRCBot bot, String nickname, String rsn) {
        User u = bot.getSettings().getUser(nickname);
        if (u == null) {
            u = new User(nickname, rsn);
            bot.getSettings().addUser(u);
        } else {
            bot.getSettings().setRSN(nickname, rsn);
        }
    }

    /**
     * Gets the rsn assigned to the irc nickname. Or returns the irc nickname if no match is found
     * 
     * @param bot
     *            the bot with the settings to fetch the rsn from
     * @param nickname
     *            the nickname to check for
     * @return the rsn assigned to the given nickname, or the nickname if no match is found
     */
    public static String getName(RSIRCBot bot, String nickname) {
        User u = bot.getSettings().getUser(nickname);
        if (u == null) {
            return nickname;
        } else {
            return u.getRSN();
        }
    }

    /**
     * Use this method everywhere a username is being parsed as an argument. <br>
     * If the username given is 'me', it will expand to the user's set RSN. <br>
     * If the user doesn't have an RSN assigned to 'me', it will return their nickname. <br>
     * If the username is not 'me', it will simply return the username. <br>
     * 
     * @param bot
     *            the bot with the settings to fetch the rsn from
     * @param username
     *            the RSN to be processed
     * @param nickname
     *            the nickname of the sender
     * @return the correct username to use
     */
    public static String processUsername(RSIRCBot bot, String username, String nickname) {
        if (username.equalsIgnoreCase("me")) {
            return getName(bot, nickname);
        } else {
            return username;
        }
    }
}
