package com.angelis.tera.game.xml.entity;

import java.util.Set;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import javolution.util.FastSet;

import com.angelis.tera.common.xml.entity.AbstractXMLEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="gathers", namespace="http://angelis.com/gathers")
public class GatherEntityHolder extends AbstractXMLEntity {

    private static final long serialVersionUID = 449824353877386690L;

    /** LOGGER */
    private static Logger log = Logger.getLogger(GatherEntityHolder.class.getName());
    
    @XmlElement(name="gather", namespace="http://angelis.com/gathers")
    private Set<GatherEntity> gathers;
    
    public Set<GatherEntity> getGathers() {
        if (gathers == null) {
            gathers = new FastSet<>(0);
        }
        return gathers;
    }
    
    @Override
    public void afterUnmarshalling(Unmarshaller unmarshaller) {
    }

    @Override
    public void onLoad() {
        log.info("Loaded "+getGathers().size()+" gathers !");
    }
}
