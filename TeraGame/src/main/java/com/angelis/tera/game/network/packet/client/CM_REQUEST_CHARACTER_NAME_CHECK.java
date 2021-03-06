package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.enums.CheckNameTypeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_CHARACTER_NAME_CHECK;
import com.angelis.tera.game.services.PlayerService;

public class CM_REQUEST_CHARACTER_NAME_CHECK extends TeraClientPacket {

    private CheckNameTypeEnum type;
    private String name;
    
    public CM_REQUEST_CHARACTER_NAME_CHECK(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readB(14);
        this.type = CheckNameTypeEnum.fromCode(readH());
        readH();
        name = readS();
    }

    @Override
    protected void runImpl() {
        this.getConnection().sendPacket(new SM_RESPONSE_CHARACTER_NAME_CHECK(type, PlayerService.getInstance().checkNamePattern(type, name), name));
    }
}
