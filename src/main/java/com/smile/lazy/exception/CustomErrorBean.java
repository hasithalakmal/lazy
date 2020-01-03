package com.smile.lazy.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The custom error bean
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomErrorBean implements Serializable {

  private static final long serialVersionUID = 2329892369893234295L;

  private String code;
  private String description;
  private String additionalInfo;

  public CustomErrorBean() {
  }

  public CustomErrorBean(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public CustomErrorBean(String code, String description, String additionalInfo) {
    this.code = code;
    this.description = description;
    this.additionalInfo = additionalInfo;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String value) {
    this.code = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String value) {
    this.description = value;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String value) {
    this.additionalInfo = value;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }

      if (o == null || getClass() != o.getClass()) {
          return false;
      }

    CustomErrorBean that = (CustomErrorBean) o;

    return new EqualsBuilder()
        .append(code, that.code)
        .append(description, that.description)
        .append(additionalInfo, that.additionalInfo)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(code)
        .append(description)
        .append(additionalInfo)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "CustomErrorBean{" +
        "code='" + code + '\'' +
        ", description='" + description + '\'' +
        ", additionalInfo='" + additionalInfo + '\'' +
        '}';
  }
}
