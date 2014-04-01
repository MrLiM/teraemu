package com.angelis.tera.game.database.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javolution.util.FastList;

import com.angelis.tera.common.database.entity.AbstractDatabaseEntity;
import com.angelis.tera.game.models.enums.StorageTypeEnum;

@Entity
@Table(name = "storage")
public class StorageEntity extends AbstractDatabaseEntity {

    private static final long serialVersionUID = 5090455704132338034L;

    @Column
    private StorageTypeEnum storageType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storage")
    private List<StorageItemEntity> storageItems;

    public StorageEntity(Integer id, StorageTypeEnum storageType) {
        super(id);
        this.storageType = storageType;
    }

    public StorageEntity(StorageTypeEnum storageType) {
        super();
        this.storageType = storageType;
    }

    public StorageEntity(Integer id) {
        this(id, null);
    }

    public StorageEntity() {
        super();
    }

    public StorageTypeEnum getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageTypeEnum storageType) {
        this.storageType = storageType;
    }

    public List<StorageItemEntity> getStorageItems() {
        if (storageItems == null) {
            storageItems = new FastList<StorageItemEntity>();
        }
        return storageItems;
    }

    public void setStorageItems(List<StorageItemEntity> items) {
        this.storageItems = items;
    }

    public void addStorageItem(StorageItemEntity storageItem) {
        this.getStorageItems().add(storageItem);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((storageItems == null) ? 0 : storageItems.hashCode());
        result = prime * result + ((storageType == null) ? 0 : storageType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        StorageEntity other = (StorageEntity) obj;
        if (storageItems == null) {
            if (other.storageItems != null)
                return false;
        }
        else if (!storageItems.equals(other.storageItems))
            return false;
        if (storageType != other.storageType)
            return false;
        return true;
    }
}
