package com.angelis.tera.game.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.angelis.tera.common.database.entity.AbstractDatabaseEntity;

@Entity
@Table(name = "storage_item")
public class StorageItemEntity extends AbstractDatabaseEntity {

    private static final long serialVersionUID = 4583410644473722142L;

    @Column
    private int itemId;

    @Column
    private int slot;

    @Column
    private int count;

    @Column
    private int color;
    
    @ManyToOne()
    private StorageEntity storage;

    public StorageItemEntity(Integer id) {
        super(id);
    }
    
    public StorageItemEntity() {
        super();
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public StorageEntity getStorage() {
        return storage;
    }

    public void setStorage(StorageEntity storage) {
        this.storage = storage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + color;
        result = prime * result + count;
        result = prime * result + itemId;
        result = prime * result + slot;
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
        StorageItemEntity other = (StorageItemEntity) obj;
        if (color != other.color)
            return false;
        if (count != other.count)
            return false;
        if (itemId != other.itemId)
            return false;
        if (slot != other.slot)
            return false;
        if (storage == null) {
            if (other.storage != null)
                return false;
        }
        else if (!storage.equals(other.storage))
            return false;
        return true;
    }
}
