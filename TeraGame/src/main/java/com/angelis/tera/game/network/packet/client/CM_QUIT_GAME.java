package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.config.PlayerConfig;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_QUIT_WINDOW_SHOW;
import com.angelis.tera.game.services.ThreadPoolService;
import com.angelis.tera.game.tasks.player.PlayerQuitTask;

/**
 * @author Angelis
 *
 * This packet is sent by client when you click on "Quit game" button.
 */
public class CM_QUIT_GAME extends TeraClientPacket {

    public CM_QUIT_GAME(ByteBuffer byteBuffer, TeraGameConnection connection) {
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
            this.getConnection().sendPacket(new SM_QUIT_WINDOW_SHOW(delay));
        }
        
        ThreadPoolService.getInstance().addTask(new PlayerQuitTask(this.getConnection().getActivePlayer(), delay == 0 ? 0 : delay+1));
    }
}
