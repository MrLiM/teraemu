package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_QUIT_WINDOW_SHOW extends TeraServerPacket {

    private final int timeOut;

    public SM_QUIT_WINDOW_SHOW(final int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeD(byteBuffer, this.timeOut);
    }
}
