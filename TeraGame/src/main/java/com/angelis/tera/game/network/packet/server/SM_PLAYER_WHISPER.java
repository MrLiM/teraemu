package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_WHISPER extends TeraServerPacket {

    private final Player sender;
    private final Player receiver;
    private final String whisper;
    
    public SM_PLAYER_WHISPER(Player sender, Player receiver, String whisper) {
        this.sender = sender;
        this.receiver = receiver;
        this.whisper = whisper;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeH(byteBuffer, 0); //first name shift
        writeH(byteBuffer, 0); //second name shift
        writeH(byteBuffer, 0); //message shift
        
        writeC(byteBuffer, 0);

        this.writeBufferPosition(byteBuffer, 4, Size.H);
        writeS(byteBuffer, this.sender.getName());

        this.writeBufferPosition(byteBuffer, 6, Size.H);
        writeS(byteBuffer, this.receiver.getName());

        this.writeBufferPosition(byteBuffer, 8, Size.H);
        writeS(byteBuffer, this.whisper);
    }
}
