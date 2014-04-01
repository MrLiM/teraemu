package com.angelis.tera.game.network.packet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.log4j.Logger;

import com.angelis.tera.common.network.crypt.CryptSession;
import com.angelis.tera.common.network.packet.AbstractServerPacket;
import com.angelis.tera.common.utils.PrintUtils;
import com.angelis.tera.game.models.HasUid;
import com.angelis.tera.game.models.enums.ObjectFamilyEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;

public abstract class TeraServerPacket extends AbstractServerPacket<TeraGameConnection> {

    /** LOGGER */
    private static Logger log = Logger.getLogger(TeraServerPacket.class.getName());
    
    private Short opcode;
    
    protected Integer bufferSavePosition;
    
    protected enum Size {
        H
    }
    
    public TeraServerPacket() {}
    
    @Override
    public void write(TeraGameConnection connection, ByteBuffer byteBuffer) {
        // Packet length reservation
        writePacketLength(byteBuffer, (short)0);
        byteBuffer.position(2);
        
        // Opcode
        writeOpcode(byteBuffer);
        
        // Implementation of this packet
        this.writeImpl(connection, byteBuffer);
        byteBuffer.flip();
        
        // Packet length
        short length = (short) byteBuffer.limit();
        writePacketLength(byteBuffer, length);
        
        if (this.showInDebug()) {
            log.info(PrintUtils.toHex(byteBuffer));
        }
        
        this.completeByteBuffer(connection, byteBuffer, length);
    }
    
    public void writePacketLength(ByteBuffer byteBuffer, short length) {
        byteBuffer.putShort(0, length);
    }
    
    public void writeOpcode(ByteBuffer byteBuffer) {
        writeH(byteBuffer, this.opcode);
    }
    
    protected final void writeUid(ByteBuffer byteBuffer, HasUid uid) {
        if (uid == null) {
            byteBuffer.putLong(0L);
            return;
        }

        byteBuffer.putInt(uid.getUid());
        byteBuffer.put(ObjectFamilyEnum.fromClass(uid.getClass()).value);
    }
    
    protected final void writeBufferPosition(ByteBuffer byteBuffer, int newPosition, Size size) {
        int position = byteBuffer.position();
        
        byteBuffer.position(newPosition);
        switch (size) {
            case H:
                writeH(byteBuffer, position);
            break;
        }
        byteBuffer.position(position);
    }
    
    public void completeByteBuffer(TeraGameConnection connection, ByteBuffer byteBuffer, short length) {
        // Reset packet
        ByteBuffer b = (ByteBuffer) byteBuffer.slice().limit(length);
        b.order(ByteOrder.LITTLE_ENDIAN);
        
        // Crypt
        CryptSession session = connection.getCryptSession(); 
        if (session.isCryptEnabled()) {
            session.encrypt(b);
        }
    }
    
    public final void setOpcode(Short opcode) {
        if (this.opcode == null) {
            this.opcode = opcode;
        }
    }
    
    @Override
    public boolean showInDebug() {
        return false;
    }
}
