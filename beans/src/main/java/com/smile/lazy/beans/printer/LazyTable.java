package com.smile.lazy.beans.printer;

import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyCoreException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.text.MessageFormat.format;

public class LazyTable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyTable.class);

    private List<String> headers;
    private List<List<String>> rows = new ArrayList<>();
    private int padding;
    private int numberOfColumns;
    private Map<Integer, Integer> columnMaxCharacters = new HashMap<>();//Column number, number of characters

    public LazyTable(List<String> headers) throws LazyCoreException {
        validateTableHeaders(headers);
        this.headers = headers;
        this.numberOfColumns = headers.size();
        this.padding = 0;
        calculateMaxLengths(headers);
    }

    public LazyTable(List<String> headers, int padding) throws LazyCoreException {
        validateTableHeaders(headers);
        this.headers = headers;
        this.numberOfColumns = headers.size();
        this.padding = padding;
        calculateMaxLengths(headers);
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows) throws LazyCoreException {
        if (CollectionUtils.isEmpty(rows)) {
            String error = "Table rows should not be empty";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_TABLE, error);
        }
        for (List<String> row : rows) {
            validateRow(row);
        }
        this.rows = rows;
    }

    private void validateRow(List<String> row) throws LazyCoreException {
        if (CollectionUtils.isEmpty(row)) {
            String error = "Table row should not be empty";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_TABLE, error);
        }

        if (row.size() != numberOfColumns) {
            String error = "Data row size should not be equal to number of headers";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_TABLE, error);
        }
        calculateMaxLengths(row);
    }

    public int getPadding() {
        return padding;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void addRow(List<String> row) throws LazyCoreException {
        validateRow(row);
        this.rows.add(row);
    }

    public String getFormatterString() {
        String leftAlignFormat = "";
        for (Integer key : columnMaxCharacters.keySet()) {
            int columnLength = columnMaxCharacters.get(key) + padding;
            leftAlignFormat += format("| %-{0}s ", columnLength);
        }
        leftAlignFormat += "|\n";
        return leftAlignFormat;
    }

    public String getBreakerString() {
        String rowBreaker = "";
        for (Integer key : columnMaxCharacters.keySet()) {
            int columnLength = columnMaxCharacters.get(key) + padding;
            String columnDash = "+" + "-".repeat(columnLength + 2);
            rowBreaker += columnDash;
        }
        rowBreaker += "+\n";
        return rowBreaker;
    }

    private void calculateMaxLengths(List<String> columnValues) {
        int count = 1;
        for (String header : columnValues) {
            int length = StringUtils.isBlank(header) ? 0 : header.length();
            if (columnMaxCharacters.containsKey(count)) {
                Integer existingValue = columnMaxCharacters.get(count);
                if (existingValue < length) {
                    columnMaxCharacters.put(count, length);
                }
            } else {
                columnMaxCharacters.put(count, length);
            }
            count++;
        }
    }

    private void validateTableHeaders(List<String> headers) throws LazyCoreException {
        if (CollectionUtils.isEmpty(headers)) {
            String error = "Table headers should not be empty";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_TABLE, error);
        }
    }
}
