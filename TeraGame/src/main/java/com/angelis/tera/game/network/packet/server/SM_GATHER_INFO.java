package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.Gather;
import com.angelis.tera.game.models.WorldPosition;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_GATHER_INFO extends TeraServerPacket {

    private final Gather gather;
    
    public SM_GATHER_INFO(Gather gather) {
        this.gather = gather;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        final WorldPosition worldPosition = this.gather.getWorldPosition();
        
        writeUid(byteBuffer, this.gather);
        writeD(byteBuffer, this.gather.getId());
        writeD(byteBuffer, this.gather.getCurrentGatherCount()); //gather counter
        writeF(byteBuffer, worldPosition.getX());
        writeF(byteBuffer, worldPosition.getY());
        writeF(byteBuffer, worldPosition.getZ());
        
        writeD(byteBuffer, 0);
        writeH(byteBuffer, 0);
    }
}
