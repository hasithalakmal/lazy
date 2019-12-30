package com.smile.lazy.beans.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.Serializable;

public class LazyResponse implements Serializable {

    private CloseableHttpResponse closeableHttpResponse;
    private long responseTime;

    public LazyResponse(CloseableHttpResponse closeableHttpResponse) {
        this.closeableHttpResponse = closeableHttpResponse;
    }

    public LazyResponse(CloseableHttpResponse closeableHttpResponse, long responseTime) {
        this.closeableHttpResponse = closeableHttpResponse;
        this.responseTime = responseTime;
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

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LazyResponse that = (LazyResponse) o;

        return new EqualsBuilder()
              .append(responseTime, that.responseTime)
              .append(closeableHttpResponse, that.closeableHttpResponse)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(closeableHttpResponse)
              .append(responseTime)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "LazyResponse{" +
              "closeableHttpResponse=" + closeableHttpResponse +
              ", responseTime=" + responseTime +
              '}';
    }
}
