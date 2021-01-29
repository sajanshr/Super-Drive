package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {




    private Integer credentialId;
    private String Url;
    private String userName;
    private String Key;
    private String Password;
    private Integer userId;

    public Credential(Integer credentialId, String url, String userName, String key, String Password, Integer userId) {
        this.credentialId = credentialId;
        this.Url = url;
        this.userName = userName;
        this.Key = key;
        this.Password = Password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        this.Key = key;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
