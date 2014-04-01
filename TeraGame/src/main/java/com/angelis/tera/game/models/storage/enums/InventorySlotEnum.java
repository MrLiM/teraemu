package com.angelis.tera.game.models.storage.enums;

public enum InventorySlotEnum {
    WEAPON(1),
    ARMOR(3),
    GLOVES(4),
    FOOT(5),
    HAIR(0), // TODO
    FACE(0), // TODO
    EARING_LEFT(0), // TODO
    EARING_RIGHT(0); // TODO

    private final int value;
    
    InventorySlotEnum(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
