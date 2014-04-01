package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_EMOTE extends TeraServerPacket {

    private final Player player;
    private final int emoteId;
    private final int duration;
    
    public SM_PLAYER_EMOTE(final Player player, final int emoteId, final int duration) {
        this.player = player;
        this.emoteId = emoteId;
        this.duration = duration;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        // 914B0E00 00800001 11000000 00000000 00
        writeUid(byteBuffer, player);
        writeD(byteBuffer, emoteId);
        writeC(byteBuffer, 0); //unk
    }
}
