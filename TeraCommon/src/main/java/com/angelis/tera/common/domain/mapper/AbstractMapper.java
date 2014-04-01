package com.angelis.tera.common.domain.mapper;

import com.angelis.tera.common.entity.AbstractEntity;
import com.angelis.tera.common.model.AbstractModel;


public abstract class AbstractMapper<E extends AbstractEntity, M extends AbstractModel> {
    
    public final E map(final M model) {
        return this.map(model, null);
    }
    
    public final M map(final E entity) {
        return this.map(entity, null);
    }
    
    public abstract E map(final M model, AbstractEntity linkedEntity);
    public abstract M map(final E entity, AbstractModel linkedModel);
    public abstract boolean equals(AbstractModel model, AbstractEntity entity);
}
