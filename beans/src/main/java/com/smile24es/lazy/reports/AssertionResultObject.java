package com.smile24es.lazy.reports;

public class AssertionResultObject {

    private int id;
    private String name;
    private String actualValue;
    private String expectedValue;
    private String isPass;
    private String status;
    private String notes;

    public AssertionResultObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        if (notes == null) {
            notes = "";
        }
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
