package com.smile.lazy.beans.dto;

import com.smile.lazy.beans.printer.LazyTable;
import com.smile.lazy.exception.LazyCoreException;

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

    public String prettyPrintString() throws LazyCoreException {

        LazyTable lazyTable = new LazyTable(Arrays.asList("Assertion Name", "Actual Result", "Is Pass"));
        for (ResultRecodeTo result :resultRecodeToList){
            lazyTable.addRow(Arrays.asList(result.getAssertionName(), result.getActualResult(), result.getIsPass()));
        }
        String formatString = lazyTable.getFormatterString();
        String breakerString = lazyTable.getBreakerString();
        String table = "\n";
        table += breakerString;
        String headersString = String.format(formatString, "Assertion Name", "Actual Result", "Is Pass");
        table += headersString;
        table += breakerString;
        for (ResultRecodeTo result :resultRecodeToList){
            String rowString = String.format(formatString,result.getAssertionName(), result.getActualResult(), result.getIsPass());
            table += rowString;
        }
        table += breakerString;
        return table;
    }
}
