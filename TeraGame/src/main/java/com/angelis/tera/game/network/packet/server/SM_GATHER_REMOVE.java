package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.Gather;
import com.angelis.tera.game.models.enums.DespawnTypeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_GATHER_REMOVE extends TeraServerPacket {

    private final Gather gather;
    private final DespawnTypeEnum despawnType;
    
    public SM_GATHER_REMOVE(Gather gather, DespawnTypeEnum despawnType) {
        this.gather = gather;
        this.despawnType = despawnType;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeUid(byteBuffer, this.gather);
        writeC(byteBuffer, this.despawnType.value);
    }
}
