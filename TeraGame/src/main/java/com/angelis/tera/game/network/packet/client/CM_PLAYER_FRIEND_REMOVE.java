package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.PlayerService;

public class CM_PLAYER_FRIEND_REMOVE extends TeraClientPacket {

    private int playerId;
    
    public CM_PLAYER_FRIEND_REMOVE(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.playerId = readD();
    }

    @Override
    protected void runImpl() {
        PlayerService.getInstance().onPlayerFriendRemove(this.getConnection().getActivePlayer(), this.playerId);
    }

}
