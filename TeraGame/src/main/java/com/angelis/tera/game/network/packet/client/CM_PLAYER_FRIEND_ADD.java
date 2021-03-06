package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.PlayerService;

public class CM_PLAYER_FRIEND_ADD extends TeraClientPacket {

    private String playerName;
    
    public CM_PLAYER_FRIEND_ADD(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readH();
        this.playerName = readS();
    }

    @Override
    protected void runImpl() {
        PlayerService.getInstance().onPlayerFriendAdd(this.getConnection().getActivePlayer(), this.playerName);
    }

}
