package com.angelis.tera.game.models.player.enums;

import org.apache.log4j.Logger;

public enum GenderEnum {
    MALE(0),
    FEMALE(1);

    private final int value;
    
    GenderEnum(final int value) {
        this.value = value;
    }
    
    public final int getValue() {
        return this.value;
    }
    
    public static GenderEnum fromValue(int value) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.getValue() == value) {
                return gender;
            }
        }
        
        Logger.getLogger(GenderEnum.class.getName()).error("Can't find GenderEnum with value "+value);
        return null;
    }
}
