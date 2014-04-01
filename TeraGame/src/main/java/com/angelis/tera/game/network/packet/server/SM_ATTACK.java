package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_ATTACK extends TeraServerPacket {

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        
    }
}
