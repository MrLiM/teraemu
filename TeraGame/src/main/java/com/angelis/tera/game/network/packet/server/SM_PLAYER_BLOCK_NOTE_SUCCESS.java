package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_BLOCK_NOTE_SUCCESS extends TeraServerPacket {

    private final Player player;
    private final String note;
    
    public SM_PLAYER_BLOCK_NOTE_SUCCESS(Player player, String note) {
        this.player = player;
        this.note = note;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        
    }

}
