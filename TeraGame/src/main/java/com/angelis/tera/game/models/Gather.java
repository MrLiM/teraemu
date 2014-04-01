package com.angelis.tera.game.models;

import com.angelis.tera.common.lang.IsObservable;
import com.angelis.tera.game.models.enums.ObjectFamilyEnum;
import com.angelis.tera.game.services.ObjectIDService;

public class Gather extends Creature {

    private int currentGatherCount = 1;
    
    public Gather(int id) {
        super(id, ObjectIDService.getInstance().generateId(ObjectFamilyEnum.fromClass(Gather.class)));
    }

    public int getCurrentGatherCount() {
        return currentGatherCount;
    }

    public void setCurrentGatherCount(int currentGatherCount) {
        this.currentGatherCount = currentGatherCount;
    }

    @Override
    public void onObservableUpdate(CreatureEventTypeEnum event, IsObservable<CreatureEventTypeEnum> observable, Object... data) {
    }

}
