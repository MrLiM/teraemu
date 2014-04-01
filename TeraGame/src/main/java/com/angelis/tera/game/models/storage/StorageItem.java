package com.angelis.tera.game.models.storage;

import com.angelis.tera.common.model.AbstractModel;

public class StorageItem extends AbstractModel {

    private int itemId;
    private int slot;
    private int count;
    private int color;
    
    private Storage storage;

    public StorageItem(Integer id) {
        super(id);
    }
    
    public StorageItem() {
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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
