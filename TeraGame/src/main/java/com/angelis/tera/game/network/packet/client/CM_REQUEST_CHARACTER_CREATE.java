package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.account.enums.AccountTypeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_CREATE_CHARACTER;

/**
 * 
 * @author Angelis
 * 
 * Client ask server if he can create moer characters
 */
public class CM_REQUEST_CHARACTER_CREATE extends TeraClientPacket {

    public CM_REQUEST_CHARACTER_CREATE(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        // empty
    }

    @Override
    protected void runImpl() {
        AccountTypeEnum accountType = this.getConnection().getAccount().getAccountType();
        int playerCount = this.getConnection().getAccount().getPlayers().size();
        this.getConnection().sendPacket(new SM_RESPONSE_CREATE_CHARACTER(playerCount < accountType.maxPlayerCount));
    }
}
