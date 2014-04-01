package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_OPCODE_LESS_PACKET;

public class CM_UNK_ENTER_WORLD3 extends TeraClientPacket {

    public CM_UNK_ENTER_WORLD3(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        // empty packet
    }

    @Override
    protected void runImpl() {
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("12C9020008000800180001000000020000000600000018000000010000000200000007000000"));
    }

}
