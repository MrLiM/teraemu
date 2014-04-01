package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.account.enums.DisplayRangeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;

public class CM_OPTION_SET_VISIBILITY_DISTANCE extends TeraClientPacket {

    private DisplayRangeEnum displayRange;

    public CM_OPTION_SET_VISIBILITY_DISTANCE(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.displayRange = DisplayRangeEnum.fromValue(readD());
    }

    @Override
    protected void runImpl() {
        this.getConnection().getAccount().getOptions().setDisplayRange(this.displayRange);
    }
}
