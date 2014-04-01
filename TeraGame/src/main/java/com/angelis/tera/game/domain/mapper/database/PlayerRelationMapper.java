package com.angelis.tera.game.domain.mapper.database;

import com.angelis.tera.common.domain.mapper.DatabaseMapper;
import com.angelis.tera.common.entity.AbstractEntity;
import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.database.entity.FriendEntity;
import com.angelis.tera.game.models.player.PlayerRelation;

public class PlayerRelationMapper extends DatabaseMapper<FriendEntity, PlayerRelation> {

    @Override
    public FriendEntity map(PlayerRelation model, AbstractEntity linkedEntity) {
        FriendEntity friendEntity = new FriendEntity(model.getId());
        
        return friendEntity;
    }

    @Override
    public PlayerRelation map(FriendEntity entity, AbstractModel linkedModel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equals(AbstractModel model, AbstractEntity entity) {
        // TODO Auto-generated method stub
        return false;
    }

}
