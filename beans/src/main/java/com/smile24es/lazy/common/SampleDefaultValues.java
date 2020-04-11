package com.smile24es.lazy.common;

import com.smile24es.lazy.beans.DefaultValues;
import com.smile24es.lazy.beans.suite.Header;
import com.smile24es.lazy.beans.suite.HeaderGroup;

public class SampleDefaultValues {

    private SampleDefaultValues() {
        //This is a private constructor
    }

    public static DefaultValues createDefaultValues() {
        return new com.smile24es.lazy.beans.DefaultValues("http", "localhost", 8080, "lazy-api", createDefaultHeaderGroup(), "GET");
    }

    public static HeaderGroup createDefaultHeaderGroup() {
        HeaderGroup defaultHeaderGroup = new HeaderGroup("Simple Header Group");
        Header header1 = new Header("Accept Header", "accept", "application/json");
        Header header2 = new Header("Content type Header", "content-type", "application/json");
        defaultHeaderGroup.getHeaders().add(header1);
        defaultHeaderGroup.getHeaders().add(header2);
        return defaultHeaderGroup;
    }
}
