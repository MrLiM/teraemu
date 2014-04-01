package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_CHARACTER_DELETE extends TeraServerPacket {

    private boolean deleteSuccess;

    public SM_CHARACTER_DELETE(boolean deleteSuccess) {
        this.deleteSuccess = deleteSuccess;
    }
    
    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeC(byteBuffer, deleteSuccess);
    }

}
