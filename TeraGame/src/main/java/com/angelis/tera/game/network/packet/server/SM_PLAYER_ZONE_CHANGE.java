package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_ZONE_CHANGE extends TeraServerPacket {

    private final byte[] zoneData;
    
    public SM_PLAYER_ZONE_CHANGE(byte[] zoneData) {
        this.zoneData = zoneData;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeC(byteBuffer, 0);
        writeB(byteBuffer, this.zoneData);
    }
}
