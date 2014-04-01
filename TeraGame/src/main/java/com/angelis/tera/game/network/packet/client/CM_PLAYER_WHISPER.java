package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.ChatService;

public class CM_PLAYER_WHISPER extends TeraClientPacket {

    private String name;
    private String whisper;
    
    public CM_PLAYER_WHISPER(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readH(); // 08 00
        readH(); // 12 00
        this.name = readS();
        this.whisper = readS();
    }

    @Override
    protected void runImpl() {
        ChatService.getInstance().onPlayerWhisper(this.getConnection().getActivePlayer(), this.name, this.whisper);
    }
}
