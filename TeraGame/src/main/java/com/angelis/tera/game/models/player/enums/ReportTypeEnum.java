package com.angelis.tera.game.models.player.enums;

import org.apache.log4j.Logger;

public enum ReportTypeEnum {
    INSULT(0),
    INVALID_NAME(1),
    REAL_MONEY_EXCHANGE(2),
    FRAUD(3),
    BOTS_HACCKING(4);
    
    public final int value;
    
    ReportTypeEnum(int value) {
        this.value = value;
    }
    
    public static ReportTypeEnum fromValue(int value) {
        for (ReportTypeEnum resportType : ReportTypeEnum.values()) {
            if (resportType.value == value) {
                return resportType;
            }
        }
        
        Logger.getLogger(ReportTypeEnum.class.getName()).error("Can't find ReportTypeEnum with value "+value);
        return null;
    }

}
