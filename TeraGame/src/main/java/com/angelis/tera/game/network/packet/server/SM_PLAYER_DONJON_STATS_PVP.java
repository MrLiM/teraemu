package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_DONJON_STATS_PVP extends TeraServerPacket {

    public SM_PLAYER_DONJON_STATS_PVP(Player player) {

    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        // TODO
        writeB(byteBuffer, "D855040000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E803000000000000000000000000000000000000000000000000000000000000000000000000000000000000E80300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E803000000000000");
    }
}
