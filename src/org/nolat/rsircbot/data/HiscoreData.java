package org.nolat.rsircbot.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class HiscoreData {
    private static final String baseUrl = "http://sk.eliteazza.com/index_lite.ws?player=";
    private String username;
    private String[] skillNames = { "overall", "attack", "defence", "strength", "hitpoints",
            "ranged", "prayer", "magic", "cooking", "woodcutting", "fletching",
            "fishing", "firemaking", "crafting", "smithing", "mining", "herblore",
            "agility", "thieving", "slayer", "farming", "runecraft", "hunter", "construction" };

    private HashMap<String, RankLevelXp> hiscores = new HashMap<String, RankLevelXp>();

    public HiscoreData(String username) throws IOException {
        username = username.replaceAll(" ", "_");
        System.out.println("Fetching hiscore data for " + username);

        URL oracle = new URL(baseUrl + username);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        int c = 0;
        while ((inputLine = in.readLine()) != null) {
            String[] tokens = inputLine.split(",");
            if (tokens.length == 3) {
                RankLevelXp rlx = new RankLevelXp(tokens[0], tokens[1], tokens[2]);
                if (c <= skillNames.length) {
                    hiscores.put(skillNames[c], rlx);
                    System.out.println("Added " + skillNames[c] + ": " + rlx.toString());
                }
                c++;
            }
        }

    }

    public RankLevelXp getDataForSkill(String skill) {
        return hiscores.get(skill.toLowerCase());
    }
}
