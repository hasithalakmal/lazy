package com.smile24es.lazy.suite.sample0.dto;

import java.util.List;

public class AccountTo {

    List<AccountSetting> accountSettings;
    private String accountName;
    private String accountStatus;
    private String enterpriseId;
    private String versionId;

    public AccountTo(String accountName, String accountStatus, String enterpriseId, String versionId, List<AccountSetting> accountSettings) {
        this.accountName = accountName;
        this.accountStatus = accountStatus;
        this.enterpriseId = enterpriseId;
        this.versionId = versionId;
        this.accountSettings = accountSettings;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public List<AccountSetting> getAccountSettings() {
        return accountSettings;
    }

    public void setAccountSettings(List<AccountSetting> accountSettings) {
        this.accountSettings = accountSettings;
    }

    @Override
    public String toString() {
        return "AccountTo{" +
              "accountName='" + accountName + '\'' +
              ", accountStatus='" + accountStatus + '\'' +
              ", enterpriseId='" + enterpriseId + '\'' +
              ", versionId='" + versionId + '\'' +
              ", accountSettings=" + accountSettings +
              '}';
    }
}
