package com.angelis.tera.packet.filter.value.number;

import com.angelis.tera.packet.parser.DataStructure;

/**
 * 
 * @author Gilles Duboscq
 *
 */
public class ConstantFloatValue extends FloatNumberValue
{
    private double _value;
    
    public ConstantFloatValue(double val)
    {
        _value = val;
    }

    @Override
    public double getFloatValue(DataStructure dp)
    {
        return _value;
    }
    
}