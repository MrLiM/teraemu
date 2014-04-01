package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_OPTION_SHOW_MASK extends TeraServerPacket {

    private final boolean maskVisible;
    
    public SM_OPTION_SHOW_MASK(boolean maskVisible) {
        this.maskVisible = maskVisible;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeUid(byteBuffer, connection.getActivePlayer());
        writeC(byteBuffer, this.maskVisible);
    }
}
