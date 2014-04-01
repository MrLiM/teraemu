package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.TeraCreature;
import com.angelis.tera.game.models.WorldPosition;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_CREATURE_MOVE extends TeraServerPacket {

    private final TeraCreature teraCreature;
    private final float x;
    private final float y;
    private final float z;
    
    public SM_CREATURE_MOVE(final TeraCreature teraCreature, final float x, final float y, final float z) {
        this.teraCreature = teraCreature;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        final WorldPosition worldPosition = this.teraCreature.getWorldPosition();
        
        writeUid(byteBuffer, this.teraCreature);
        writeF(byteBuffer, x);
        writeF(byteBuffer, y);
        writeF(byteBuffer, z);
        writeH(byteBuffer, worldPosition.getHeading());
        writeH(byteBuffer, this.teraCreature.getCreatureCurrentStats().getSpeed());
        
        writeF(byteBuffer, worldPosition.getX());
        writeF(byteBuffer, worldPosition.getY());
        writeF(byteBuffer, worldPosition.getZ());
        writeD(byteBuffer, 0);
    }

}
