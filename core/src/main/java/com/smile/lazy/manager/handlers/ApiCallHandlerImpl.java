package com.smile.lazy.manager.handlers;

import com.smile.lazy.beans.executor.ApiCallExecutionData;
import com.smile.lazy.beans.response.LazyApiCallResponse;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.Header;
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
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static java.text.MessageFormat.format;
import static org.apache.http.impl.client.HttpClients.createDefault;

@Repository
public class ApiCallHandlerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCallHandlerImpl.class);

    public LazyApiCallResponse executeApiCall(ApiCall apiCall, ApiCallExecutionData apiCallExecutionData) throws LazyException {

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
            LazyApiCallResponse lazyApiCallResponse = new LazyApiCallResponse(response, (executionEndTime - executionStartTime), result);
            populateExecutionDat(apiCall, apiCallExecutionData, uri, lazyApiCallResponse);
            return lazyApiCallResponse;
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

    public void printExecutionData(ApiCallExecutionData apiCallExecutionData) throws LazyException {
        if (apiCallExecutionData == null) {
            final String message = "Provided api call execution data is null";
            LOGGER.error(message);
            throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.IO_EXCEPTION, message);
        }

        String requestData = format("\n\n-----------------------------------------------------------------Executing api call [{0}] - [{1}] \n",
              apiCallExecutionData.getApiCallId(), apiCallExecutionData.getApiCallName());
        requestData += format(getPrintKey("Http Method") + ": [{0}]\n", apiCallExecutionData.getHttpMethod());
        requestData += format(getPrintKey("Request URL") + ": [{0}]\n", apiCallExecutionData.getUrl().toString());
        if (!apiCallExecutionData.getHttpMethod().equals("GET")) {
            requestData += format(getPrintKey("Request Body") + ": [{0}]\n", apiCallExecutionData.getRequestBody());
        }
        if (apiCallExecutionData.getHeaderGroup() != null && !CollectionUtils.isEmpty(apiCallExecutionData.getHeaderGroup().getHeaders())) {
            requestData += format(getPrintKey("Header List") + ":\n");
            for (Header header : apiCallExecutionData.getHeaderGroup().getHeaders()) {
                requestData += format(getPrintKey("") + "[{0}:{1}]\n", header.getKey(), header.getValue());
            }
        }
        requestData += "\n\nExecuting actual API Call......\n\n";
        requestData += format(getPrintKey("Execution time") + ": [{0}] milli seconds\n", apiCallExecutionData.getResponse().getResponseTime());
        requestData += format(getPrintKey("HTTP status code") + ": [{0}]\n",
              apiCallExecutionData.getResponse().getCloseableHttpResponse().getStatusLine().getStatusCode());
        requestData += format(getPrintKey("Response") + ": [{0}]\n", apiCallExecutionData.getResponse().getResponseBody());
        requestData += "\n-----------------------------------------------------------------\n\n";
        LOGGER.info(requestData);


    }

    private String getPrintKey(String printableKey) {
        return StringUtils.rightPad(printableKey, 20);
    }

    private void populateExecutionDat(ApiCall apiCall, ApiCallExecutionData apiCallExecutionData, URI uri, LazyApiCallResponse lazyApiCallResponse) {
        apiCallExecutionData.setApiCallId(apiCall.getApiCallId());
        apiCallExecutionData.setApiCallName(apiCall.getApiCallName());
        apiCallExecutionData.setApiCallDescription(apiCall.getApiCallDescription());
        apiCallExecutionData.setApiCallDisplayId(apiCall.getApiCallDisplayId());
        apiCallExecutionData.setProtocol(apiCall.getProtocol());
        apiCallExecutionData.setHostName(apiCall.getHostName());
        apiCallExecutionData.setPort(apiCall.getPort());
        apiCallExecutionData.setContextPath(apiCall.getContextPath());
        apiCallExecutionData.setUri(apiCall.getUri());
        apiCallExecutionData.setQueryParams(apiCall.getQueryParams());
        apiCallExecutionData.setHeaderGroup(apiCall.getHeaderGroup());
        apiCallExecutionData.setHttpMethod(apiCall.getHttpMethod());
        apiCallExecutionData.setRequestBody(apiCall.getRequestBody());
        apiCallExecutionData.setUrl(uri);
        apiCallExecutionData.setResponse(lazyApiCallResponse);
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
