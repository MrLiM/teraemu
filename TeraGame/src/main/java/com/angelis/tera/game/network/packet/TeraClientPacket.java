package com.angelis.tera.game.network.packet;

import java.nio.ByteBuffer;

import com.angelis.tera.common.network.packet.AbstractClientPacket;
import com.angelis.tera.game.network.connection.TeraGameConnection;

public abstract class TeraClientPacket extends AbstractClientPacket<TeraGameConnection> {

    public TeraClientPacket(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }
}
