package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.enums.ChatTypeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.ChatService;

public class CM_PLAYER_CHAT extends TeraClientPacket {

    private String chat;
    private ChatTypeEnum chatType;
    
    public CM_PLAYER_CHAT(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readH(); // length
        
        this.chatType = ChatTypeEnum.fromValue(readD());
        this.chat = readS();
    }

    @Override
    protected void runImpl() {
        ChatService.getInstance().onPlayerChat(this.getConnection().getActivePlayer(), this.chat, this.chatType);
    }
}
