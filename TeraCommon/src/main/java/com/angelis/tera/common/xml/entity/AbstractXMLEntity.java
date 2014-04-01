package com.angelis.tera.common.xml.entity;

import java.io.Serializable;

import javax.xml.bind.Unmarshaller;

import com.angelis.tera.common.entity.AbstractEntity;

public abstract class AbstractXMLEntity extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -3825172794095209401L;

    public abstract void afterUnmarshalling(Unmarshaller unmarshaller);
    
    public abstract void onLoad();
}
