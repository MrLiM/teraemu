package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.PlayerService;

public class CM_PLAYER_EMOTE extends TeraClientPacket {

    private int emotionId;
    private int duration;
    
    public CM_PLAYER_EMOTE(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.emotionId = readD();
        this.duration = readC();
    }

    @Override
    protected void runImpl() {
        PlayerService.getInstance().onPlayerEmote(this.getConnection().getActivePlayer(), this.emotionId, this.duration);
    }
}
