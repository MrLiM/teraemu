package com.angelis.tera.packet.crypt;

import com.angelis.tera.common.network.crypt.CryptSession;
import com.angelis.tera.packet.protocol.Protocol;
import com.angelis.tera.packet.protocol.protocoltree.PacketFamilly.PacketDirectionEnum;

public class TeraCrypter implements ProtocolCrypter {

    private CryptSession cryptSession;
    private byte[] clientKey1;
    private byte[] clientKey2;
    private byte[] serverKey1;
    private byte[] serverKey2;
    
    public boolean decrypt(byte[] raw, PacketDirectionEnum dir) {
        if (!this.cryptSession.isCryptEnabled()) {
            switch (dir) {
                case CLIENT:
                    if (clientKey1 == null) {
                        clientKey1 = raw;
                    } else if (clientKey2 == null) {
                        clientKey2 = raw;
                    }
                break;
                
                case SERVER:
                    if (raw.length == 128) {
                        if (serverKey1 == null) {
                            serverKey1 = raw;
                        } else if (serverKey2 == null) {
                            serverKey2 = raw;
                            this.cryptSession = new CryptSession(clientKey1, clientKey2, serverKey1, serverKey2);
                        }
                    }
                break;
            }
            return false;
        }
        
        this.cryptSession.decrypt(raw, raw.length);
        return true;
    }

    public void setProtocol(Protocol protocol) {
        
    }

}
