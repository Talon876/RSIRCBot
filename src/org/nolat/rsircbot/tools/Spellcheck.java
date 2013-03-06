package org.nolat.rsircbot.tools;

import java.util.HashMap;
import java.util.Map;

public class Spellcheck {

    private static HashMap<String, String[]> corrections = new HashMap<String, String[]>();

    static {
        //what to correct to is the key to it's array of misspellings/abbrevs
        corrections.put("attack", new String[] { "atk", "att" });
        corrections.put("strength", new String[] { "str" });
        corrections.put("defence", new String[] { "defnse", "def" });
        corrections.put("ranged", new String[] { "range", "ranging" });
        corrections.put("prayer", new String[] { "pray" });
        corrections.put("magic", new String[] { "mage" });
        corrections.put("runecraft", new String[] { "runecrafting", "rc", "rcing" });
        corrections.put("construction", new String[] { "con", "cons" });
        corrections.put("hitpoints", new String[] { "health", "hp", "life", "lifepoints" });
        corrections.put("agility", new String[] { "agi", "agil" });
        corrections.put("herblore", new String[] { "herblaw", "herb", "herbs" });
        corrections.put("thieving", new String[] { "theiving", "thief", "thieve", "thv", "stealing" });
        corrections.put("crafting", new String[] { "craft" });
        corrections.put("fletching", new String[] { "fletch" });
        corrections.put("slayer", new String[] { "slay" });
        corrections.put("hunter", new String[] { "hunting", "hunt" });
        corrections.put("mining", new String[] { "minin", "mine" });
        corrections.put("smithing", new String[] { "smelting", "smithing", "smithin", "smith" });
        corrections.put("fishing", new String[] { "fish", "fishin" });
        corrections.put("cooking", new String[] { "cook", "cookin" });
        corrections.put("firemaking", new String[] { "fm", "fming", "fire" });
        corrections.put("woodcutting", new String[] { "woodcut", "wcing", "wc" });
        corrections.put("farming", new String[] { "farm" });
        corrections.put("overall", new String[] { "everything", "all" });
    }

    /**
     * Used to correct the spelling or expand an abbreviation of a word. Works with most common skill names
     * 
     * @param userSkill
     *            the user spelled word to correct
     * @return the corrected word, or the word passed in if no matches were found
     */
    public static String correctSpelling(String userSkill) {
        String correction = userSkill;
        for (Map.Entry<String, String[]> kv : corrections.entrySet()) {
            String corrected = kv.getKey();
            for (String attempt : kv.getValue()) {
                if (attempt.equalsIgnoreCase(userSkill)) {
                    correction = corrected;
                }
            }
        }
        return correction;
    }
}
