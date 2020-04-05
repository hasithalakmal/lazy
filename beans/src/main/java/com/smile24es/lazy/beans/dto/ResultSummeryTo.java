package com.smile24es.lazy.beans.dto;

import com.smile24es.lazy.beans.printer.LazyTable;
import com.smile24es.lazy.exception.LazyCoreException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultSummeryTo {

    private List<ResultRecodeTo> resultRecodeToList;
    private int totalAssertions;
    private int executedAssertions;
    private int skippedAssertions;
    private int totalSuccessRate;
    private int netSuccessRate;

    public List<ResultRecodeTo> getResultRecodeToList() {
        if (resultRecodeToList == null) {
            resultRecodeToList = new ArrayList<>();
        }
        return resultRecodeToList;
    }

    public void setResultRecodeToList(List<ResultRecodeTo> resultRecodeToList) {
        this.resultRecodeToList = resultRecodeToList;
    }

    public int getTotalAssertions() {
        return totalAssertions;
    }

    public void setTotalAssertions(int totalAssertions) {
        this.totalAssertions = totalAssertions;
    }

    public int getExecutedAssertions() {
        return executedAssertions;
    }

    public void setExecutedAssertions(int executedAssertions) {
        this.executedAssertions = executedAssertions;
    }

    public int getSkippedAssertions() {
        return skippedAssertions;
    }

    public void setSkippedAssertions(int skippedAssertions) {
        this.skippedAssertions = skippedAssertions;
    }

    public int getTotalSuccessRate() {
        return totalSuccessRate;
    }

    public void setTotalSuccessRate(int totalSuccessRate) {
        this.totalSuccessRate = totalSuccessRate;
    }

    public int getNetSuccessRate() {
        return netSuccessRate;
    }

    public void setNetSuccessRate(int netSuccessRate) {
        this.netSuccessRate = netSuccessRate;
    }

    @Override
    public String toString() {
        return "ResultSummeryTo{" +
              "resultRecodeToList=" + resultRecodeToList +
              ", totalAssertions=" + totalAssertions +
              ", executedAssertions=" + executedAssertions +
              ", skippedAssertions=" + skippedAssertions +
              ", totalSuccessRate=" + totalSuccessRate +
              ", netSuccessRate=" + netSuccessRate +
              '}';
    }

    public String prettyPrintString(boolean withActualResults) throws LazyCoreException {
        LazyTable lazyTable = withActualResults ?
              new LazyTable(Arrays.asList("Result Id", "API Call Name", "Assertion Name", "Expected Results", "Actual Result", "Execution Status", "Is Pass")) :
              new LazyTable(Arrays.asList("Result Id", "API Call Name", "Assertion Name", "Execution Status", "Is Pass"));
        if (resultRecodeToList != null) {
            for (ResultRecodeTo result : resultRecodeToList) {
                if (withActualResults) {
                    lazyTable.addRow(Arrays.asList(result.getResultId(),
                          result.getApiCallName(),
                          result.getAssertionName(),
                          result.getExpectedResult(),
                          result.getActualResult(),
                          result.getStatus(),
                          result.getIsPass()));
                } else {
                    lazyTable.addRow(Arrays.asList(result.getResultId(),
                          result.getApiCallName(),
                          result.getAssertionName(),
                          result.getStatus(),
                          result.getIsPass()));
                }
            }
        }
        String formatString = lazyTable.getFormatterString();
        String breakerString = lazyTable.getBreakerString();
        String table = "\n";
        table += breakerString;
        String headersString = withActualResults ?
              String.format(formatString, "Result Id", "API Call Name", "Assertion Name", "Expected Results", "Actual Result", "Execution Status", "Is Pass") :
              String.format(formatString, "Result Id", "API Call Name", "Assertion Name", "Execution Status", "Is Pass");
        table += headersString;
        table += breakerString;
        if (resultRecodeToList != null) {
            for (ResultRecodeTo result : resultRecodeToList) {
                String rowString = "";
                if (withActualResults) {
                    String.format(formatString,
                          result.getResultId(),
                          result.getApiCallName(),
                          result.getAssertionName(),
                          result.getExpectedResult(),
                          result.getActualResult(),
                          result.getStatus(),
                          result.getIsPass());
                } else {
                    rowString = String.format(formatString,
                          result.getResultId(),
                          result.getApiCallName(),
                          result.getAssertionName(),
                          result.getStatus(),
                          result.getIsPass());
                }
                table += rowString;
            }
        }
        table += breakerString;
        return table;
    }

    public String prettyPrintString() throws LazyCoreException {
        return prettyPrintString(false);
    }
}
