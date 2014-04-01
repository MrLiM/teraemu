package com.angelis.tera.game.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.angelis.tera.game.models.player.enums.PlayerClassEnum;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "basestat", namespace = "http://angelis.com/basestats")
public class BaseStatsEntity {
    
    @XmlAttribute(name="targetClass")
    private PlayerClassEnum targetClass;

    @XmlAttribute(name="hp")
    private int hp;

    @XmlAttribute(name="mp")
    private int mp;

    @XmlAttribute(name="attack")
    private int attack;

    @XmlAttribute(name="defense")
    private int defense;

    @XmlAttribute(name="speed")
    private int speed;

    @XmlAttribute(name="critChance")
    private int critChance;

    @XmlAttribute(name="critResist")
    private int critResist;

    public PlayerClassEnum getTargetClass() {
        return targetClass;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getCritResist() {
        return critResist;
    }
}
