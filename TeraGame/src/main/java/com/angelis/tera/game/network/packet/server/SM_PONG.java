package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PONG extends TeraServerPacket {

    private final int data;
    
    public SM_PONG(int data) {
        this.data = data;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeD(byteBuffer, this.data);
        writeC(byteBuffer, 1);
    }
}
