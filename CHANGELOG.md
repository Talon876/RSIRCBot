Change Log <a id="Change_Log"></a>
----------
*  Version 1.2a
    * Reformatted WTS/WTB command output to be more compact.
    * New !botstats (!bs) command to show misc. bot statistics
    * New !feedback command to leave comments/suggestions for the developer.
    * Added !broadcast command to allow the bot hoster to send system messages.
*  Version 1.1.3a
    * Added !alch command for finding out the high alch value of items using their RS2007 item names
    * Improved Hiscore parsing reliability
*  Version 1.1.2a
    * Stability improvements
    * Commands run on a separate thread now
    * Wts/wtb replies with min(5, amount of offers) offers so it doesn't go out of bounds
    * Fixed infinite reconnects (hopefully)
    * ignores messages that start with a space to avoid string index out of bounds exceptions
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