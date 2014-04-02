package com.angelis.tera.common.model;

import com.angelis.tera.common.entity.HasId;

public abstract class AbstractModel implements HasId, HasUid {
    
    private Integer id;
    private final Integer uid;
    
    public AbstractModel(Integer id, Integer uid) {
        this.id = id;
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUid() {
        return this.uid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AbstractModel)) {
            return false;
        }
        AbstractModel other = (AbstractModel) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        }
        else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
