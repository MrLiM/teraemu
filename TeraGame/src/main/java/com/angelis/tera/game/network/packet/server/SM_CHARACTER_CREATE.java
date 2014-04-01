package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_CHARACTER_CREATE extends TeraServerPacket {

    private boolean creationSuccess;

    public SM_CHARACTER_CREATE(boolean creationSuccess) {
        this.creationSuccess = creationSuccess;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeC(byteBuffer, creationSuccess);
    }
}
