package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.enums.ReportTypeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;

public class CM_PLAYER_REPORT extends TeraClientPacket {

    private ReportTypeEnum reportType;
    private String playerName;
    
    public CM_PLAYER_REPORT(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readH(); // unk (0A00)
        this.reportType = ReportTypeEnum.fromValue(readD());
        this.playerName = readS();
    }

    @Override
    protected void runImpl() {
        // TODO
    }

}
