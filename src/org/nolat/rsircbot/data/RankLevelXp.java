package org.nolat.rsircbot.data;

import org.nolat.rsircbot.tools.RSFormatter;

public class RankLevelXp {
    private String rank;
    private String level;
    private String xp;

    public RankLevelXp(String rank, String level, String xp) {
        this.rank = rank;
        this.level = level;
        this.xp = xp;
    }

    public int getRank() {
        return Integer.parseInt(rank);
    }

    public int getLevel() {
        return Integer.parseInt(level);
    }

    public int getXp() {
        return Integer.parseInt(xp);
    }

    public String getRankString() {
        return RSFormatter.format(getRank());
    }

    public String getLevelString() {
        return RSFormatter.format(getLevel());
    }

    public String getXpString() {
        return RSFormatter.format(getXp());
    }

    @Override
    public String toString() {
        return "Rank: " + getRankString() + "; Level: " + getLevelString() + "; XP: " + getXpString();
    }
}