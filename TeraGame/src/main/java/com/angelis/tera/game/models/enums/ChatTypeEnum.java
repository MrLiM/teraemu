package com.angelis.tera.game.models.enums;

import org.apache.log4j.Logger;

public enum ChatTypeEnum {

    SAY(0), // OK
    PARTY(1), // OK
    GUILD(2), // OK
    AREA(3), // OK
    TRADE(4), // OK
    TEAM(5), // OK
    CLUB(6),
    PRIVATE_HEARD(7),
    PRIVATE_WHISPERED(8),

    BARGAIN(9), // TODO
    CHANNEL1(11), // OK
    CHANNEL2(12), // OK
    CHANNEL3(13), // OK
    CHANNEL4(14), // OK
    CHANNEL5(15), // OK
    CHANNEL6(16), // OK
    CHANNEL7(17), // OK
    CHANNEL8(18), // OK
    MERCHANT(19), // OK
    LOOKING_FOR_GROUP(20), // OK
    NOTICE(21), // OK
    VARNARCH_ANNOUNCEMENT(22), // OK
    SYSTEM(23),
    SOCIAL(26), // OK
    GENERAL(27); // OK

    private final int value;
    
    ChatTypeEnum(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }

    public static ChatTypeEnum fromValue(int value) {
        for (ChatTypeEnum chatType : ChatTypeEnum.values()) {
            if (chatType.value == value) {
                return chatType;
            }
        }
        
        Logger.getLogger(ChatTypeEnum.class.getName()).error("Can't find ChatTypeEnum with value "+value);
        return null;
    }
}
