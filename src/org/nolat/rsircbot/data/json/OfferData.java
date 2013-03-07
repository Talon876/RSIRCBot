package org.nolat.rsircbot.data.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.nolat.rsircbot.tools.RSFormatter;

public class OfferData {
    String date;
    String price;
    String quantity;
    String rs_name;
    String selling;

    public boolean isSelling() {
        return selling.equals("1");
    }

    public String getDateString() {
        Date d = new Date(Long.parseLong(date) * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd hh:mm a (Z)");
        return sdf.format(d);
    }

    public Date getDate() {
        Date d = new Date(Long.parseLong(date) * 1000);
        return d;
    }

    public String getRSN() {
        return rs_name;
    }

    public String getQuantityString() {
        return RSFormatter.format(quantity);
    }

    public String getPriceString() {
        return RSFormatter.format(price);
    }

    public int msSincePosted() {
        Date posted = getDate();
        Date now = new Date();
        return (int) (now.getTime() - posted.getTime());
    }
}
