package com.angelis.tera.game.models.enums;

import org.apache.log4j.Logger;

public enum EmotionEnum {
    TALK1(1),
    TALK2(2);
    
    public final int value;
    
    EmotionEnum(int value) {
        this.value = value;
    }
    
    public static final EmotionEnum fromValue(int value) {
        for (EmotionEnum emotion : EmotionEnum.values()) {
            if (emotion.value == value) {
                return emotion;
            }
        }
        
        Logger.getLogger(EmotionEnum.class.getName()).error("Can't find EmotionEnum with value "+value);
        return null;
    }
}
