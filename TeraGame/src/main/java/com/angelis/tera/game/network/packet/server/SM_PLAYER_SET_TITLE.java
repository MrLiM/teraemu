package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_SET_TITLE extends TeraServerPacket {

    private final Player player;
    private final int title;
    
    public SM_PLAYER_SET_TITLE(Player player, int title) {
        super();
        this.player = player;
        this.title = title;
    }



    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeUid(byteBuffer, this.player); // TO CHECK
        writeD(byteBuffer, this.title);
        writeD(byteBuffer, 0); // UNK TIME LEFT
        writeD(byteBuffer, 0); // UNK TIME MAX
    }

}
