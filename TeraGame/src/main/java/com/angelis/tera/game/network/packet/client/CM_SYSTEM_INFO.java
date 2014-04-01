package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_OPCODE_LESS_PACKET;
import com.angelis.tera.game.network.packet.server.SM_SYSTEM_INFO;

public class CM_SYSTEM_INFO extends TeraClientPacket {

    public CM_SYSTEM_INFO(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        readH(); // OsNameShift
        readH(); // CpuNameShift
        readH(); // GpuNameShift
    }

    @Override
    protected void runImpl() {
        this.getConnection().sendPacket(new SM_SYSTEM_INFO());
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("C0CC0100080008000000B2010000FFFFFF7F00000000"));
    }
}
