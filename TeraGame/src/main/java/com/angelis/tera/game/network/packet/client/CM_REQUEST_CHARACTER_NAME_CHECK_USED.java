package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.PlayerService;

public class CM_REQUEST_CHARACTER_NAME_CHECK_USED extends TeraClientPacket {

    private short type;
    private String name;
    
    public CM_REQUEST_CHARACTER_NAME_CHECK_USED(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.type = readH();
        this.name = readS();
    }

    @Override
    protected void runImpl() {
        PlayerService.getInstance().onPlayerCheckNameUsed(this.getConnection(), type, name);
    }
}
