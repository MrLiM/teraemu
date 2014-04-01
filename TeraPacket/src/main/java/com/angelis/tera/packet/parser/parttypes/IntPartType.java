package com.angelis.tera.packet.parser.parttypes;


import com.angelis.tera.packet.parser.PartType;
import com.angelis.tera.packet.parser.datatree.DataTreeNodeContainer;
import com.angelis.tera.packet.parser.datatree.IntValuePart;
import com.angelis.tera.packet.parser.datatree.ValuePart;
import com.angelis.tera.packet.parser.formattree.Part;

/**
 * 
 * @author Gilles Duboscq
 *
 */
public class IntPartType extends PartType
{
    public enum intType
    {
        c,
        h,
        d
    }
    
    intType _type;

    public IntPartType(String name, intType type)
    {
        super(name);
        _type = type;
    }

    @Override
    public ValuePart getValuePart(DataTreeNodeContainer parent, Part part)
    {
        return new IntValuePart(parent, part, _type);
    }

    @Override
    public boolean isBlockType()
    {
        return false;
    }

    @Override
    public boolean isReadableType()
    {
        return true;
    }

    @Override
    public int getTypeByteNumber()
    {
        switch(_type)
        {
            case c:
                return 1;
            case h:
                return 2;
            case d:
                return 4;
        }
        return 0; //useless
    }
    
}