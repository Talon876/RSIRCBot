package org.nolat.rsircbot.tools;

import java.util.Random;

public class Greetings {

    public static final String[] greetings = { "Ahoy", "G'day", "Greetings", "Hello", "Hey", "Hi", "Howdy", "Welcome",
        "Yo", "Sup" };
    private static final Random random = new Random();

    public static String getRandomGreeting() {
        return greetings[random.nextInt(greetings.length)];
    }
}
