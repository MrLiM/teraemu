package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_BLOCK_ADD_SUCCESS extends TeraServerPacket {

    private final Player player;
    
    public SM_PLAYER_BLOCK_ADD_SUCCESS(Player player) {
        this.player = player;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeB(byteBuffer, "0C001600"); // unk
        writeD(byteBuffer, this.player.getId());
        writeS(byteBuffer, this.player.getName());
        writeH(byteBuffer, 0); // unk
    }
}
