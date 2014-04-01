package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;

public class CM_REQUEST_SYSTEM_INFO extends TeraClientPacket {

    public CM_REQUEST_SYSTEM_INFO(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readB(4);
    }

    @Override
    protected void runImpl() {
        // nothing to do
    }
}
