package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_FRIEND_ADD_SUCCESS extends TeraServerPacket {

    private final Player player;
    
    
    public SM_PLAYER_FRIEND_ADD_SUCCESS(Player player) {
        this.player = player;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeB(byteBuffer, "2800");
        writeD(byteBuffer, player.getId());
        writeD(byteBuffer, this.player.getLevel());
        writeD(byteBuffer, this.player.getRace().value);
        writeD(byteBuffer, this.player.getPlayerClass().value);
        writeB(byteBuffer, this.player.getZoneData());
        writeB(byteBuffer, "100000000000"); // unk
        writeS(byteBuffer, player.getName());
    }
}
