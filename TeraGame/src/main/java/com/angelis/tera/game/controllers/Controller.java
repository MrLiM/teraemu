package com.angelis.tera.game.controllers;

import com.angelis.tera.common.model.AbstractModel;

public abstract class Controller<O extends AbstractModel> {
    private O owner;

    public Controller() {}

    public O getOwner() {
        return owner;
    }

    public void setOwner(O owner) {
        this.owner = owner;
    }
}
