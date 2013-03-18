package org.nolat.rsircbot.settings.json;

import java.util.HashMap;

public class Channel {
    String name;
    boolean greeting;
    HashMap<String, String> qotd;

    public Channel(String name, boolean showGreeting, boolean showQotd, String qotdMsg) {
        this.name = name;
        greeting = showGreeting;
        qotd = new HashMap<String, String>();
        qotd.put("message", qotdMsg);
        qotd.put("display", Boolean.toString(greeting));
    }

    public String getQotdMessage() {
        return qotd.get("message");
    }

    public boolean shouldDisplayQotd() {
        return Boolean.parseBoolean(qotd.get("display"));
    }

    public String getName() {
        return name;
    }

    public boolean shouldDisplayGreeting() {
        return greeting;
    }

    public void setQotd(String message) {
        qotd.put("message", message);
    }

    public void toggleQotd() {
        boolean showQotd = !Boolean.parseBoolean(qotd.get("display"));
        qotd.put("display", Boolean.toString(showQotd));
    }

    public void toggleGreeting() {
        greeting = !greeting;
    }
}
