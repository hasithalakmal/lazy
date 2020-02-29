package com.smile.lazy.beans.suite.assertions;

import com.smile.lazy.beans.enums.DataTypeEnum;
import com.smile.lazy.beans.enums.UnitEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class AssertionValue implements Serializable {

    private String expectedValue1;
    private String expectedValue2;
    private DataTypeEnum dataType;
    private UnitEnum unit;

    public AssertionValue() {
    }

    public AssertionValue(String expectedValue1) {
        this.expectedValue1 = expectedValue1;
    }

    public AssertionValue(String expectedValue1, DataTypeEnum dataType) {
        this.expectedValue1 = expectedValue1;
        this.dataType = dataType;
    }

    public AssertionValue(String expectedValue1, DataTypeEnum dataType, UnitEnum unit) {
        this.expectedValue1 = expectedValue1;
        this.dataType = dataType;
        this.unit = unit;
    }

    public AssertionValue(String expectedValue1, String expectedValue2) {
        this.expectedValue1 = expectedValue1;
        this.expectedValue2 = expectedValue2;
    }

    public String getExpectedValue1() {
        return expectedValue1;
    }

    public void setExpectedValue1(String expectedValue1) {
        this.expectedValue1 = expectedValue1;
    }

    public String getExpectedValue2() {
        return expectedValue2;
    }

    public void setExpectedValue2(String expectedValue2) {
        this.expectedValue2 = expectedValue2;
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
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssertionValue that = (AssertionValue) o;

        return new EqualsBuilder()
              .append(expectedValue1, that.expectedValue1)
              .append(expectedValue2, that.expectedValue2)
              .append(dataType, that.dataType)
              .append(unit, that.unit)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(expectedValue1)
              .append(expectedValue2)
              .append(dataType)
              .append(unit)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "AssertionValue{" +
              "expectedValue1='" + expectedValue1 + '\'' +
              "expectedValue2='" + expectedValue2 + '\'' +
              ", dataType=" + dataType +
              ", unit=" + unit +
              '}';
    }
}
