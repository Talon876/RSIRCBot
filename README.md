Old School RuneScape IRC Bot
============================

This IRC bot is dedicated to only the Old School version of RuneScape. This means all hiscores, prices, xp, etc. will all be for Old School. In addition to RuneScape related commands, there are some commands which are useful for managing IRC.

I made this bot to run on the SwiftIRC network, but it can be used to join any IRC network. It's still in its early stages so there are bound to be bugs and frequent changes.

Main Features
--------

* Hiscore Retrieval
* Hiscore Comparison
* Combat Level Calculation
* Price Checking
* Basic Merchenting Abilities using Zybez's [OSRS Price Guide](http://forums.zybez.net/pages/2007-price-guide)
* Built in commands for leaving feedback
* and more

Bot Commands
--------

Here is a list of commands the bot currently knows:

    !hiscore <username> <skill> - Retrieves the hiscore data for the given username and skill
    !combat <username> - Calculates the user's combat level
    !level <level> - Calculates how much xp is needed for a certain level
    !map - Returns a link to an interactive RuneScape map
    !qotd <message> - Sets the bot QOTD that is displayed to everyone who joins
    !botstats - Displays irc bot statistics
    !toggle <service name> - Used to toggle services (greeting, qotd) on or off
    !feedback <message> - Used to send comments/suggestions to the developer.
    !broadcast <message> - Broadcasts the message to every channel the bot is in. Requires the broadcast_tag to be in the message.
    !compare <player1> <player2> <skillname> - Compare's a skill between two players.
    !rsn <name> - Assigns a RSN to your irc nickname. Allows you to use 'me' as a substitute for your RSN in commands that require a RSN.
    !price <item name> - Retrieves the current market value of an item
    !wts <item name> - Returns a list of people buying the specified item
    !wtb <item name> - Returns a list of people selling the specified item
    !calc <expression> - Evaluates simple math expressions
    !alch <item name> - Retrieves the high alch value of an item.

Calling a command using ! or . as a prefix will cause the bot to reply only to you. To get the bot to reply to the whole channel, call a command with @ as the prefix.

Get the bot to join your channel
--------------------------------
In order to invite Skill-Bot to your channel on irc.swiftirc.net, run the command  `/invite Skill-Bot #your_channel`.
Keep in mind that this is alpha software and will behave as such. This means there could be random disconnects, incorrect results, or any other strange behavior. If you notice anything out of the ordinary, please use the `!feedback` command to let me know what happened. Also feel free to use `!feedback` to leave comments or suggestions.


Hosting Yourself
----------------
Requirements:

* Java must be installed
* Know how to run jars from the command line

In order to host the bot yourself you will have to compile the source in to an executable jar (see below).
Then once you have the jar, see the How to Run section below to be able to run it.

How To Compile
--------------

Obtain the code with `git clone git@github.com:Talon876/RSIRCBot.git` or download [the zip file](https://github.com/Talon876/RSIRCBot/archive/master.zip).

CD in to the directory with build.xml

Run `ant jar` to build the executable jar.


How To Run
----------
Create a settings.json file then run the jar.

Settings File:

    {
      "name": "My Custom Skill-Bot",
      "server": "irc.swiftirc.net",
      "port": 6667,
      "debug": true,
      "message_count": 0,
      "command_count": 0,
      "broadcast_tag": "{this has to be in the broadcast message or else it won't be sent}",
      "channels": [
        {
          "name": "#testbot",
          "greeting": true,
          "qotd": {
            "message": "Sample qotd",
            "display": "true"
          }
        }
      ],
      "users": [
        {
          "name": "PZezima",
          "rsn": "zezima"
        }
      ]
    }


Settings Description:

|Key|Description|
|---|-----------|
|name| name of the irc bot|
|server | the server to connect to|
|port | the port to connect to the server on|
|debug | whether or not to print debug info|
|message_count | keeps track of the number of messages received|
|command_count | keeps track of the number of messages that were commands|
|broadcast_tag | a special string that is required to be in the broadcast message|
|channels | an array of channels to connect to and their individual settings|
|channel.name | the name of the channel to join|
|channel.greeting | whether or not to display the help greeting when people join the channel|
|channel.qotd.message | the qotd message displayed to users if its enabled|
|channel.qotd.display | whether or not to display the qotd when people join the channel|
|users | an array of users mapping irc nicknames to rsn names|
|user.name | the irc nickname of a user|
|user.rsn | the rsn for the user|

Running the jar:

    Usage: java -jar RSIRCBot.jar [options]
    Example: java -jar RSIRCBot.jar -f settings.json

    Help:
      [-h|--help]
            Displays this help message

      [(-f|--file) <file>]
            The path to the file to load settings from (default: settings.json)


Known Problems
--------------
*  Have to manually enter underscores in RSNs

Future Development
------------------

* Quest dependency data (for example !requirements recipe for disaster could return something like this: http://i4.minus.com/iHFTgwcFIMJKA.png)
* Basic skill calculator features
* More intelligent command/argument parsing that allows for quoted arguments, defaults, and possibly switches.