package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.WorldPosition;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_TELEPORT extends TeraServerPacket {

    private final Player player;
    
    public SM_PLAYER_TELEPORT(final Player player) {
        this.player = player;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeUid(byteBuffer, this.player);
        
        final WorldPosition worldPosition = this.player.getWorldPosition();
        writeF(byteBuffer, worldPosition.getX());
        writeF(byteBuffer, worldPosition.getY());
        writeF(byteBuffer, worldPosition.getZ());
        writeH(byteBuffer, worldPosition.getHeading());
        writeC(byteBuffer, this.player.getCreatureCurrentStats().isDead() ? 0 : 1);
    }
}
