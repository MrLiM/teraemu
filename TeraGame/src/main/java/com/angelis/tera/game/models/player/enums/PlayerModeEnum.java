package com.angelis.tera.game.models.player.enums;

public enum PlayerModeEnum {
    NORMAL(0x00),
    ARMORED(0x01),
    RELAX(0x02),
    FLYING(0x03);
    
    private final int value;
    
    PlayerModeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
