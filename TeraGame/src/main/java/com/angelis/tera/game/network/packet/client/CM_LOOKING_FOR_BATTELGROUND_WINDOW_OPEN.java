package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;

public class CM_LOOKING_FOR_BATTELGROUND_WINDOW_OPEN extends TeraClientPacket {

    public CM_LOOKING_FOR_BATTELGROUND_WINDOW_OPEN(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        // empty
    }

    @Override
    protected void runImpl() {
        // TODO Auto-generated method stub
        
    }

}
