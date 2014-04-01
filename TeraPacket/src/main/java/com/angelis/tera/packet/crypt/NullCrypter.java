package com.angelis.tera.packet.crypt;

import com.angelis.tera.packet.protocol.Protocol;
import com.angelis.tera.packet.protocol.protocoltree.PacketFamilly.PacketDirectionEnum;

/**
 * 
 * @author Gilles Duboscq
 * 
 */
public class NullCrypter implements ProtocolCrypter {

    public boolean decrypt(byte[] raw, PacketDirectionEnum dir) {
        return true;
    }

    public void setProtocol(Protocol protocol) {

    }
}