package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_RESPONSE_CHARACTER_NAME_CHECK_USED extends TeraServerPacket {

    private boolean nameFree;
    
    public SM_RESPONSE_CHARACTER_NAME_CHECK_USED(boolean nameNotUsed) {
        this.nameFree = nameNotUsed;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeC(byteBuffer, this.nameFree);
    }
}
