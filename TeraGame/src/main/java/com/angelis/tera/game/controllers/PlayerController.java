package com.angelis.tera.game.controllers;

import java.util.EnumSet;

import com.angelis.tera.game.controllers.enums.Right;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.player.request.Request;

public class PlayerController extends Controller<Player> {

    private final EnumSet<Right> rights = EnumSet.allOf(Right.class);
    private Request request;
    
    public boolean can(Right right) {
        return this.rights.contains(right);
    }

    public final void removeRight(Right right) {
        this.rights.remove(right);
    }
    
    public final void addRight(Right right) {
        this.rights.add(right);
    }

    public Request getRequest() {
        return this.request;
    }
    
    public void setRequest(Request request) {
        this.request = request;
    }
}
