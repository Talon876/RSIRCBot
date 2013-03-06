package org.nolat.rsircbot.tools;

import java.util.HashMap;

public class Names {

    private static HashMap<String, String> names = new HashMap<String, String>();

    /**
     * Assigns a rsn to a irc nickname so they can use 'me' in commands that require a RSN.
     * 
     * @param nickname
     *            the irc nickname
     * @param rsn
     *            the rsn
     */
    public static void addName(String nickname, String rsn) {
        names.put(nickname, rsn);
    }

    /**
     * Gets the rsn assigned to the irc nickname. Or returns the irc nickname if no match is found
     * 
     * @param nickname
     *            the nickname to check for
     * @return the rsn assigned to the given nickname, or the nickname if no match is found
     */
    public static String getName(String nickname) {
        String rsn = names.get(nickname);
        if (rsn == null) {
            rsn = nickname;
        }
        return rsn;
    }

    /**
     * Use this method everywhere a username is being parsed as an argument. <br>
     * If the username given is 'me', it will expand to the user's set RSN. <br>
     * If the user doesn't have an RSN assigned to 'me', it will return their nickname. <br>
     * If the username is not 'me', it will simply return the username. <br>
     * 
     * @param username
     *            the RSN to be processed
     * @param nickname
     *            the nickname of the sender
     * @return the correct username to use
     */
    public static String processUsername(String username, String nickname) {
        if (username.equalsIgnoreCase("me")) {
            return getName(nickname);
        } else {
            return username;
        }
    }
}
