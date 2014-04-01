package com.angelis.tera.game.xml.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "quest", namespace="http://angelis.com/quests")
public class QuestEntity {
    
    @XmlAttribute()
    private List<Integer> counters;

    public List<Integer> getCounters() {
        return counters;
    }
}
