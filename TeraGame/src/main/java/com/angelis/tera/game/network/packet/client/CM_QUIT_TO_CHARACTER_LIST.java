package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.config.PlayerConfig;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_RETURN_TO_CHARACTER_LIST_WINDOW_SHOW;
import com.angelis.tera.game.services.ThreadPoolService;
import com.angelis.tera.game.tasks.player.PlayerQuitToPlayerListTask;

public class CM_QUIT_TO_CHARACTER_LIST extends TeraClientPacket {

    public CM_QUIT_TO_CHARACTER_LIST(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        // Empty packet
    }

    @Override
    protected void runImpl() {
        int delay = PlayerConfig.PLAYER_QUIT_TIMEOUT;
        if (this.getConnection().getAccount().getAccess() > 0) {
            delay = 0;
        }
        
        if (delay != 0) {
            this.getConnection().sendPacket(new SM_RETURN_TO_CHARACTER_LIST_WINDOW_SHOW(delay));
        }
        
        ThreadPoolService.getInstance().addTask(new PlayerQuitToPlayerListTask(this.getConnection().getActivePlayer(), delay == 0 ? 0 : delay+1));
    }
}
