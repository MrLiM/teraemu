package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.enums.ObjectFamilyEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.PlayerService;

public class CM_PLAYER_SELECT_CREATURE extends TeraClientPacket {

    public int creatureId;
    public ObjectFamilyEnum objectFamilly;
    
    public CM_PLAYER_SELECT_CREATURE(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.creatureId = readD();
        this.objectFamilly = ObjectFamilyEnum.fromValue(readB(4)); // todo
    }

    @Override
    protected void runImpl() {
        PlayerService.getInstance().onPlayerSelectCreature(this.getConnection().getActivePlayer(), this.creatureId, this.objectFamilly);
    }
}
