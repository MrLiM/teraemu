package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;

public class CM_PLAYER_DIALOG_SELECT extends TeraClientPacket {

    public CM_PLAYER_DIALOG_SELECT(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readD(); // dialog id
        readQ();
    }

    @Override
    protected void runImpl() {
        // TODO Auto-generated method stub
        
    }

}
