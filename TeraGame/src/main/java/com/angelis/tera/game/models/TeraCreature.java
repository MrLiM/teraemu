package com.angelis.tera.game.models;

import com.angelis.tera.common.lang.IsObservable;
import com.angelis.tera.game.models.enums.ObjectFamilyEnum;
import com.angelis.tera.game.services.ObjectIDService;

public class TeraCreature extends Creature {
    
    private Short creatureType;
    private Integer modelId;
    private boolean aggresive;

    public TeraCreature(Integer id) {
        super(id, ObjectIDService.getInstance().generateId(ObjectFamilyEnum.CREATURE));
    }

    public Short getCreatureType() {
        return creatureType;
    }

    public void setCreatureType(Short creatureType) {
        this.creatureType = creatureType;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public boolean isAggresive() {
        return aggresive;
    }

    public void setAggresive(boolean aggresive) {
        this.aggresive = aggresive;
    }

    @Override
    public void onObservableUpdate(CreatureEventTypeEnum event, IsObservable<CreatureEventTypeEnum> observable, Object... data) {
        // Nothing to do
    }
}
