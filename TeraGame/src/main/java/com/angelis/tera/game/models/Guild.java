package com.angelis.tera.game.models;

import com.angelis.tera.common.model.AbstractModel;

public class Guild extends AbstractModel {

    private String name;
    
    public Guild(Integer id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
