package org.nolat.rsircbot.data.json;

import java.util.ArrayList;
import java.util.List;

import org.nolat.rsircbot.tools.RSFormatter;
import org.nolat.rsircbot.tools.TimeUtils;

public class ItemData {
    String average;
    String high_alch;
    List<HistoryData> history;
    String id;
    String image;
    String name;
    List<OfferData> offers;
    String recent_high;
    String recent_low;

    public int getHighAlchValue() {
        return Integer.parseInt(high_alch);
    }

    public String getHighAlchString() {
        return RSFormatter.format(high_alch);
    }

    public String getPriceString() {
        return RSFormatter.format(average);
    }

    public int getPriceValue() {
        return (int) Double.parseDouble(average);
    }

    public List<HistoryData> getHistory() {
        return history;
    }

    public String getName() {
        return name;
    }

    public List<OfferData> getOffers() {
        return offers;
    }

    public List<OfferData> getRecentOffers(int hours) {
        List<OfferData> recentOffers = new ArrayList<OfferData>();
        for (OfferData od : getOffers()) {
            if (od.msSincePosted() <= TimeUtils.ONE_HOUR * hours) {
                recentOffers.add(od);
            }
        }
        return recentOffers;
    }

    public List<OfferData> getRecentBuyers(int hours) {
        List<OfferData> buyers = new ArrayList<OfferData>();
        for (OfferData od : getRecentOffers(hours)) {
            if (!od.isSelling()) {
                buyers.add(od);
            }
        }
        return buyers;
    }

    public List<OfferData> getRecentSellers(int hours) {
        List<OfferData> sellers = new ArrayList<OfferData>();
        for (OfferData od : getRecentOffers(hours)) {
            if (od.isSelling()) {
                sellers.add(od);
            }
        }
        return sellers;
    }

    public String getRecentHighString() {
        return RSFormatter.format(recent_high);
    }

    public String getRecentLowString() {
        return RSFormatter.format(recent_low);
    }
}
