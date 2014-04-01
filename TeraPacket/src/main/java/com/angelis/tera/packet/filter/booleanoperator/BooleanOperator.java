package com.angelis.tera.packet.filter.booleanoperator;

import java.util.List;

import com.angelis.tera.packet.filter.Condition;
import com.angelis.tera.packet.parser.DataStructure;


/**
 * 
 * @author Gilles Duboscq
 *
 */
public interface BooleanOperator
{
    public boolean execute(List<Condition> conditions, DataStructure dp);
}