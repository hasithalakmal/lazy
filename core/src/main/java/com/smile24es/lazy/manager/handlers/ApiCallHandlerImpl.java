package com.smile24es.lazy.manager.handlers;

import com.smile24es.lazy.beans.enums.HttpMethodEnum;
import com.smile24es.lazy.beans.executor.ApiCallExecutionData;
import com.smile24es.lazy.beans.response.LazyApiCallResponse;
import com.smile24es.lazy.beans.suite.ApiCall;
import com.smile24es.lazy.beans.suite.Header;
import com.smile24es.lazy.beans.suite.QueryParam;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyException;
import com.smile24es.lazy.utils.VariableManipulationUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
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
            if (apiCall.getHttpMethod().equals(HttpMethodEnum.GET.getValue())) {
                request = new HttpGet(uri);
            } else if (apiCall.getHttpMethod().equals(HttpMethodEnum.POST.getValue())) {
                request = new HttpPost(uri);
                ((HttpPost) request).setEntity(new StringEntity(apiCall.getRequestBody()));
            } else if (apiCall.getHttpMethod().equals(HttpMethodEnum.PUT.getValue())) {
                request = new HttpPut(uri);
                ((HttpPut) request).setEntity(new StringEntity(apiCall.getRequestBody()));
            } else if (apiCall.getHttpMethod().equals(HttpMethodEnum.PATCH.getValue())) {
                request = new HttpPatch(uri);
                ((HttpPatch) request).setEntity(new StringEntity(apiCall.getRequestBody()));
            } else if (apiCall.getHttpMethod().equals(HttpMethodEnum.DELETE.getValue())) {
                request = new HttpDelete(uri);
            } else {
                throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED,
                      "Given HTTP method has not supported yet");
            }

            populateHeaders(apiCall, request);

            populateExecutionData(apiCall, apiCallExecutionData, uri);

            long executionStartTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(request);
            long executionEndTime = System.currentTimeMillis();

            String result = getResponseBody(response);
            LazyApiCallResponse lazyApiCallResponse = new LazyApiCallResponse(response, (executionEndTime - executionStartTime), result);
            apiCallExecutionData.setResponse(lazyApiCallResponse);
            return lazyApiCallResponse;
        } catch (URISyntaxException e) {
            final String message = "Invalid URI syntax";
            LOGGER.debug(message, e);
            LazyException lazyException = new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.URI_SYNTAX_ERROR, message, e);
            lazyException.setStackTrace(e.getStackTrace());
            apiCallExecutionData.setException(lazyException);
            throw lazyException;
        } catch (IOException e) {
            final String message = "IO exception occurred while connecting to the api. Cannot connect to the API, since double check the API connectivity";
            LOGGER.debug(message, e);
            LazyException lazyException = new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.IO_EXCEPTION, message, e);
            lazyException.setStackTrace(e.getStackTrace());
            apiCallExecutionData.setException(lazyException);
            throw lazyException;
        }


    }

    public void printExecutionDataInError(ApiCallExecutionData apiCallExecutionData, Exception ex) throws LazyException {
        if (apiCallExecutionData == null) {
            final String message = "Provided api call execution data is null";
            LOGGER.error(message);
            throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.IO_EXCEPTION, message);
        }

        String requestData = format("\n\n----------------------------------------------------------------- \nExecuting api call [{0}] - [{1}] \n----------------------------------------------------------------- \n",
              apiCallExecutionData.getApiCallId(), apiCallExecutionData.getApiCallName());
        requestData += format(getPrintKey("Http Method") + ": [{0}]\n", apiCallExecutionData.getHttpMethod());
        requestData += format(getPrintKey("Request URL") + ": [{0}]\n", apiCallExecutionData.getUrl() == null ? null :
              apiCallExecutionData.getUrl().toString());
        if (!"GET".equals(apiCallExecutionData.getHttpMethod())) {
            requestData += format(getPrintKey("Request Body") + ": [{0}]\n", apiCallExecutionData.getRequestBody());
        }
        if (apiCallExecutionData.getHeaderGroup() != null && !CollectionUtils.isEmpty(apiCallExecutionData.getHeaderGroup().getHeaders())) {
            requestData += format(getPrintKey("Header List") + ":\n");
            for (Header header : apiCallExecutionData.getHeaderGroup().getHeaders()) {
                requestData += format(getPrintKey("") + " - [{0}:{1}]\n", header.getKey(), header.getValue());
            }
        }
        requestData += "-----------------------------------------------------------------\n";
        if (ex != null) {
            requestData += format(getPrintKey("Exception") + ": {0}\n", ex.toString());
            requestData += format(getPrintKey("Lazy message") + ": {0}\n", ex.getMessage());
            requestData += format(getPrintKey("Cause") + ": {0}\n", ex.getCause() == null ? null : ex.getCause().toString());
        }
        requestData += "-----------------------------------------------------------------\n\n";
        LOGGER.info(requestData);


    }

    public void printExecutionData(ApiCallExecutionData apiCallExecutionData) throws LazyException {
        if (apiCallExecutionData == null) {
            final String message = "Provided api call execution data is null";
            LOGGER.error(message);
            throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.IO_EXCEPTION, message);
        }

        String requestData = format("\n\n----------------------------------------------------------------- \nExecuting api call [{0}] - [{1}] \n----------------------------------------------------------------- \n",
              apiCallExecutionData.getApiCallId(), apiCallExecutionData.getApiCallName());
        requestData += format(getPrintKey("Http Method") + ": [{0}]\n", apiCallExecutionData.getHttpMethod());
        requestData += format(getPrintKey("Request URL") + ": [{0}]\n", apiCallExecutionData.getUrl().toString());
        if (!apiCallExecutionData.getHttpMethod().equals("GET")) {
            requestData += format(getPrintKey("Request Body") + ": [{0}]\n", apiCallExecutionData.getRequestBody());
        }
        if (apiCallExecutionData.getHeaderGroup() != null && !CollectionUtils.isEmpty(apiCallExecutionData.getHeaderGroup().getHeaders())) {
            requestData += format(getPrintKey("Header List") + ":\n");
            for (Header header : apiCallExecutionData.getHeaderGroup().getHeaders()) {
                requestData += format(getPrintKey("") + " - [{0}:{1}]\n", header.getKey(), header.getValue());
            }
        }
        requestData += "-----------------------------------------------------------------\n";
        requestData += format(getPrintKey("Execution time") + ": [{0}] milli seconds\n", apiCallExecutionData.getResponse().getResponseTime());
        requestData += format(getPrintKey("HTTP status code") + ": [{0}]\n",
              apiCallExecutionData.getResponse().getCloseableHttpResponse().getStatusLine().getStatusCode());
        requestData += format(getPrintKey("Response") + ": [{0}]\n", apiCallExecutionData.getResponse().getResponseBody());
        requestData += "-----------------------------------------------------------------\n\n";
        LOGGER.info(requestData);


    }

    private String getPrintKey(String printableKey) {
        return StringUtils.rightPad(printableKey, 20);
    }

    private void populateExecutionData(ApiCall apiCall, ApiCallExecutionData apiCallExecutionData, URI uri) {
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
    }

    private void populateHeaders(ApiCall apiCall, HttpRequestBase request) {
        if (apiCall.getHeaderGroup() != null) {
            apiCall.getHeaderGroup().getHeaders().forEach(header -> request.addHeader(header.getKey(), header.getValue()));
        }
    }

    private String getResponseBody(CloseableHttpResponse response) throws IOException {
        String result = null;
        try (response) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        }
        return result;
    }

    private URI populateRequestUri(ApiCall apiCall) throws URISyntaxException {
        final String apiPath = StringUtils.isBlank(apiCall.getContextPath()) ? "/".concat(apiCall.getUri()) :
              "/".concat(apiCall.getContextPath().concat("/").concat(apiCall.getUri()));

        String query = "";
        if (!CollectionUtils.isEmpty(apiCall.getQueryParams())) {
            int qParamCount = apiCall.getQueryParams().size();
            int count =1;
            for (QueryParam queryParam: apiCall.getQueryParams()) {
                if (count < qParamCount) {
                    query += VariableManipulationUtil.getVariableValue(queryParam.getKey(), apiCall.getStack()) + "=" + VariableManipulationUtil.getVariableValue(queryParam.getValue(), apiCall.getStack())+"&";
                } else {
                    query += VariableManipulationUtil.getVariableValue(queryParam.getKey(), apiCall.getStack()) + "=" + VariableManipulationUtil.getVariableValue(queryParam.getValue(), apiCall.getStack());
                }
                count++;
            }
        }
        return new URI(apiCall.getProtocol(), null, apiCall.getHostName(), apiCall.getPort(), apiPath,
              StringUtils.isBlank(query)?null:query, null);
    }

}
