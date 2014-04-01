package com.angelis.tera.launcher.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public abstract class Action<T> {
    
    enum Method {
        GET,
        POST
    }
    
    private final String doSend(String url, Method method, Map<String, String> parameters) {
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(method.name());
            connection.setAllowUserInteraction(false);
            connection.setUseCaches(false);
            
            connection.setDoOutput(true);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            for (String key : parameters.keySet()) {
                String value = parameters.get(key);
                out.print(key+"="+value+"&");
            }
            
            out.flush();
            out.close();
            
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = in.readLine()) != null) {
                response.append(line);
                response.append(System.getProperty("line.separator"));
            }
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return response.toString();
    }
    
    public final String doGet(String url, Map<String, String> parameters) {
        return this.doSend(url, Method.GET, parameters);
    }
    
    public final String doPost(String url, Map<String, String> parameters) {
        return this.doSend(url, Method.POST, parameters);
    }
    
    public abstract T doAction();
}
