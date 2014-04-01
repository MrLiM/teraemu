package com.angelis.tera.packet.filter;


import com.angelis.tera.packet.filter.assertionoperator.AssertionOperator;
import com.angelis.tera.packet.filter.assertionoperator.NumberAssertionOperator;
import com.angelis.tera.packet.filter.assertionoperator.StringAssertionOperator;
import com.angelis.tera.packet.filter.value.Value;
import com.angelis.tera.packet.filter.value.number.NumberValue;
import com.angelis.tera.packet.filter.value.string.StringValue;
import com.angelis.tera.packet.parser.DataStructure;

/**
 * 
 * @author Gilles Duboscq
 *
 */
public class AssertionExpression extends Expression
{
    private AssertionOperator _operator;
    private Value _value1;
    private Value _value2;
    
    public AssertionExpression(AssertionOperator op)
    {
        _operator = op;
    }
    
    @Override
    public boolean evaluate(DataStructure dp)
    {
        if(this.getValue1() == null || this.getValue2() == null)
            throw new IllegalStateException("Must set values before evaluation");
        if(this.getAssertionOperator() instanceof StringAssertionOperator)
        {
            return ((StringAssertionOperator) this.getAssertionOperator()).execute((StringValue)this.getValue1(), (StringValue)this.getValue2(), dp);
        }
        else if(this.getAssertionOperator() instanceof NumberAssertionOperator)
        {
            return ((NumberAssertionOperator) this.getAssertionOperator()).execute((NumberValue)this.getValue1(), (NumberValue)this.getValue2(), dp);
        }
        return false;
    }
    
    public AssertionOperator getAssertionOperator()
    {
        return _operator;
    }
    
    public Value getValue1()
    {
        return _value1;
    }
    
    public void setValue1(Value val)
    {
        if(getAssertionOperator() instanceof StringAssertionOperator && !(val instanceof StringValue))
            throw new IllegalArgumentException("This Expression is made to have String operands");
        else if (getAssertionOperator() instanceof NumberAssertionOperator && !(val instanceof NumberValue))
            throw new IllegalArgumentException("This Expression is made to have Number operands");
        _value1 = val;
    }
    
    public Value getValue2()
    {
        return _value2;
    }
    
    public void setValue2(Value val)
    {
        if(getAssertionOperator() instanceof StringAssertionOperator && !(val instanceof StringValue))
            throw new IllegalArgumentException("This Expression is made to have String operands");
        else if (getAssertionOperator() instanceof NumberAssertionOperator && !(val instanceof NumberValue))
            throw new IllegalArgumentException("This Expression is made to have Number operands");
        _value2 = val;
    }
}