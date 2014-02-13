package org.nolat.rsircbot.tools;

public class Calculate {

    public static int xpForLevel(int level) {
        double value = 0;
        for (int i = 1; i < level; i++) {
            value += Math.floor(i + 300f * Math.pow(2f, i / 7f));
        }
        value = (int) Math.floor(.25 * value);
        return (int) value;
    }

    public static int levelFromXp(int xp) {
        for (int i = 1; i <= 120; i++) {
            if (xpForLevel(i) > xp) {
                return i - 1;
            }
        }
        return -1;
    }

    public static int xpUntilLevelUp(int xp) {
        int level = levelFromXp(xp);
        int xpForNextLevel = xpForLevel(level + 1);
        return xpForNextLevel - xp;
    }

    public static int combatLevelRounded(int atk, int str, int def, int prayer, int range, int magic, int hp) {

        return (int) combatLevel(atk, str, def, prayer, range, magic, hp);
    }

    public static double combatLevel(int atk, int str, int def, int prayer, int range, int magic, int hp) {

        double baseLvl = (def + hp + Math.floor(prayer / 2)) * 0.25;

        double meleeLvl = (atk + str) * 0.325;
        double rangerLvl = Math.floor(range * 1.5) * 0.325;
        double mageLvl = Math.floor(magic * 1.5) * 0.325;

        baseLvl = baseLvl + Math.max(meleeLvl, (Math.max(rangerLvl, mageLvl)));

        return baseLvl;
    }
}
