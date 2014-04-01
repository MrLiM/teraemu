package com.angelis.tera.game.models.enums;

import org.apache.log4j.Logger;

public enum CheckNameTypeEnum {
    CHARACTER(1, 2, 16, false),
    TARGET(9, 1, 1, false),
    STATUS(11, 2, 40, true);
    
    private final int code;
    private final int minLength;
    private final int maxLength;
    private final boolean spaceAllowed;
    
    CheckNameTypeEnum(int code, int minLength, int maxLength, final boolean spaceAllowed) {
        this.code = code;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.spaceAllowed = spaceAllowed;
    }
    
    public int getCode() {
        return code;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
    
    public boolean isSpaceAllowed() {
        return spaceAllowed;
    }
    
    public static CheckNameTypeEnum fromCode(int code) {
        for (CheckNameTypeEnum checkNameType : CheckNameTypeEnum.values()) {
            if (checkNameType.code == code) {
                return checkNameType;
            }
        }
        
        Logger.getLogger(CheckNameTypeEnum.class.getName()).error("Can't find CheckNameTypeEnum with code "+code);
        return null;
    }
}
