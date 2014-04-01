package com.angelis.tera.packet.parser.formattree;

import java.lang.ref.WeakReference;

import javolution.util.FastList;

import com.angelis.tera.packet.parser.DataStructure;
import com.angelis.tera.packet.parser.PartType;
import com.angelis.tera.packet.protocol.protocoltree.PacketFormat;


/**
 * 
 * @author Gilles Duboscq
 *
 */
public class Format
{
    private final PacketFormat _containingPacketFormat;
    private final PartContainer _mainBlock;
    //  uses WeakReferance to avoid leaking the DataPackets
    private final FastList<WeakReference<DataStructure>> _formatChangeListeners;
    
    public Format(PacketFormat container)
    {
        _formatChangeListeners = new FastList<WeakReference<DataStructure>>();
        _mainBlock = new PartContainer(PartType.block, true);
        _mainBlock.setContainingFormat(this);
        _containingPacketFormat = container;
    }
    
    public PartContainer getMainBlock()
    {
        return _mainBlock;
    }
    
    public void triggerFormatChanged()
    {
        for(WeakReference<DataStructure> ref :_formatChangeListeners)
        {
            DataStructure dp = ref.get();
            if(dp != null)
                dp.invalidateParsing();
        }
    }

    public void registerFormatChangeListener(DataStructure packet)
    {
        _formatChangeListeners.add(new WeakReference<DataStructure>(packet));
    }

    /**
     * @return the containingPacketFormat
     */
    public PacketFormat getContainingPacketFormat()
    {
        return _containingPacketFormat;
    }
}