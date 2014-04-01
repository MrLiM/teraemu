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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="creatures", namespace="http://angelis.com/creatures")
public class CreatureEntityHolder extends AbstractXMLEntity {

    private static final long serialVersionUID = -7067997321438234684L;

    /** LOGGER */
    private static Logger log = Logger.getLogger(CreatureEntityHolder.class.getName());
    
    @XmlElement(name="creature", namespace = "http://angelis.com/creatures")
    private Set<CreatureEntity> creatures;

    public Set<CreatureEntity> getCreatures() {
        if (this.creatures == null) {
            this.creatures = new FastSet<CreatureEntity>(0);
        }
        return this.creatures;
    }

    @Override
    public void afterUnmarshalling(Unmarshaller unmarshaller) {
    }
    
    @Override
    public void onLoad() {
        log.info("Loaded "+getCreatures().size()+" creatures !");
    }
}
