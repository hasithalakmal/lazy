package com.smile.lazy.manager.Impl;

import com.smile.lazy.beans.response.LazyApiCallResponse;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.apache.http.impl.client.HttpClients.createDefault;

@Repository
public class ApiCallHandlerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCallHandlerImpl.class);

    public LazyApiCallResponse executeApiCall(ApiCall apiCall) throws LazyException {

        try (CloseableHttpClient httpClient = createDefault()) {
            URI uri = populateRequestUri(apiCall);
            HttpRequestBase request = null;
            if (apiCall.getHttpMethod().equals("GET")) {
                request = new HttpGet(uri);
            } else if (apiCall.getHttpMethod().equals("POST")) {
                request = new HttpPost(uri);
                ((HttpPost) request).setEntity(new StringEntity(apiCall.getRequestBody()));
            } else {
                throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED,
                      "Given HTTP method has not supported yet");
            }

            populateHeaders(apiCall, request);

            long executionStartTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(request);
            long executionEndTime = System.currentTimeMillis();

            String result = getResponseBody(response);
            return new LazyApiCallResponse(response, (executionEndTime - executionStartTime), result);
        } catch (URISyntaxException e) {
            final String message = "Invalid URI syntax";
            LOGGER.error(message, e);
            throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.URI_SYNTAX_ERROR, message);
        } catch (IOException e) {
            final String message = "IO exception occurred while executing the test suite";
            LOGGER.error(message, e);
            throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.IO_EXCEPTION, message);
        }


    }

    private void populateHeaders(ApiCall apiCall, HttpRequestBase request) {
        if (apiCall.getHeaderGroup() != null) {
            apiCall.getHeaderGroup().getHeaders().forEach(header -> {
                request.addHeader(header.getKey(), header.getValue());
            });
        }
    }

    //TODO - remove this method
    private String getResponseBody(CloseableHttpResponse response) throws IOException {
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

        } finally {
            response.close();
            return result;
        }
    }

    private URI populateRequestUri(ApiCall apiCall) throws URISyntaxException {
        final String apiPath = StringUtils.isBlank(apiCall.getContextPath()) ? "/".concat(apiCall.getUri()) :
              "/".concat(apiCall.getContextPath().concat("/").concat(apiCall.getUri()));

        return new URI(apiCall.getProtocol(), null, apiCall.getHostName(), apiCall.getPort(), apiPath,
              null, null);
    }

}
