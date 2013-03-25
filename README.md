Old School RuneScape IRC Bot
============================

This is a bot I made for use on the SwiftIRC network, but can be used to join any irc network. It's still in its early stages so there are bound to be bugs and frequent changes.

Main Features
--------

* Hiscore Retrieval
* Hiscore Comparison
* Combat Level Calculation
* Price Checking
* Basic Merchenting Abilities using [OSRS Price Guide](http://forums.zybez.net/pages/2007-price-guide)
* and more

Bot Commands
--------

Here is a list of commands the bot currently knows:

    !hiscore <username> <skill> - Retrieves the hiscore data for the given username and skill
    !combat <username> - Calculates the user's combat level
    !level <level> - Calculates how much xp is needed for a certain level
    !map - Returns a link to an interactive RuneScape map
    !qotd <message> - Sets the bot QOTD that is displayed to everyone who joins
    !toggle <service name> - Used to toggle services (greeting, qotd) on or off
    !compare <player1> <player2> <skillname> - Compare's a skill between two players.
    !rsn <name> - Assigns a RSN to your irc nickname. Allows you to use 'me' as a substitute for your RSN in commands that require a RSN.
    !price <item name> - Retrieves the current market value of an item
    !wts <item name> - Returns a list of people buying the specified item
    !wtb <item name> - Returns a list of people selling the specified item
    !calc <expression> - Evaluates simple math expressions

Calling a command using ! or . as a prefix will cause the bot to reply only to you. To get the bot to reply to the whole channel, call a command with @ as the prefix.

Get the bot to join your channel
--------------------------------
If you want the bot to join your channel on irc.swiftirc.net but don't want to host it yourself you can try inviting it by typing `/invite Skill-Bot #some_channel`. However this will only work if I have my bot running which isn't necessarily 24/7 and it is prone to restarting on occasion while I develop it.


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
      "name": "Skill-Bot",
      "server": "irc.swiftirc.net",
      "port": 6667,
      "debug": false,
      "channels": [
        {
          "name": "#testbot",
          "greeting": true,
          "qotd": {
            "message": "The qotd has not been set. Use !qotd to set it or !toggle qotd to turn it off.",
            "display": "true"
          }
        }
      ],
      "users": [
        {
          "name": "KingKarthas",
          "rsn": "King_Karthas"
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

Change Log <a id="Change_Log"></a>
----------
*  Version 1.1.1a
    * Bot won't rejoin channels it was kicked from.
    * Commands can now be called with @ and . as well as !
    * Commands now reply using a notice (privately) when called with a . or !
    * Commands reply publicly to the channel when called with @
*  Version 1.1a
    * Added channel specific qotd and settings
    * Saves RSN's set with !rsn between restarts
    * Rejoins channels it was in on restart
    * Removed command line flags in favor of reading settings from a file
    * Added !toggle command for toggling qotd/greeting
*  Version 1.0a
    * Initial release