package com.angelis.tera.game.domain.mapper.database;

import com.angelis.tera.common.domain.mapper.DatabaseMapper;
import com.angelis.tera.common.domain.mapper.MapperManager;
import com.angelis.tera.common.entity.AbstractEntity;
import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.database.entity.StorageEntity;
import com.angelis.tera.game.database.entity.StorageItemEntity;
import com.angelis.tera.game.models.storage.Storage;
import com.angelis.tera.game.models.storage.StorageItem;

public class StorageItemMapper extends DatabaseMapper<StorageItemEntity, StorageItem> {

    @Override
    public StorageItemEntity map(final StorageItem model, AbstractEntity linkedEntity) {
        StorageItemEntity storageItemEntity = new StorageItemEntity(model.getId());
        
        // DIRECT
        storageItemEntity.setItemId(model.getItemId());
        storageItemEntity.setSlot(model.getSlot());
        storageItemEntity.setCount(model.getCount());
        storageItemEntity.setColor(model.getColor());
        
        // STORAGE
        if (linkedEntity instanceof StorageEntity) {
            storageItemEntity.setStorage((StorageEntity) linkedEntity);
        } else {
            storageItemEntity.setStorage(MapperManager.getMapper(StorageMapper.class).map(model.getStorage(), storageItemEntity));
        }
        
        return storageItemEntity;
    }
    
    @Override
    public StorageItem map(final StorageItemEntity entity, AbstractModel linkedModel) {
        StorageItem storageItem = new StorageItem(entity.getId());
        
        // DIRECT
        storageItem.setItemId(entity.getItemId());
        storageItem.setSlot(entity.getSlot());
        storageItem.setCount(entity.getCount());
        storageItem.setColor(entity.getColor());
        
        // STORAGE
        if (linkedModel instanceof Storage) {
            storageItem.setStorage((Storage) linkedModel);
        } else {
            storageItem.setStorage(MapperManager.getMapper(StorageMapper.class).map(entity.getStorage(), storageItem));
        }
        
        return storageItem;
    }

    @Override
    public boolean equals(AbstractModel model, AbstractEntity entity) {
        return model.getId() == entity.getId();
    }

}
