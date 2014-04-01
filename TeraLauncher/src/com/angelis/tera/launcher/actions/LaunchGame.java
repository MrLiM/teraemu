package com.angelis.tera.launcher.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.angelis.tera.launcher.model.Account;

public class LaunchGame extends Action<Void> {

    private final Account account;
    
    public LaunchGame(Account account) {
        this.account = account;
    }
    
    @Override
    public Void doAction() {
        List<String> commands = new ArrayList<String>();
        commands.add("E:\\Program Files (x86)\\TERA\\Game.exe");
        commands.add("1");
        commands.add(account.getPassword());
        commands.add("1");
        commands.add("1");
        commands.add(account.getLogin());
        commands.add(account.getLang());
        
        try {
            Process p = new ProcessBuilder(commands).directory(new File("E:\\Program Files (x86)\\TERA")).start();
            p.waitFor();
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
