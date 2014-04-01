package com.angelis.tera.game.delegate;

import com.angelis.tera.common.database.dao.DAOManager;
import com.angelis.tera.common.domain.mapper.MapperManager;
import com.angelis.tera.game.database.dao.PlayerDAO;
import com.angelis.tera.game.database.entity.PlayerEntity;
import com.angelis.tera.game.domain.mapper.database.PlayerMapper;
import com.angelis.tera.game.models.player.Player;

public class PlayerDelegate {
    
    //------------------------------------------------------------
    //                       PlayerEntity
    //------------------------------------------------------------
    public static void createPlayerEntity(PlayerEntity entity) {
        DAOManager.getDAO(PlayerDAO.class).create(entity);
    }
    
    public static PlayerEntity readPlayerEntityById(Integer id) {
        return DAOManager.getDAO(PlayerDAO.class).read(id);
    }
    
    public static PlayerEntity readPlayerEntityByName(String name) {
        return DAOManager.getDAO(PlayerDAO.class).readByName(name);
    }
    
    public static void updatePlayerEntity(PlayerEntity entity) {
        DAOManager.getDAO(PlayerDAO.class).update(entity);
    }
    
    public static void deletePlayerEntity(PlayerEntity entity) {
        DAOManager.getDAO(PlayerDAO.class).delete(entity);
    }
    
    //------------------------------------------------------------
    //                          Player
    //------------------------------------------------------------
    public static void createPlayerModel(Player model) {
        PlayerEntity playerEntity = MapperManager.getMapper(PlayerMapper.class).map(model);
        PlayerDelegate.createPlayerEntity(playerEntity);
        model.setId(playerEntity.getId());
    }
    
    public static Player readPlayerModelById(Integer id) {
        Player player = null;
        PlayerEntity playerEntity = PlayerDelegate.readPlayerEntityById(id);
        if (playerEntity != null) {
            player = MapperManager.getMapper(PlayerMapper.class).map(playerEntity);
        }
        return player;
    }
    
    public static Player readPlayerModelByName(String name) {
        Player player = null;
        PlayerEntity playerEntity = PlayerDelegate.readPlayerEntityByName(name);
        if (playerEntity != null) {
            player = MapperManager.getMapper(PlayerMapper.class).map(playerEntity);
        }
        return player;
    }
    
    public static void updatePlayerModel(Player model) {
        PlayerEntity playerEntity = MapperManager.getMapper(PlayerMapper.class).map(model);
        PlayerDelegate.updatePlayerEntity(playerEntity);
    }

    public static void deletePlayerModel(Player model) {
        PlayerEntity playerEntity = MapperManager.getMapper(PlayerMapper.class).map(model);
        PlayerDelegate.deletePlayerEntity(playerEntity);
    }
}
