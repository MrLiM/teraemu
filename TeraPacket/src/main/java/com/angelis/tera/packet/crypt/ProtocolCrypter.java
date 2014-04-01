package com.angelis.tera.packet.crypt;


import com.angelis.tera.packet.protocol.Protocol;
import com.angelis.tera.packet.protocol.protocoltree.PacketFamilly.PacketDirectionEnum;

/**
 * This interface is used to interface with all the different protocol encryptions
 * @author Gilles Duboscq
 *
 */
public interface ProtocolCrypter
{
	/**
	 * this decrypts the packet, the data provided must be only one full packet
	 * @param raw
	 * @return
	 */
	public boolean decrypt(byte[] raw, PacketDirectionEnum dir);
    
    public void setProtocol(Protocol protocol);
}