package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.DialogService;

public class CM_PLAYER_DIALOG_WINDOW_SHOW extends TeraClientPacket {

    private int npcId;
    private int objectFamilly;
    
    public CM_PLAYER_DIALOG_WINDOW_SHOW(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }
    
    @Override
    protected void readImpl() {
        this.npcId = readD();
        this.objectFamilly = readD();
    }

    @Override
    protected void runImpl() {
        DialogService.getInstance().onPlayerTalk(this.getConnection().getActivePlayer(), this.npcId, this.objectFamilly);
    }
}
