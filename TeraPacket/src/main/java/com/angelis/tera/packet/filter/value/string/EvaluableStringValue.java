package com.angelis.tera.packet.filter.value.string;


import com.angelis.tera.packet.filter.value.Value;
import com.angelis.tera.packet.parser.DataStructure;

/**
 * 
 * @author Gilles Duboscq
 *
 */
public class EvaluableStringValue extends StringValue
{
    private String _accessStr;
    public EvaluableStringValue(String str)
    {
        _accessStr = str;
    }

    @Override
    public String getStringValue(DataStructure dp)
    {
        Object obj = Value.getObjectFromAccessString(_accessStr);
        if(!(obj instanceof String))
            throw new IllegalStateException("Malformed filter, the expression doesnt return a string for an EvaluableStringValue.");
        return (String) obj;
    }
    
}