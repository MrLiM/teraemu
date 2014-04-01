package com.angelis.tera.common.database.dao;

import org.hibernate.Session;

import com.angelis.tera.common.database.entity.AbstractDatabaseEntity;

public abstract class AbstractDAO<E extends AbstractDatabaseEntity> {
    
    protected final Session session = this.getSession();
    
    public abstract void create(E entity);
    public abstract E read(Integer id);
    public abstract void update(E entity);
    public abstract void delete(E entity);
    
    protected abstract Session getSession();
}
