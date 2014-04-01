package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_CHARACTER_RESTORE extends TeraServerPacket {

    private boolean restoreSuccess;
    
    public SM_CHARACTER_RESTORE(boolean restoreSuccess) {
        this.restoreSuccess = restoreSuccess;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeC(byteBuffer, restoreSuccess);
    }

}
