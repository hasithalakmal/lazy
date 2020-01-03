package com.smile.lazy.beans.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.Serializable;

public class LazyApiCallResponse implements Serializable {

    private CloseableHttpResponse closeableHttpResponse;
    private long responseTime;
    private String responseBody;

    public LazyApiCallResponse(CloseableHttpResponse closeableHttpResponse) {
        this.closeableHttpResponse = closeableHttpResponse;
    }

    public LazyApiCallResponse(CloseableHttpResponse closeableHttpResponse, long responseTime) {
        this.closeableHttpResponse = closeableHttpResponse;
        this.responseTime = responseTime;
    }

    public LazyApiCallResponse(CloseableHttpResponse closeableHttpResponse, long responseTime, String responseBody) {
        this.closeableHttpResponse = closeableHttpResponse;
        this.responseTime = responseTime;
        this.responseBody = responseBody;
    }

    public CloseableHttpResponse getCloseableHttpResponse() {
        return closeableHttpResponse;
    }

    public void setCloseableHttpResponse(CloseableHttpResponse closeableHttpResponse) {
        this.closeableHttpResponse = closeableHttpResponse;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LazyApiCallResponse that = (LazyApiCallResponse) o;

        return new EqualsBuilder()
              .append(responseTime, that.responseTime)
              .append(responseBody, that.responseBody)
              .append(closeableHttpResponse, that.closeableHttpResponse)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(closeableHttpResponse)
              .append(responseTime)
              .append(responseBody)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "LazyResponse{" +
              "closeableHttpResponse=" + closeableHttpResponse +
              ", responseTime=" + responseTime +
              ", responseBody=" + responseBody +
              '}';
    }
}
