package com.smile24es.lazy.utils;

public class MathsUtil {

    public static double getPercentage(int total, int value) {
        if (total == 0) {
            return 0;
        } else {
            return value/total;
        }
    }
}
