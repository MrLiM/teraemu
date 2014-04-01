package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.Creature;
import com.angelis.tera.game.models.WorldPosition;
import com.angelis.tera.game.models.enums.DespawnTypeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_CREATURE_REMOVE extends TeraServerPacket {

    private final Creature creature;
    private final DespawnTypeEnum despawnType;

    public SM_CREATURE_REMOVE(final Creature creature, final DespawnTypeEnum despawnType) {
        this.creature = creature;
        this.despawnType = despawnType;
    }
    
    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        final WorldPosition worldPosition = this.creature.getWorldPosition();
        
        writeUid(byteBuffer, this.creature);
        writeF(byteBuffer, worldPosition.getX());
        writeF(byteBuffer, worldPosition.getY());
        writeF(byteBuffer, worldPosition.getZ());
        writeD(byteBuffer, this.despawnType.value);
        writeD(byteBuffer, 0);
    }
}
