package com.angelis.tera.game.models.enums;

import org.apache.log4j.Logger;

import com.angelis.tera.common.utils.PrintUtils;
import com.angelis.tera.game.models.Gather;
import com.angelis.tera.game.models.TeraCreature;
import com.angelis.tera.game.models.dialog.Dialog;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.player.request.PartyInviteRequest;
import com.angelis.tera.game.models.player.request.Request;

public enum ObjectFamilyEnum {
    SYSTEM("0", Void.class), // TODO

    //Abstract

    ATTACK("0", Void.class), // TODO
    REQUEST("0", Request.class, PartyInviteRequest.class),

    //Visible

    PLAYER("00800001", Player.class),
    CREATURE("00800C00", TeraCreature.class),
    GATHER("00800400", Gather.class),
    DIALOG("00800C00", Dialog.class),
    ITEM("0", Void.class), // TODO
    INVENTORYITEM("0", Void.class), // TODO
    PROJECTILE("0", Void.class), // TODO
    CAMPFIRE("0", Void.class), // TODO
    GUILD("0", Void.class); // TODO
    
    public final Class<?>[] associatedClass;
    public final byte[] value;
    
    ObjectFamilyEnum(String value, Class<?>... associatedClass) {
        this.value = PrintUtils.hex2bytes(value);
        this.associatedClass = associatedClass;
    }
    
    public static final ObjectFamilyEnum fromClass(Class<?> clazz) {
        for (ObjectFamilyEnum objectFamily : ObjectFamilyEnum.values()) {
            for (Class<?> associatedClass : objectFamily.associatedClass) {
                if (clazz == associatedClass) {
                    return objectFamily;
                }
            }
        }
        
        Logger.getLogger(ObjectFamilyEnum.class.getName()).error("Can't find ObjectFamilyEnum with class "+clazz.getName());
        return null;
    }

    public static ObjectFamilyEnum fromValue(byte[] value) {
        for (ObjectFamilyEnum objectFamily : ObjectFamilyEnum.values()) {
            if (value.equals(objectFamily.value)) {
                return objectFamily;
            }
        }
        
        Logger.getLogger(ObjectFamilyEnum.class.getName()).error("Can't find ObjectFamilyEnum with value "+value);
        return null;
    }
}
