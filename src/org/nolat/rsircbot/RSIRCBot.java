package org.nolat.rsircbot;

import org.nolat.rsircbot.commands.Command;
import org.nolat.rsircbot.settings.Settings;
import org.nolat.rsircbot.settings.json.Channel;
import org.nolat.rsircbot.tools.Greetings;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

import java.io.IOException;

public class RSIRCBot extends ListenerAdapter<PircBotX> {

    private PircBotX bot;

    public static final String VERSION = "1.3.1a";

    private final Settings settings;

    public RSIRCBot(Settings settings) {
        this.settings = settings;
        Configuration.Builder<PircBotX> builder = new Configuration.Builder<>().
                setName(settings.getName()).
                setAutoNickChange(true).
                setMessageDelay(50).
                setServer(settings.getServer(), settings.getPort()).
                setAutoReconnect(true).
                addListener(this);
        for (Channel c : settings.getChannels()) {
            builder.addAutoJoinChannel(c.getName());
        }
        bot = new PircBotX(builder.buildConfiguration());
        try {
            System.out.println("Attempting to connect to " + settings.getServer() + ":" + settings.getPort());
            settings.save();
            bot.startBot();
        } catch (IOException e) {
            System.out.println("Couldn't start bot: " + e.getMessage());
            e.printStackTrace();
        } catch (IrcException e) {
            System.out.println("Couldn't start bot: " + e.getMessage());
            e.printStackTrace();
        }

    }


    @Override
    public void onDisconnect(DisconnectEvent<PircBotX> event) throws Exception {
        System.out.println("Why did this happen?");

    }

    @Override
    public void onKick(KickEvent<PircBotX> event) throws Exception {
        if (event.getRecipient().getNick().equalsIgnoreCase(bot.getNick())) {
            //we were kicked so remove this channel from the settings
            getSettings().removeChannel(event.getChannel().getName());
            System.out.println("We got kicked from #" + event.getChannel().getName() + " by " +
                    event.getUser().getNick() + " because " + event.getReason());
        }
    }

    @Override
    public void onInvite(InviteEvent<PircBotX> event) throws Exception {
        Channel c = new Channel(event.getChannel(), true, true, "Use !qotd to set the qotd or !toggle qotd to turn it off.");
        settings.addChannel(c);
        bot.sendIRC().joinChannel(event.getChannel());
    }

    @Override
    public void onJoin(JoinEvent<PircBotX> event) throws Exception {
        if (!event.getUser().getNick().equalsIgnoreCase(bot.getNick())) {
            onUserJoin(event.getChannel().getName(), event.getUser().getNick());
        } else {
            onBotJoin(event.getChannel().getName());
        }
    }

    private void onUserJoin(String channel, String user) {
        Channel c = settings.getChannel(channel);
        if (c.shouldDisplayGreeting()) {
            bot.sendIRC().message(channel, Greetings.getRandomGreeting() + " " + user
                    + ", type !help to view help for this bot. Send commands directly to the bot with /msg "
                    + bot.getNick() + " !help");
        }

        if (c.shouldDisplayQotd()) {
            bot.sendIRC().message(channel, "QOTD: " + c.getQotdMessage());
//            sendMessage(channel, "QOTD: " + c.getQotdMessage());
        }
    }

    private void onBotJoin(String channel) {
        Channel c = settings.getChannel(channel);
        bot.sendIRC().message(
                channel,
                Greetings.getRandomGreeting()
                        + " everybody! (Version: "
                        + VERSION
                        + "; Patch Notes: https://github.com/Talon876/RSIRCBot/blob/master/CHANGELOG.md ) Keep in mind this bot is alpha software so there may be sporadic behavior and odd bugs.");

        if (c.shouldDisplayQotd()) {
            bot.sendIRC().message(channel, "QOTD: " + c.getQotdMessage());
        }
    }

    @Override
    public void onMessage(MessageEvent<PircBotX> event) throws Exception {
        process(event.getChannel().getName(), event.getUser().getNick(), event.getMessage());
    }

    @Override
    public void onPrivateMessage(PrivateMessageEvent<PircBotX> event) throws Exception {
        process(event.getUser().getNick(), event.getUser().getNick(), event.getMessage());
    }

    public void sendMessage(String channel, String executor, String message, Command command) {
        if (command.isPrivateReply()) {
            bot.sendIRC().notice(executor, message);
        } else {
            bot.sendIRC().message(channel, message);
        }
    }

    private void process(String target, String sender, String message) {
        Command command = Command.getCommand(message);
        settings.increaseMessageCount();
        if (command != null) {
            CommandExecutor ce = new CommandExecutor(this, target, sender, message, command);
            new Thread(ce).start();
            settings.increaseCommandCount();
        } else {
            System.out.println(message + " wasn't a command");
        }
    }

    @Override
    public void onQuit(QuitEvent<PircBotX> event) throws Exception {
        purgeEmptyChannels(); //someone quit, so check for empty channels
    }

    @Override
    public void onPart(PartEvent<PircBotX> event) throws Exception {
        purgeEmptyChannels(); //someone left, so check for empty channels
    }

    public Settings getSettings() {
        return settings;
    }

    /**
     * Checks every channel the bot is in and leaves the channel if the only person in the channel is the bot.
     */
    public void purgeEmptyChannels() {

        for (org.pircbotx.Channel channel : bot.getUserChannelDao().getAllChannels()) {
            leaveIfEmpty(channel.getName(), channel.getUsers().size());
        }
    }

    @Override
    public void onUserList(UserListEvent<PircBotX> event) throws Exception {
        leaveIfEmpty(event.getChannel().getName(), event.getUsers().size());
    }

    /**
     * Checks if a channel only has 1 user (the bot) and if so, leaves it.
     *
     * @param channel the channel to check
     * @param userAmt the amount of users in the channel
     */
    private void leaveIfEmpty(String channel, int userAmt) {
        if (userAmt == 1) {
            System.out.println("We are the only user in " + channel + ", leaving.");
            bot.getUserChannelDao().getChannel(channel).send().part("This channel is empty");
            getSettings().removeChannel(channel); //remove channel from stored settings so it doesn't rejoin it later
        }
    }

    public PircBotX getBot() {
        return bot;
    }
}
