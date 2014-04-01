package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;

public class CM_SERVERGUILD_WINDOW_OPEN extends TeraClientPacket {

    public CM_SERVERGUILD_WINDOW_OPEN(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void readImpl() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void runImpl() {
        // TODO Auto-generated method stub
        
    }

}
