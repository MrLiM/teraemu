package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_ENTER_CHANNEL extends TeraServerPacket {

    private final int canalNumber;
    
    public SM_PLAYER_ENTER_CHANNEL(int canalNumber) {
        super();
        this.canalNumber = canalNumber;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeH(byteBuffer, 0);
        writeC(byteBuffer, 0);
        writeD(byteBuffer, canalNumber);
        writeD(byteBuffer, 1);
    }

}
