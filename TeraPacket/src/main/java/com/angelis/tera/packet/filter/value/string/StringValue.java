package com.angelis.tera.packet.filter.value.string;


import com.angelis.tera.packet.filter.value.Value;
import com.angelis.tera.packet.parser.DataStructure;

/**
 * 
 * @author Gilles Duboscq
 *
 */
public abstract class StringValue extends Value
{
    public abstract String getStringValue(DataStructure dp);
}