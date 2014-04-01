package com.angelis.tera.game.models.player.request.enums;

import org.apache.log4j.Logger;

public enum RequestTypeEnum {
    PARTY_INVITE(4)
    ;
    
    public final int value;
    
    RequestTypeEnum(int value) {
        this.value = value;
    }

    public static RequestTypeEnum fromValue(short value) {
        for (RequestTypeEnum requestType : RequestTypeEnum.values()) {
            if (requestType.value == value) {
                return requestType;
            }
        }
        
        Logger.getLogger(RequestTypeEnum.class.getName()).error("Can't find RequestTypeEnum with value "+value);
        return null;
    }
}
