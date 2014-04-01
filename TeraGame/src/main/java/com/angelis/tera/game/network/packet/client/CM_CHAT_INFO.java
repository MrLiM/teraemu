package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_CHAT_INFO;
import com.angelis.tera.game.services.PlayerService;

public class CM_CHAT_INFO extends TeraClientPacket {

    private int type;
    private int version;
    private String name;

    public CM_CHAT_INFO(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readH(); //shift
        this.type = readD();
        this.version = readD();
        this.name = readS();
    }

    @Override
    protected void runImpl() {
        Player player = PlayerService.getInstance().findPlayerByName(name);
        if (player == null) {
            return;
        }
        
        this.getConnection().sendPacket(new SM_CHAT_INFO(player, this.version, this.type));
    }
}
