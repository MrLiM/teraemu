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
@XmlRootElement(name="experiences", namespace="http://angelis.com/experiences")
public class ExperienceEntityHolder extends AbstractXMLEntity {

    private static final long serialVersionUID = 6037173453270745675L;
    
    /** LOGGER */
    private static Logger log = Logger.getLogger(ExperienceEntityHolder.class.getName());
    
    @XmlElement(name="experience", namespace="http://angelis.com/experiences")
    private Set<ExperienceEntity> experiences;

    public Set<ExperienceEntity> getExperiences() {
        if (this.experiences == null) {
            this.experiences = new FastSet<ExperienceEntity>(0);
        }
        return this.experiences;
    }

    @Override
    public void afterUnmarshalling(Unmarshaller unmarshaller) {
    }
    
    @Override
    public void onLoad() {
        log.info("Loaded "+getExperiences().size()+" experiences !");
    }
}
