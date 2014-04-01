package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.enums.ChatTypeEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_CHAT extends TeraServerPacket {

    private final Player player;
    private final String message;
    private final ChatTypeEnum chatType;
    
    public SM_PLAYER_CHAT(Player player, String message, ChatTypeEnum chatType) {
        this.player = player;
        this.message = message;
        this.chatType = chatType;
    }
    
    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeH(byteBuffer, 0); //Sender shift
        writeH(byteBuffer, 0); //Message shift
        writeD(byteBuffer, this.chatType.getValue());

        writeUid(byteBuffer, this.player);

        writeC(byteBuffer, 0); //Blue shit
        writeC(byteBuffer, player.getAccount().getAccess() > 0); //GM

        if (this.player != null) {
            this.writeBufferPosition(byteBuffer, 4, Size.H);
            writeS(byteBuffer, player.getName());
        }

        this.writeBufferPosition(byteBuffer, 6, Size.H);
        writeS(byteBuffer, this.message);

        writeC(byteBuffer, 0);
    }

}
