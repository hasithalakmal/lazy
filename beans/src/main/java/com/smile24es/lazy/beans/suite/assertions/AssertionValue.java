package com.smile24es.lazy.beans.suite.assertions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile24es.lazy.beans.enums.DataTypeEnum;
import com.smile24es.lazy.beans.enums.UnitEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssertionValue implements Serializable {

    private String expectedStringValue1;
    private String expectedStringValue2;
    private Integer expectedIntegerValue1;
    private Integer expectedIntegerValue2;
    private Double expectedDoubleValue1;
    private Double expectedDoubleValue2;
    private Boolean expectedBooleanValue;
    private List expectedValueList;
    private DataTypeEnum dataType;
    private UnitEnum unit;

    public AssertionValue() {
    }

    public AssertionValue(String expectedStringValue1) {
        this.expectedStringValue1 = expectedStringValue1;
        this.dataType = DataTypeEnum.STRING;
    }

    public AssertionValue(String expectedStringValue1, UnitEnum unit) {
        this.expectedStringValue1 = expectedStringValue1;
        this.unit = unit;
        this.dataType = DataTypeEnum.STRING;
    }

    public AssertionValue(String expectedStringValue1, String expectedStringValue2) {
        this.expectedStringValue1 = expectedStringValue1;
        this.expectedStringValue2 = expectedStringValue2;
        this.dataType = DataTypeEnum.STRING;
    }

    public AssertionValue(Integer expectedIntegerValue1) {
        this.expectedIntegerValue1 = expectedIntegerValue1;
        this.dataType = DataTypeEnum.INTEGER;
    }

    public AssertionValue(Integer expectedIntegerValue1, UnitEnum unit) {
        this.expectedIntegerValue1 = expectedIntegerValue1;
        this.unit = unit;
        this.dataType = DataTypeEnum.INTEGER;
    }

    public AssertionValue(Integer expectedIntegerValue1, Integer expectedIntegerValue2) {
        this.expectedIntegerValue1 = expectedIntegerValue1;
        this.expectedIntegerValue2 = expectedIntegerValue2;
        this.dataType = DataTypeEnum.INTEGER;
    }

    public AssertionValue(Double expectedDoubleValue1) {
        this.expectedDoubleValue1 = expectedDoubleValue1;
        this.dataType = DataTypeEnum.DOUBLE;
    }

    public AssertionValue(Double expectedDoubleValue1, UnitEnum unit) {
        this.expectedDoubleValue1 = expectedDoubleValue1;
        this.unit = unit;
        this.dataType = DataTypeEnum.DOUBLE;
    }

    public AssertionValue(Double expectedDoubleValue1, Double expectedDoubleValue2) {
        this.expectedDoubleValue1 = expectedDoubleValue1;
        this.expectedDoubleValue2 = expectedDoubleValue2;
        this.dataType = DataTypeEnum.DOUBLE;
    }

    public AssertionValue(Boolean expectedBooleanValue) {
        this.expectedBooleanValue = expectedBooleanValue;
        this.dataType = DataTypeEnum.BOOLEAN;
    }

    public AssertionValue(List expectedValueList) {
        this.expectedValueList = expectedValueList;
        this.dataType = DataTypeEnum.ARRAY;
    }

    public String getExpectedStringValue1() {
        return expectedStringValue1;
    }

    public void setExpectedStringValue1(String expectedStringValue1) {
        this.expectedStringValue1 = expectedStringValue1;
    }

    public String getExpectedStringValue2() {
        return expectedStringValue2;
    }

    public void setExpectedStringValue2(String expectedStringValue2) {
        this.expectedStringValue2 = expectedStringValue2;
    }

    public Integer getExpectedIntegerValue1() {
        return expectedIntegerValue1;
    }

    public void setExpectedIntegerValue1(Integer expectedIntegerValue1) {
        this.expectedIntegerValue1 = expectedIntegerValue1;
    }

    public Integer getExpectedIntegerValue2() {
        return expectedIntegerValue2;
    }

    public void setExpectedIntegerValue2(Integer expectedIntegerValue2) {
        this.expectedIntegerValue2 = expectedIntegerValue2;
    }

    public Double getExpectedDoubleValue1() {
        return expectedDoubleValue1;
    }

    public void setExpectedDoubleValue1(Double expectedDoubleValue1) {
        this.expectedDoubleValue1 = expectedDoubleValue1;
    }

    public Double getExpectedDoubleValue2() {
        return expectedDoubleValue2;
    }

    public void setExpectedDoubleValue2(Double expectedDoubleValue2) {
        this.expectedDoubleValue2 = expectedDoubleValue2;
    }

    public Boolean getExpectedBooleanValue() {
        return expectedBooleanValue;
    }

    public void setExpectedBooleanValue(Boolean expectedBooleanValue) {
        this.expectedBooleanValue = expectedBooleanValue;
    }

    public List getExpectedValueList() {
        return expectedValueList;
    }

    public void setExpectedValueList(List expectedValueList) {
        this.expectedValueList = expectedValueList;
        this.dataType = DataTypeEnum.ARRAY;
    }

    public DataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }

    public UnitEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitEnum unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AssertionValue that = (AssertionValue) o;

        return new EqualsBuilder()
              .append(expectedStringValue1, that.expectedStringValue1)
              .append(expectedStringValue2, that.expectedStringValue2)
              .append(expectedIntegerValue1, that.expectedIntegerValue1)
              .append(expectedIntegerValue2, that.expectedIntegerValue2)
              .append(expectedDoubleValue1, that.expectedDoubleValue1)
              .append(expectedDoubleValue2, that.expectedDoubleValue2)
              .append(expectedBooleanValue, that.expectedBooleanValue)
              .append(expectedValueList, that.expectedValueList)
              .append(dataType, that.dataType)
              .append(unit, that.unit)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(expectedStringValue1)
              .append(expectedStringValue2)
              .append(expectedIntegerValue1)
              .append(expectedIntegerValue2)
              .append(expectedDoubleValue1)
              .append(expectedDoubleValue2)
              .append(expectedBooleanValue)
              .append(expectedValueList)
              .append(dataType)
              .append(unit)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "AssertionValue{" +
              "expectedStringValue1='" + expectedStringValue1 + '\'' +
              ", expectedStringValue2='" + expectedStringValue2 + '\'' +
              ", expectedIntegerValue1=" + expectedIntegerValue1 +
              ", expectedIntegerValue2=" + expectedIntegerValue2 +
              ", expectedDoubleValue1=" + expectedDoubleValue1 +
              ", expectedDoubleValue2=" + expectedDoubleValue2 +
              ", expectedBooleanValue=" + expectedBooleanValue +
              ", expectedStringValueList=" + expectedValueList +
              ", dataType=" + dataType +
              ", unit=" + unit +
              '}';
    }
}
