package org.nolat.rsircbot.settings.json;

public class User {
    String name;
    String rsn;

    public User(String name, String rsn) {
        this.name = name;
        this.rsn = rsn;
    }

    public String getName() {
        return name;
    }

    public String getRSN() {
        return rsn;
    }

    public void setRSN(String rsn) {
        this.rsn = rsn;
    }
}
