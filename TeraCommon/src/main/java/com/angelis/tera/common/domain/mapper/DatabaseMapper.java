package com.angelis.tera.common.domain.mapper;

import com.angelis.tera.common.database.entity.AbstractDatabaseEntity;
import com.angelis.tera.common.model.AbstractModel;

public abstract class DatabaseMapper<E extends AbstractDatabaseEntity, M extends AbstractModel> extends AbstractMapper<E, M> {
    
}
