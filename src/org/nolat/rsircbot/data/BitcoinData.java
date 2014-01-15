package org.nolat.rsircbot.data;

import org.nolat.rsircbot.tools.URLReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BitcoinData {
    private static final String baseUrl = "http://data.mtgox.com/api/1/BTCUSD/ticker";

    private String lowValue = "";
    private String highValue = "";
    private String avgValue = "";

    public BitcoinData() throws LookupException {
        String jsonData = null;
        boolean failed = false;
        try {
            jsonData = URLReader.readUrl(baseUrl);
        } catch (Exception e) {
            System.out.println("Failed to retrieve bitcoin prices");
            e.printStackTrace();
            failed = true;
        }

        if (jsonData != null && !failed) {
            Gson gson = new Gson();
            JsonObject response = gson.fromJson(jsonData, JsonElement.class).getAsJsonObject().get("return")
                    .getAsJsonObject();
            lowValue = response.getAsJsonObject("low").get("display").getAsString();
            highValue = response.getAsJsonObject("high").get("display").getAsString();
            avgValue = response.getAsJsonObject("avg").get("display").getAsString();
        } else {
            throw new LookupException("Failed to retrieve bitcoin prices from mtgox.");
        }
        parseData();
    }

    private void parseData() {
        Gson gson = new Gson();
    }

    public String getLowValue() {
        return lowValue;
    }

    public String getHighValue() {
        return highValue;
    }

    public String getAvgValue() {
        return avgValue;
    }
}
