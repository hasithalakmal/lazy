package com.smile24es.lazy.utils;

public class MathsUtil {

    private MathsUtil() {
        //This is a private constructor
    }

    public static Double getPercentage(int total, int value) {
        if (total == 0) {
            return 0.00;
        } else {
            double roundOff = Math.round(((double) value / (double)total)*100*100)/100.00;
            return  roundOff;
        }
    }
}
