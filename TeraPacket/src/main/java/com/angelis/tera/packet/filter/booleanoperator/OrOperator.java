package com.angelis.tera.packet.filter.booleanoperator;

import java.util.List;

import com.angelis.tera.packet.filter.Condition;
import com.angelis.tera.packet.parser.DataStructure;


/**
 * 
 * @author Gilles Duboscq
 *
 */
public class OrOperator implements BooleanOperator
{

    public boolean execute(List<Condition> conditions, DataStructure dp)
    {
        for(Condition cond : conditions)
        {
            if(cond.evaluate(dp))
                return true;
        }
        return false;
    }
    
}