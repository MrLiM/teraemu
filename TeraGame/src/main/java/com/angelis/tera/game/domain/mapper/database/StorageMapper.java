package com.angelis.tera.game.domain.mapper.database;

import java.util.List;

import javolution.util.FastList;

import com.angelis.tera.common.domain.mapper.DatabaseMapper;
import com.angelis.tera.common.domain.mapper.MapperManager;
import com.angelis.tera.common.entity.AbstractEntity;
import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.database.entity.StorageEntity;
import com.angelis.tera.game.database.entity.StorageItemEntity;
import com.angelis.tera.game.models.storage.Storage;
import com.angelis.tera.game.models.storage.StorageItem;

public class StorageMapper extends DatabaseMapper<StorageEntity, Storage> {
    
    @Override
    public StorageEntity map(final Storage model, AbstractEntity linkedEntity) {
        StorageEntity storageEntity = new StorageEntity(model.getId());
        
        // DIRECT
        storageEntity.setStorageType(model.getStorageType());
        
        // STORAGE ITEM
        List<StorageItemEntity> storageItems = new FastList<>();
        for (StorageItem storageItem : model.getStorageItems()) {
            if (linkedEntity instanceof StorageItemEntity) {
                storageItems.add((StorageItemEntity) linkedEntity);
            } else {
                storageItems.add(MapperManager.getMapper(StorageItemMapper.class).map(storageItem, storageEntity));
            }
        }
        storageEntity.setStorageItems(storageItems);
        
        return storageEntity;
    }

    @Override
    public Storage map(final StorageEntity entity, AbstractModel linkedModel) {
        Storage storage = new Storage(entity.getId());
        
        // DIRECT
        storage.setStorageType(entity.getStorageType());
        
        // STORAGE ITEM
        List<StorageItem> storageItems = new FastList<>();
        for (StorageItemEntity storageItemEntity : entity.getStorageItems()) {
            if (linkedModel instanceof StorageItem) {
                storageItems.add((StorageItem) linkedModel);
            } else {
                storageItems.add(MapperManager.getMapper(StorageItemMapper.class).map(storageItemEntity, storage));
            }
        }
        storage.setStorageItems(storageItems);
        
        return storage;
    }

    @Override
    public boolean equals(AbstractModel model, AbstractEntity entity) {
        return model.getId() == entity.getId();
    }
}
