package org.nolat.rsircbot.tools;

public class Calculate {

    public int xpForLevel(int level) {
        return 0;
    }

    public int levelFromXp(int xp) {
        return 0;
    }

    public int xpUntilLevelUp(int xp) {
        int level = levelFromXp(xp);
        int xpForNextLevel = xpForLevel(level + 1);
        return xpForNextLevel - xp;
    }
}
