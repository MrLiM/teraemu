package com.angelis.tera.game.xml.entity;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javolution.util.FastSet;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "gather", namespace = "http://angelis.com/gathers")
public class GatherEntity {
    
    @XmlAttribute(name="id")
    private int id;
    
    @XmlElement(name="spawn", namespace = "http://angelis.com/gathers")
    private Set<SpawnEntity> spawns;

    public int getId() {
        return id;
    }

    public Set<SpawnEntity> getSpawns() {
        if (spawns == null) {
            spawns = new FastSet<>();
        }
        return spawns;
    }
}
