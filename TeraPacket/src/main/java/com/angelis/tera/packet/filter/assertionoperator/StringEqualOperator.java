package com.angelis.tera.packet.filter.assertionoperator;


import com.angelis.tera.packet.filter.value.string.StringValue;
import com.angelis.tera.packet.parser.DataStructure;

/**
 * 
 * @author Gilles Duboscq
 *
 */
public class StringEqualOperator implements StringAssertionOperator
{

    public boolean execute(StringValue value1, StringValue value2, DataStructure dp)
    {
        if(value1.getStringValue(dp) == null)
            return (value2.getStringValue(dp) == null);
        return value1.getStringValue(dp).equals(value2.getStringValue(dp));
    }
}