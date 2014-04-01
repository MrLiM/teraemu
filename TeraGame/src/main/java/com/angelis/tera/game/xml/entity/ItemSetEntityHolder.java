package com.angelis.tera.game.xml.entity;

import java.util.Set;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javolution.util.FastSet;

import org.apache.log4j.Logger;

import com.angelis.tera.common.xml.entity.AbstractXMLEntity;
import com.angelis.tera.game.models.player.enums.PlayerClassEnum;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="itemsets", namespace="http://angelis.com/itemsets")
public class ItemSetEntityHolder extends AbstractXMLEntity {

    private static final long serialVersionUID = 348233423217946855L;

    /** LOGGER */
    private static Logger log = Logger.getLogger(ItemSetEntityHolder.class.getName());

    @XmlElement(name="itemset", namespace="http://angelis.com/itemsets")
    private Set<ItemSetEntity> itemsets;
    
    public Set<ItemSetEntity> getItemsets() {
        if (itemsets == null) {
            itemsets = new FastSet<>(0);
        }
        return itemsets;
    }
    
    public ItemSetEntity getItemsetsByTargetClass(PlayerClassEnum classEnum) {
        for (ItemSetEntity itemSet : this.getItemsets()) {
            if (itemSet.getTargetClass() == classEnum) {
                return itemSet;
            }
        }
        return null;
    }

    @Override
    public void afterUnmarshalling(Unmarshaller unmarshaller) {
    }

    @Override
    public void onLoad() {
        log.info("Loaded "+getItemsets().size()+" item sets !");
    }
}
