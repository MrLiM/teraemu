package com.angelis.tera.game.xml.entity;

import javax.xml.bind.annotation.XmlAttribute;

import com.angelis.tera.game.models.enums.CreatureTitleEnum;

public class CreatureTemplateEntity {
    @XmlAttribute(name = "title")
    private CreatureTitleEnum creatureTitle;

    @XmlAttribute(name = "huntingZoneId")
    private int huntingZoneId;

    public CreatureTitleEnum getCreatureTitle() {
        return creatureTitle;
    }

    public int getHuntingZoneId() {
        return huntingZoneId;
    }
}
