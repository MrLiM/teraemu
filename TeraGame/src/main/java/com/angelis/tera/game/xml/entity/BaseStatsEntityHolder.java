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
@XmlRootElement(name = "basestats", namespace = "http://angelis.com/basestats")
public class BaseStatsEntityHolder extends AbstractXMLEntity {

    private static final long serialVersionUID = 2941004152168232255L;

    /** LOGGER */
    private static Logger log = Logger.getLogger(BaseStatsEntityHolder.class.getName());

    @XmlElement(name = "basestat", namespace = "http://angelis.com/basestats")
    private Set<BaseStatsEntity> baseStats;

    public Set<BaseStatsEntity> getBaseStats() {
        if (baseStats == null) {
            baseStats = new FastSet<>(0);
        }
        return baseStats;
    }

    public BaseStatsEntity getBaseStatsByTargetClass(PlayerClassEnum classEnum) {
        for (BaseStatsEntity baseStats : this.getBaseStats()) {
            if (baseStats.getTargetClass() == classEnum) {
                return baseStats;
            }
        }
        return null;
    }

    @Override
    public void afterUnmarshalling(Unmarshaller unmarshaller) {
    }

    @Override
    public void onLoad() {
        log.info("Loaded " + getBaseStats().size() + " base stats !");
    }

}
