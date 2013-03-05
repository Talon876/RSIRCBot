package org.nolat.rsircbot.tools;

import java.text.DecimalFormat;

public class RSFormatter {
    public static final DecimalFormat formatter = new DecimalFormat("#,###");

    public static String format(int num) {
        return formatter.format(num);
    }

    public static String format(double num) {
        return formatter.format(num);
    }
}
