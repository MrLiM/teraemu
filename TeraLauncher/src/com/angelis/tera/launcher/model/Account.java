package com.angelis.tera.launcher.model;

public class Account {
    private String login = "Angelis";
    private String password = "123456789";
    private String lang = "";
    
    public Account(String login, String password, String lang) {
        this.login = login;
        this.password = password;
        this.lang = lang;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public final String getPassword() {
        return this.password;
    }

    public String getLang() {
        return this.lang;
    }
}
