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
    !compare <player1> <player2> <skillname> - Compare's a skill between two players.
    !rsn <name> - Assigns a RSN to your irc nickname. Allows you to use 'me' as a substitute for your RSN in commands that require a RSN.
    !price <item name> - Retrieves the current market value of an item
    !wts <item name> - Returns a list of people buying the specified item
    !wtb <item name> - Returns a list of people selling the specified item
    !calc <expression> - Evaluates simple math expressions

Get the bot to join your channel
--------------------------------
If you want the bot to join your channel on irc.swiftirc.net but don't want to host it yourself you can try inviting it by typing `/invite Skill-Bot #some_channel`. However this will only work if I have my bot running which isn't necessarily 24/7 and it is prone to restarting on occasion while I develop it.


#Hosting Yourself
Requirements:

* Java must be installed
* Know how to run jars from the command line

In order to host the bot yourself you will have to compile the source in to an executable jar (see below).
Then once you have the jar, see the How to Use section below to be able to run it.

How To Compile
--------------

Obtain the code with `git clone git@github.com:Talon876/RSIRCBot.git`

CD in to the directory with build.xml

Run `ant jar` to build the executable jar.


How To Run
----------

Usage: java -jar RSIRCBot.jar [options]
Example: java -jar RSIRCBot.jar --name "Guthix" -c #some_channel

List of options:
  [(-n|--name) <name>]
        The name of the bot (default: Skill-Bot)

  (-c|--channel) <channel>
        The name of the channel the bot should join

  [(-s|--server) <server>]
        The server to connect to (default: irc.swiftirc.net)

  [(-p|--port) <port>]
        The port number to connect to the server with (default: 6667)


Known Problems
--------------
*  The bot doesn't remember your RSN set with !rsn after restarting. This is on my to do list.
*  Have to manually enter underscores in RSNs

Future Development
------------------

* Make the bot persist data (qotd, rsn names)
* Quest dependency data (for example !requirements recipe for disaster could return something like this: http://i4.minus.com/iHFTgwcFIMJKA.png)
* Basic skill calculator features