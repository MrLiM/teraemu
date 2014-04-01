package com.angelis.tera.game.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "experience")
public class ExperienceEntity {

    @XmlAttribute
    private int level;

    @XmlAttribute
    private int experience;

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }
}
