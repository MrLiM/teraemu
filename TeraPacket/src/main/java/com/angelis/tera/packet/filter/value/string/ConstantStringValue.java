package com.angelis.tera.packet.filter.value.string;

import com.angelis.tera.packet.parser.DataStructure;

/**
 * 
 * @author Gilles Duboscq
 *
 */
public class ConstantStringValue extends StringValue
{
    public String _value;
    
    public ConstantStringValue(String val)
    {
        _value = val;
    }

    @Override
    public String getStringValue(DataStructure dp)
    {
        return _value;
    }
    
}