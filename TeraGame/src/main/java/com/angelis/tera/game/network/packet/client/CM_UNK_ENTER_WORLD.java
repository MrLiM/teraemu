package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_OPCODE_LESS_PACKET;

public class CM_UNK_ENTER_WORLD extends TeraClientPacket {

    public CM_UNK_ENTER_WORLD(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        
    }

    @Override
    protected void runImpl() {
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("46C6C7000000"));
    }

}
