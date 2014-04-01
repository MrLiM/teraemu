package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_CHAT_INFO extends TeraServerPacket {

    private final Player player;
    private final int version;
    private final int type;
    
    public SM_CHAT_INFO(final Player player, final int version, final int type) {
        this.player = player;
        this.version = version;
        this.type = type;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeH(byteBuffer, 0); //name start shift
        writeD(byteBuffer, this.type);
        writeD(byteBuffer, this.player.getRaceGenderClassValue());
        writeD(byteBuffer, this.player.getLevel());

        writeB(byteBuffer, "0001");
        writeD(byteBuffer, this.version);

        this.writeBufferPosition(byteBuffer, 4, Size.H);
        writeS(byteBuffer, this.player.getName());
    }
}
