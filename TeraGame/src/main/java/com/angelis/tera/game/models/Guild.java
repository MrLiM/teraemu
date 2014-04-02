package com.angelis.tera.game.models;

import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.models.enums.ObjectFamilyEnum;
import com.angelis.tera.game.services.ObjectIDService;

public class Guild extends AbstractModel {

    private String name;
    
    public Guild(Integer id) {
        super(id, ObjectIDService.getInstance().generateId(ObjectFamilyEnum.GUILD));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
