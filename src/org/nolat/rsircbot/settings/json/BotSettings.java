package org.nolat.rsircbot.settings.json;

import java.util.List;

public class BotSettings {
    private String name;
    private String server;
    private int port;
    private boolean debug;
    private List<Channel> channels;
    private List<User> users;

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public boolean isDebug() {
        return debug;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getQotd(String channel) {
        Channel c = getChannel(channel);
        if (c != null) {
            return c.getQotdMessage();
        } else {
            return "";
        }
    }

    public Channel getChannel(String name) {
        for (Channel c : channels) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public User getUser(String name) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    public void addChannel(Channel c) {
        channels.add(c);
    }

    public void addUser(User u) {
        users.add(u);
    }
}
