package com.angelis.tera.game.network.packet.client.key;

import java.nio.ByteBuffer;

import com.angelis.tera.common.network.crypt.CryptSession;
import com.angelis.tera.common.network.packet.AbstractClientPacket;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.server.key.KeyServerPacket;

public class KeyClientPacket extends AbstractClientPacket<TeraGameConnection> {

    private byte[] data = null;

    public KeyClientPacket(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    
    @Override
    protected void readImpl() {
        this.data = readB(128);
    }

    @Override
    protected void runImpl() {
        TeraGameConnection connection = this.getConnection();
        CryptSession session = connection.getCryptSession();
        session.readKeyPacket(this.data);
        
        connection.sendPacket(new KeyServerPacket());
    }
}   
