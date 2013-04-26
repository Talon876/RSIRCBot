package org.nolat.rsircbot.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.nolat.rsircbot.settings.json.BotSettings;
import org.nolat.rsircbot.settings.json.Channel;
import org.nolat.rsircbot.settings.json.User;
import org.nolat.rsircbot.tools.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Settings {

    File settingsFile;
    String jsonData;
    BotSettings settings;

    public Settings(String path) throws SettingsException {
        File f = new File(path);
        if (f.exists() && f.isFile()) {
            settingsFile = f;
            try {
                jsonData = FileReader.readFile(path);
            } catch (IOException e) {
                throw new SettingsException("Error reading file " + path + ": " + e.getMessage());
            }
            init();
        } else {
            throw new SettingsException("File does not exist: " + path);
        }
    }

    private void init() {
        Gson gson = new Gson();
        settings = gson.fromJson(jsonData, BotSettings.class);
    }

    /**
     * Reloads settings from disk
     */
    public void refresh() {
        try {
            jsonData = FileReader.readFile(settingsFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace(); //shouldn't happen
        }
        Gson gson = new Gson();
        settings = gson.fromJson(jsonData, BotSettings.class);
    }

    public void save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(settings);
        try {
            FileReader.writeFile(settingsFile.getAbsolutePath(), json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return settings.getName().replaceAll(" ", "_");
    }

    public String getServer() {
        return settings.getServer();
    }

    public int getPort() {
        return settings.getPort();
    }

    public List<Channel> getChannels() {
        return settings.getChannels();
    }

    public List<User> getUsers() {
        return settings.getUsers();
    }

    public Channel getChannel(String name) {
        refresh();
        return settings.getChannel(name);
    }

    public User getUser(String name) {
        refresh();
        return settings.getUser(name);
    }

    public boolean isDebugMode() {
        return settings.isDebug();
    }

    public void addChannel(Channel c) {
        settings.addChannel(c);
        save();
    }

    public void removeChannel(String channel) {
        settings.removeChannel(channel);
        save();
    }

    public void addUser(User u) {
        settings.addUser(u);
        save();
    }

    public void setQotd(String channel, String message) {
        settings.getChannel(channel).setQotd(message);
        save();
    }

    public void setRSN(String nick, String rsn) {
        settings.getUser(nick).setRSN(rsn);
        save();
    }

    public void toggleQotd(String channel) {
        getChannel(channel).toggleQotd();
        save();
    }

    public void toggleGreeting(String channel) {
        getChannel(channel).toggleGreeting();
        save();
    }

    public int getMessageCount() {
        return settings.getMessageCount();
    }

    public int getCommandCount() {
        return settings.getCommandCount();
    }

    public void increaseMessageCount() {
        settings.increaseMessageCount();
        save();
    }

    public void increaseCommandCount() {
        settings.increaseCommandCount();
        save();
    }

    public void addFeedback(String fb) {
        settings.addFeedback(fb);
        save();
    }
}
