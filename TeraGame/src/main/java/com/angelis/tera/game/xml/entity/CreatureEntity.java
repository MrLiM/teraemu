package com.angelis.tera.game.xml.entity;

import java.util.Set;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javolution.util.FastSet;

import com.angelis.tera.common.xml.entity.AbstractXMLEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "creature", namespace = "http://angelis.com/creatures")
public class CreatureEntity extends AbstractXMLEntity {

    private static final long serialVersionUID = -5082744641054235756L;

    @XmlAttribute(name = "id")
    private Integer id;

    @XmlAttribute(name = "creatureType")
    private short creatureType;

    @XmlAttribute(name = "modelId")
    private int modelId;

    @XmlAttribute(name = "inoffensive")
    private int inoffensive;

    @XmlAttribute(name = "speed")
    private short speed;

    @XmlElement(name = "spawn", namespace = "http://angelis.com/creatures")
    private Set<CreatureSpawnEntity> spawns;

    public Integer getId() {
        return this.id;
    }

    public short getCreatureType() {
        return creatureType;
    }

    public int getModelId() {
        return modelId;
    }

    public int isInoffensive() {
        return inoffensive;
    }

    public short getSpeed() {
        return speed;
    }

    public Set<CreatureSpawnEntity> getSpawns() {
        if (this.spawns == null) {
            this.spawns = new FastSet<>();
        }
        return this.spawns;
    }

    @Override
    public void afterUnmarshalling(Unmarshaller unmarshaller) {
    }

    @Override
    public void onLoad() {
    }
}
