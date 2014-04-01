package com.angelis.tera.game.domain.mapper.database;

import com.angelis.tera.common.domain.mapper.DatabaseMapper;
import com.angelis.tera.common.entity.AbstractEntity;
import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.database.entity.GuildEntity;
import com.angelis.tera.game.models.Guild;

public class GuildMapper extends DatabaseMapper<GuildEntity, Guild> {

    @Override
    public GuildEntity map(Guild model, AbstractEntity linkedEntity) {
        return null;
    }
    
    @Override
    public Guild map(GuildEntity entity, AbstractModel linkedModel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equals(AbstractModel model, AbstractEntity entity) {
        // TODO Auto-generated method stub
        return false;
    }
}
