package com.angelis.tera.game.services;

import java.util.Set;

import javolution.util.FastSet;

import org.apache.log4j.Logger;

import com.angelis.tera.game.models.enums.StorageTypeEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.storage.Storage;
import com.angelis.tera.game.models.storage.StorageItem;
import com.angelis.tera.game.xml.entity.ItemEntity;
import com.angelis.tera.game.xml.entity.ItemSetEntity;
import com.angelis.tera.game.xml.entity.ItemSetEntityHolder;

public class StorageService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(StorageService.class.getName());
    
    /** INSTANCE */
    private static StorageService instance = null;
    
    @Override
    public void onInit() {
        log.info("StorageService started");
    }

    @Override
    public void onDestroy() {
    }
    
    public void onPlayerCreate(Player player) {
        Set<Storage> storages = new FastSet<>();
        storages.add(new Storage(StorageTypeEnum.INVENTORY));
        storages.add(new Storage(StorageTypeEnum.PLAYER_WAREHOUSE));
        player.setStorages(storages);
        
        ItemSetEntity itemset = XMLService.getInstance().getEntity(ItemSetEntityHolder.class).getItemsetsByTargetClass(player.getPlayerClass());
        for (ItemEntity item : itemset.getItems()) {
            StorageItem storageItem = new StorageItem();
            storageItem.setItemId(item.getItemId());
            storageItem.setSlot(item.getSlot());
            storageItem.setCount(1);
            
            player.getStorage(StorageTypeEnum.INVENTORY).addStorageItem(storageItem);
        }
    }
    
    /** SINGLETON */
    public static StorageService getInstance() {
        if (instance == null) {
            instance = new StorageService();
        }
        return instance;
    }

    
}
