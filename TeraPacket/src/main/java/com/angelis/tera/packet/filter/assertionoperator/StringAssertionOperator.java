package com.angelis.tera.packet.filter.assertionoperator;


import com.angelis.tera.packet.filter.value.string.StringValue;
import com.angelis.tera.packet.parser.DataStructure;

/**
 * 
 * @author Gilles Duboscq
 *
 */
public interface StringAssertionOperator extends AssertionOperator
{
    public boolean execute(StringValue value1, StringValue value2, DataStructure dp);
}