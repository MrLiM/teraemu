package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_RESPONSE_ACCOUNT_OBJECT extends TeraServerPacket {

    private final Player player;
    
    public SM_RESPONSE_ACCOUNT_OBJECT(Player player) {
        this.player = player;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        // 030008000800140013000000A30100001400200014000000A501000020000000170000009C010000
    }

}
