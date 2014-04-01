package com.angelis.tera.game.network.packet.server;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_SYSTEM_MESSAGE extends TeraServerPacket {

    private final String[] args;
    
    public SM_SYSTEM_MESSAGE(final String[] args) {
        this.args = args;
    }
    
    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        
        for (int i = 0 ; i < args.length ; i++) {
            try {
                writeH(byteBuffer, i == 0 ? 6 : 11);
                String[] s = args[i].split("");
                for (int j = 1 ; j < s.length ; j++) {
                    writeB(byteBuffer, s[j].getBytes("UTF-8"));
                    writeC(byteBuffer, 0);
                }
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        writeH(byteBuffer, 0);
        //0600 4000 3100 3100 3100 0000
    }

}
