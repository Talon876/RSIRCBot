package org.nolat.rsircbot.settings.json;

import java.util.ArrayList;
import java.util.List;

public class BotSettings {
    private String name;
    private String server;
    private int port;
    private boolean debug;
    private String broadcast_tag;
    private String wit_token;
    private int message_count;
    private int command_count;
    private List<Channel> channels;
    private List<User> users;
    private List<String> feedback;

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

    public String getBroadcastTag() {
        return broadcast_tag;
    }

    public String getWitToken() {
        return wit_token;
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

    public void removeChannel(String channel) {
        channels.remove(getChannel(channel));
    }

    public int getMessageCount() {
        return message_count;
    }

    public int getCommandCount() {
        return command_count;
    }

    public void increaseMessageCount() {
        message_count++;
    }

    public void increaseCommandCount() {
        command_count++;
    }

    public void addFeedback(String fb) {
        if (feedback != null) {
            feedback.add(fb);
        } else {
            feedback = new ArrayList<String>();
            feedback.add(fb);
        }

    }
}
