package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_PONG;

public class CM_PING extends TeraClientPacket {

    private int data;
    
    public CM_PING(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.data = readD();
    }

    @Override
    protected void runImpl() {
        this.getConnection().sendPacket(new SM_PONG(data));
    }
}
