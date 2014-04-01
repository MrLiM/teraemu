package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_CANCEL_QUIT_TO_CHARACTER_LIST;
import com.angelis.tera.game.services.ThreadPoolService;
import com.angelis.tera.game.tasks.TaskTypeEnum;

public class CM_CANCEL_QUIT_TO_CHARACTER_LIST extends TeraClientPacket {

    public CM_CANCEL_QUIT_TO_CHARACTER_LIST(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        // Empty packet
    }

    @Override
    protected void runImpl() {
        this.getConnection().sendPacket(new SM_CANCEL_QUIT_TO_CHARACTER_LIST());
        ThreadPoolService.getInstance().cancelTask(this.getConnection().getActivePlayer(), TaskTypeEnum.PLAYER_QUIT_TO_PLAYER_LIST);
    }

}
