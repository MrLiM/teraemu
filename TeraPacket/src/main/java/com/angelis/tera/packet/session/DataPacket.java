package com.angelis.tera.packet.session;

import java.nio.BufferUnderflowException;

import com.angelis.tera.packet.MainPacket;
import com.angelis.tera.packet.parser.DataStructure;
import com.angelis.tera.packet.protocol.PacketId;
import com.angelis.tera.packet.protocol.Protocol;
import com.angelis.tera.packet.protocol.protocoltree.PacketFamilly.PacketDirectionEnum;
import com.angelis.tera.packet.protocol.protocoltree.PacketFormat;


/**
 * 
 * @author Gilles Duboscq
 *
 */
public class DataPacket extends DataStructure
{
    private PacketDirectionEnum _direction;
    private Protocol _protocol;
    private long _timeStamp;
    private PacketFormat _packetFormat;
    private PacketId _packetID;
    private byte[] _IdData;
    private int _size;
    
    public DataPacket(byte[] data, PacketDirectionEnum dir, long timeStamp, Protocol proto)
    {
        this(data,dir,timeStamp,proto,true);
    }
    
    public DataPacket(byte[] data, PacketDirectionEnum direction, long timeStamp, Protocol protocol, boolean parse)
    {
        super(data,null);
        _direction = direction;
        _protocol = protocol;
        _timeStamp = timeStamp;
        _packetID = new PacketId();
        _packetFormat = _protocol.getFormat(this,_packetID);
        if (_packetFormat == null)
        {
            this.getByteBuffer().rewind();
            _packetID = null;
        }
        _size = data.length;
        _IdData = new byte[this.getByteBuffer().position()];
        System.arraycopy(data, 0, _IdData, 0, this.getByteBuffer().position());
        this.getByteBuffer().compact();
        this.getByteBuffer().flip();
        if (_packetFormat != null)
        {
            this.setFormat(_packetFormat.getDataFormat());
        }
        if (parse)
        {
            try
            {
                this.parse();
                if (this.getProtocol() != null && this.getProtocol().isStrictLength() && this.getUnparsedData().length > 0)
                {
                    _warning = "Incomplete Format";
                }
                else if (this.getFormat() == null)
                {
                    _warning = "Missing Format";
                }
            }
            catch (BufferUnderflowException e)
            {
                _error = "Insuficient data for the specified format";
                if(MainPacket.VERBOSITY.ordinal() >= MainPacket.VerboseLevel.VERBOSE.ordinal())
                {
                    if(this.getFormat() != null)
                        System.out.println(this.getFormat().toString());
                    dumpParts();
                }
                MainPacket.getUserInterface().log("ERROR: Parsing packet ("+this.getName()+"), insuficient data for the specified format. Please verify the format."); 
            }
        }
    }
    
    public Protocol getProtocol()
    {
        return _protocol;
    }
    
    public boolean fromServer()
    {
        return (_direction == PacketDirectionEnum.SERVER);
    }
    
    public long getTimeStamp()
    {
        return _timeStamp;
    }

    public PacketDirectionEnum getDirection()
    {
        return _direction;
    }
    
    public PacketFormat getPacketFormat()
    {
        return _packetFormat;
    }
    
    public String getName()
    {
        if (this.getPacketFormat() == null)
        {
            return null;
        }
        return this.getPacketFormat().getName();
    }
    
    public PacketId getPacketId()
    {
        return _packetID;
    }
    
    public int getRawSize()
    {
        return _size;
    }
    
    public byte[] getIdData()
    {
        return _IdData;
    }
}