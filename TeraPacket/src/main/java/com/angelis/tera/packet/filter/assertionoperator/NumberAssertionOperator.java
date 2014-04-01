package com.angelis.tera.packet.filter.assertionoperator;


import com.angelis.tera.packet.filter.value.number.NumberValue;
import com.angelis.tera.packet.parser.DataStructure;

/**
 * 
 * @author Gilles Duboscq
 *
 */
public interface NumberAssertionOperator extends AssertionOperator
{
    public boolean execute(NumberValue value1, NumberValue value2, DataStructure dp);
}