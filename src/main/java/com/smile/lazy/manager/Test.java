package com.smile.lazy.manager;

import org.apache.commons.lang3.StringUtils;

public class Test {

    public static void main(String[] args) {
        String x = "   ";
        if (x == null || x.trim().equals("")) {
            System.out.println("TRUE");
        } else {
            int len = x.length();
            System.out.println("FALSE" + len);
        }

        if (StringUtils.isEmpty(x)) {
            System.out.println("ASASSSS");
        }
    }
}
