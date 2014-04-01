package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.WorldPosition;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_BIND extends TeraServerPacket {

    private final Player player;
    
    public SM_PLAYER_BIND(Player player) {
        super();
        this.player = player;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        final WorldPosition worldPosition = this.player.getWorldPosition();
        
        writeD(byteBuffer, worldPosition.getMapId());
        writeF(byteBuffer, worldPosition.getX());
        writeF(byteBuffer, worldPosition.getY());
        writeF(byteBuffer, worldPosition.getZ());
        writeC(byteBuffer, 0); //NOT Heading
    }

}
