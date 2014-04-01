package com.angelis.tera.game.models.dialog.enums;

public enum DialogQuestEnum {
    ACCEPT(1),
    DECLINE(2),
    NONE(3),
    SOLO(4),
    PARTY(5),
    GUESTFAILDEDRESTARTFEWMINUTES(6);
    
    public final int value;
    
    DialogQuestEnum(int value) {
        this.value = value;
    }
}
