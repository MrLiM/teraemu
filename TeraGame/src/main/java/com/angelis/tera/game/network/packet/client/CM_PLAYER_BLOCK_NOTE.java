package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.PlayerService;

public class CM_PLAYER_BLOCK_NOTE extends TeraClientPacket {

    private int playerId;
    private String note;

    public CM_PLAYER_BLOCK_NOTE(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readH(); // unk 0A00
        this.playerId = readD();
        this.note = readS();
    }

    @Override
    protected void runImpl() {
        PlayerService.getInstance().onPlayerBlockNote(this.getConnection().getActivePlayer(), this.playerId, this.note);
    }

}
