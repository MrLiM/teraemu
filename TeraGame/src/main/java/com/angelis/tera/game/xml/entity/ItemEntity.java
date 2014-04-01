package com.angelis.tera.game.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "item", namespace="http://angelis.com/itemsets")
public class ItemEntity {

    @XmlAttribute(name="itemId")
    private int itemId;
    
    @XmlAttribute(name="slot")
    private int slot;

    public int getItemId() {
        return itemId;
    }
    
    public int getSlot() {
        return slot;
    }
}
