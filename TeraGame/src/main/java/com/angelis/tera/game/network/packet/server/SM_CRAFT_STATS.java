package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.database.entity.PlayerEntity;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_CRAFT_STATS extends TeraServerPacket {

    private final PlayerEntity player;
    
    public SM_CRAFT_STATS(PlayerEntity player) {
        this.player = player;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        
    }
}
