package com.angelis.tera.game.xml.entity;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javolution.util.FastSet;

import com.angelis.tera.game.models.player.enums.PlayerClassEnum;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "itemset", namespace="http://angelis.com/itemsets")
public class ItemSetEntity {
    
    @XmlAttribute
    private PlayerClassEnum targetClass;
    
    @XmlElement(name="item", namespace="http://angelis.com/itemsets")
    private Set<ItemEntity> items;

    public PlayerClassEnum getTargetClass() {
        return targetClass;
    }

    public Set<ItemEntity> getItems() {
        if (items == null) {
            items = new FastSet<>(0);
        }
        return items;
    }
}
