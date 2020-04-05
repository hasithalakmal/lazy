package com.smile24es.lazy.utils;

import com.smile24es.lazy.beans.DefaultValues;
import com.smile24es.lazy.beans.suite.Header;
import com.smile24es.lazy.beans.suite.HeaderGroup;

public class SampleDefaultValues {

    private SampleDefaultValues() {
        //This is a private constructor
    }

    public static DefaultValues createDefaultValues() {
        return new com.smile24es.lazy.beans.DefaultValues("http", "localhost", 8080, "account-api", createDefaultHeaderGroup(), "GET");
    }

    public static HeaderGroup createDefaultHeaderGroup() {
        HeaderGroup defaultHeaderGroup = new HeaderGroup(1, "Simple Header Group");
        Header header1 = new Header(1, "Accept Header", "accept", "application/json");
        Header header2 = new Header(2, "Content type Header", "content-type", "application/json");
        defaultHeaderGroup.getHeaders().add(header1);
        defaultHeaderGroup.getHeaders().add(header2);
        return defaultHeaderGroup;
    }
}
