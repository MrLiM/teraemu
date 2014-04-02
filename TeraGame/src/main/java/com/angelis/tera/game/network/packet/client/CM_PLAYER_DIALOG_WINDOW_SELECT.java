package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.DialogService;

public class CM_PLAYER_DIALOG_WINDOW_SELECT extends TeraClientPacket {

    protected int dialogUid;
    protected int selectedIndex;
    
    public CM_PLAYER_DIALOG_WINDOW_SELECT(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.dialogUid = readD();
        this.selectedIndex = readD();
        readD(); //FFFFFFFF
        readD(); //FFFFFFFF
    }

    @Override
    protected void runImpl() {
        DialogService.getInstance().onPlayerTalkProgress(this.getConnection().getActivePlayer(), this.dialogUid, this.selectedIndex);
    }

}
