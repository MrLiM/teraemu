package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.PlayerService;

public class CM_PLAYER_ZONE_CHANGE extends TeraClientPacket {

    private byte[] zoneData;
    
    public CM_PLAYER_ZONE_CHANGE(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.zoneData = readB(12);
    }

    @Override
    protected void runImpl() {
        PlayerService.getInstance().onPlayerZoneChange(this.getConnection().getActivePlayer(), this.zoneData);
    }

}
