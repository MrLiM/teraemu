package com.angelis.tera.game.network.packet.server.key;

import java.nio.ByteBuffer;

import com.angelis.tera.common.network.crypt.CryptSession;
import com.angelis.tera.common.network.packet.AbstractServerPacket;
import com.angelis.tera.game.network.connection.TeraGameConnection;

public class KeyServerPacket extends AbstractServerPacket<TeraGameConnection> {

    @Override
    public void write(TeraGameConnection connection, ByteBuffer byteBuffer) {
        CryptSession cryptSession = connection.getCryptSession();
        writeB(byteBuffer, cryptSession.sendKeyPacket());
        
        byteBuffer.flip();
        byteBuffer.position(0);
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer buffer) {
        // never called
    }
    
    public void setOpcode(Short opcode) {
        // useless
    }

    @Override
    public boolean showInDebug() {
        return false;
    }
}
