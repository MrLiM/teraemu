package com.angelis.tera.game.domain.mapper.database;

import java.util.Set;

import javolution.util.FastSet;

import com.angelis.tera.common.domain.mapper.DatabaseMapper;
import com.angelis.tera.common.domain.mapper.MapperManager;
import com.angelis.tera.common.entity.AbstractEntity;
import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.database.entity.AccountEntity;
import com.angelis.tera.game.database.entity.FriendEntity;
import com.angelis.tera.game.database.entity.PlayerEntity;
import com.angelis.tera.game.database.entity.StorageEntity;
import com.angelis.tera.game.models.CreatureCurrentStats;
import com.angelis.tera.game.models.WorldPosition;
import com.angelis.tera.game.models.account.Account;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.player.PlayerAppearance;
import com.angelis.tera.game.models.player.PlayerRelation;
import com.angelis.tera.game.models.storage.Storage;

public class PlayerMapper extends DatabaseMapper<PlayerEntity, Player> {
    
    @Override
    public PlayerEntity map(final Player model, AbstractEntity linkedEntity) {
        PlayerEntity playerEntity = new PlayerEntity(model.getId());

        // DIRECT
        playerEntity.setName(model.getName());
        playerEntity.setGender(model.getGender());
        playerEntity.setRace(model.getRace());
        playerEntity.setPlayerClass(model.getPlayerClass());
        playerEntity.setLevel(model.getLevel());
        playerEntity.setExperience(model.getExperience());
        playerEntity.setMoney(model.getMoney());
        playerEntity.setCreationTime(model.getCreationTime());
        playerEntity.setDeletionTime(model.getDeletionTime());
        playerEntity.setLastOnlineTime(model.getLastOnlineTime());
        playerEntity.setOnline(model.isOnline());
        playerEntity.setTitle(model.getTitle());
        playerEntity.setPlayerMode(model.getPlayerMode());
        playerEntity.setZoneData(model.getZoneData());
        
        // GUILD
        playerEntity.setGuild(MapperManager.getMapper(GuildMapper.class).map(model.getGuild()));
        
        // STORAGE
        Set<StorageEntity> storages = new FastSet<>();
        for (Storage storage: model.getStorages()) {
            storages.add(MapperManager.getMapper(StorageMapper.class).map(storage));
        }
        playerEntity.setStorages(storages);
        
        // FRIENDS
        Set<FriendEntity> friends = new FastSet<>();
        for (PlayerRelation relation : model.getFriends()) {
            if (linkedEntity instanceof PlayerEntity && this.equals(relation, linkedEntity)) {
                friends.add((FriendEntity) linkedEntity);
            } else {
                friends.add(MapperManager.getMapper(PlayerRelationMapper.class).map(relation, playerEntity));
            }
        }
        
        // BLOCKEDS
        
        // CREATURE STATS
        final CreatureCurrentStats currentStats = model.getCreatureCurrentStats();
        playerEntity.setHp(currentStats.getHp());
        playerEntity.setMaxHp(currentStats.getMaxHp());
        playerEntity.setMp(currentStats.getMp());
        playerEntity.setMaxMp(currentStats.getMaxMp());
        playerEntity.setStamina(currentStats.getStamina());
        
        
        playerEntity.setSpeed(currentStats.getSpeed());
        
        playerEntity.setAttack(currentStats.getAttack());
        playerEntity.setDefense(currentStats.getDefense());
        playerEntity.setImpact(currentStats.getImpact());
        playerEntity.setBalance(currentStats.getBalance());
        playerEntity.setCritChance(currentStats.getCritChance());
        playerEntity.setCritResist(currentStats.getCritResist());
        
        // WORLD POSITION
        final WorldPosition worldPosition = model.getWorldPosition();
        playerEntity.setMapId(worldPosition.getMapId());
        playerEntity.setX(worldPosition.getX());
        playerEntity.setY(worldPosition.getY());
        playerEntity.setZ(worldPosition.getZ());
        playerEntity.setHeading(worldPosition.getHeading());
        
        // PLAYER APPEARANCE
        final PlayerAppearance playerAppearance = model.getPlayerAppearance();
        playerEntity.setData(playerAppearance.getData());
        playerEntity.setDetails1(playerAppearance.getDetails1());
        playerEntity.setDetails2(playerAppearance.getDetails2());
        
        // ACCOUNT
        if (linkedEntity instanceof AccountEntity) {
            playerEntity.setAccount((AccountEntity) linkedEntity);
        } else {
            playerEntity.setAccount(MapperManager.getMapper(AccountMapper.class).map(model.getAccount(), playerEntity));
        }
        
        return playerEntity;
    }
    
    @Override
    public Player map(final PlayerEntity entity, AbstractModel linkedModel) {
        Player player = new Player(entity.getId());
        
        // DIRECT
        player.setName(entity.getName());
        player.setGender(entity.getGender());
        player.setRace(entity.getRace());
        player.setPlayerClass(entity.getPlayerClass());
        player.setLevel(entity.getLevel());
        player.setExperience(entity.getExperience());
        player.setMoney(entity.getMoney());
        player.setCreationTime(entity.getCreationTime());
        player.setDeletionTime(entity.getDeletionTime());
        player.setLastOnlineTime(entity.getLastOnlineTime());
        player.setOnline(entity.isOnline());
        player.setTitle(entity.getTitle());
        player.setPlayerMode(entity.getPlayerMode());
        player.setZoneData(entity.getZoneData());

        // GUILD
        player.setGuild(MapperManager.getMapper(GuildMapper.class).map(entity.getGuild()));
        
        // STORAGE
        Set<Storage> storages = new FastSet<>();
        for (StorageEntity storage: entity.getStorages()) {
            storages.add(MapperManager.getMapper(StorageMapper.class).map(storage));
        }
        player.setStorages(storages);
        
        // CREATURE STATS
        final CreatureCurrentStats currentStats = new CreatureCurrentStats();
        currentStats.setHp(entity.getHp());
        currentStats.setMaxHp(entity.getMaxHp());
        currentStats.setMp(entity.getMp());
        currentStats.setMaxHp(entity.getMaxHp());
        currentStats.setStamina(entity.getStamina());
        currentStats.setSpeed(entity.getSpeed());
        currentStats.setAttack(entity.getAttack());
        currentStats.setDefense(entity.getDefense());
        currentStats.setImpact(entity.getImpact());
        currentStats.setBalance(entity.getBalance());
        currentStats.setCritChance(entity.getCritChance());
        currentStats.setCritResist(entity.getCritResist());
        player.setCreatureCurrentStats(currentStats);
        
        // WORLD POSITION
        final WorldPosition worldPosition = new WorldPosition();
        worldPosition.setMapId(entity.getMapId());
        worldPosition.setX(entity.getX());
        worldPosition.setY(entity.getY());
        worldPosition.setZ(entity.getZ());
        worldPosition.setHeading(entity.getHeading());
        player.setWorldPosition(worldPosition);
        
        // PLAYER APPEARANCE
        final PlayerAppearance playerAppearance = new PlayerAppearance();
        playerAppearance.setData(entity.getData());
        playerAppearance.setDetails1(entity.getDetails1());
        playerAppearance.setDetails2(entity.getDetails2());
        player.setPlayerAppearance(playerAppearance);
        
        // ACCOUNT
        if (linkedModel instanceof Account) {
            player.setAccount((Account) linkedModel);
        } else {
            player.setAccount(MapperManager.getMapper(AccountMapper.class).map(entity.getAccount()));
        }
        
        return player;
    }

    @Override
    public boolean equals(AbstractModel model, AbstractEntity entity) {
        return model.getId() == entity.getId();
    }
}
