package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.database.entity.PlayerEntity;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_SKILL_LIST extends TeraServerPacket {

    private final PlayerEntity player;
    
    public SM_SKILL_LIST(PlayerEntity player) {
        this.player = player;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        
    }
}
