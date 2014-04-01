package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_PLAYER_UNLOCK;

public class CM_PLAYER_SEND_REQUEST extends TeraClientPacket {

    public CM_PLAYER_SEND_REQUEST(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        
    }

    @Override
    protected void runImpl() {
        this.getConnection().sendPacket(new SM_RESPONSE_PLAYER_UNLOCK());
    }

    
}
