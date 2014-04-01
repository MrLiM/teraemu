package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_CANCEL_QUIT_GAME;
import com.angelis.tera.game.services.ThreadPoolService;
import com.angelis.tera.game.tasks.TaskTypeEnum;

public class CM_CANCEL_QUIT_GAME extends TeraClientPacket {

    public CM_CANCEL_QUIT_GAME(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        // Empty packet
    }

    @Override
    protected void runImpl() {
        this.getConnection().sendPacket(new SM_CANCEL_QUIT_GAME());
        ThreadPoolService.getInstance().cancelTask(this.getConnection().getActivePlayer(), TaskTypeEnum.PLAYER_QUIT);
    }
}
