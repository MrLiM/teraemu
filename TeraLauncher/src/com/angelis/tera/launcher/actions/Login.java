package com.angelis.tera.launcher.actions;

import java.util.HashMap;
import java.util.Map;

import com.angelis.tera.launcher.model.Account;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Login extends Action<Account> {

    private final String login;
    private final String password;
    
    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public Account doAction() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("login", this.login);
        parameters.put("password", this.password);
        
        String result = this.doPost("http://127.0.0.1/tera/login.php", parameters);
        JsonObject root = new JsonParser().parse(result).getAsJsonObject();
        System.out.println(root.toString());
        boolean success = root.get("success").getAsBoolean();
        if (!success) {
            return null;
        }
        
        return new Account(root.get("login").getAsString(), root.get("password").getAsString(), root.get("lang").getAsString());
    }
    
}
