package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_RESPONSE_PLAYER_UNLOCK extends TeraServerPacket {

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeD(byteBuffer, 1);
        writeD(byteBuffer, 0);
        writeD(byteBuffer, 0);
        writeB(byteBuffer, "01FA0000");
    }

}
