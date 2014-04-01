package com.angelis.tera.game.models.storage;

import java.util.List;

import javolution.util.FastList;

import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.models.enums.StorageTypeEnum;
import com.angelis.tera.game.models.storage.enums.InventorySlotEnum;

public class Storage extends AbstractModel {

    private StorageTypeEnum storageType;
    List<StorageItem> storageItems;
    
    public Storage(Integer abstractId) {
        super(abstractId);
    }

    public Storage(StorageTypeEnum storageType) {
        this.storageType = storageType;
    }

    public StorageTypeEnum getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageTypeEnum storageType) {
        this.storageType = storageType;
    }

    public List<StorageItem> getStorageItems() {
        if (storageItems == null) {
            storageItems = new FastList<>();
        }
        return storageItems;
    }

    public void setStorageItems(List<StorageItem> storageItems) {
        this.storageItems = storageItems;
    }

    public void addStorageItem(StorageItem storageItem) {
        this.getStorageItems().add(storageItem);
        storageItem.setStorage(this);
    }

    public StorageItem getStorageItem(InventorySlotEnum storageSlot) {
        return this.getStorageItem(storageSlot.getValue());
    }
    
    public StorageItem getStorageItem(int slot) {
        for (StorageItem storageItem : this.getStorageItems()) {
            if (storageItem.getSlot() == slot) {
                return storageItem;
            }
        }
        
        // TODO handle null for caller
        return new StorageItem();
    }
}
