package com.angelis.tera.game.models.account.enums;

public enum AccountTypeEnum {
    NORMAL(2),
    VETERAN(8),
    PREMIUM(8);
    
    public final int maxPlayerCount;
    
    AccountTypeEnum(int maxPlayerCount) {
        this.maxPlayerCount = maxPlayerCount;
    }
}
