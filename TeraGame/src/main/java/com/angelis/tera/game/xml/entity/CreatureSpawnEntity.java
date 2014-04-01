package com.angelis.tera.game.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="spawn", namespace = "http://angelis.com/creatures")
public class CreatureSpawnEntity extends SpawnEntity {

    @XmlAttribute(name = "heading")
    private short heading;

    public short getHeading() {
        return heading;
    }
}
