package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

/**
 * 
 * @author Angelis
 * Server tell if user can create character
 */
public class SM_RESPONSE_CREATE_CHARACTER extends TeraServerPacket {
    
    private boolean createAllowed;
    
    public SM_RESPONSE_CREATE_CHARACTER(boolean createAllowed) {
        this.createAllowed = createAllowed;
    }
    
    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeC(byteBuffer, createAllowed);
    }
}
