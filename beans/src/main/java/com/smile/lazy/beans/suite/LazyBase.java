package com.smile.lazy.beans.suite;

import com.smile.lazy.beans.DefaultValues;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class LazyBase implements Serializable {

    private DefaultValues defaultValues;

    public DefaultValues getDefaultValues() {
        return defaultValues;
    }

    public void setDefaultValues(DefaultValues defaultValues) {
        this.defaultValues = defaultValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LazyBase lazyBase = (LazyBase) o;

        return new EqualsBuilder()
              .append(defaultValues, lazyBase.defaultValues)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(defaultValues)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "LazyBase{" +
              "defaultValues=" + defaultValues +
              '}';
    }
}
