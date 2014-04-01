package com.angelis.tera.game.models;

import com.angelis.tera.common.lang.Eventable;

public enum CreatureEventTypeEnum implements Eventable {
    APPEAR,
    DISAPPEAR,
    MOVE,
    TALK,
    EMOTE,
    DIE,
    SET_TITLE
    ;
}
