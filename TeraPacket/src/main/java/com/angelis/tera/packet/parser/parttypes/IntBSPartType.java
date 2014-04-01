package com.angelis.tera.packet.parser.parttypes;

import com.angelis.tera.packet.parser.datatree.DataTreeNodeContainer;
import com.angelis.tera.packet.parser.datatree.IntBCValuePart;
import com.angelis.tera.packet.parser.datatree.ValuePart;
import com.angelis.tera.packet.parser.formattree.Part;

/**
 * @author -Nemesiss-
 *
 */
public class IntBSPartType extends IntPartType
{
	public IntBSPartType(String name, intType type)
	{
		super(name, type);
	}

    @Override
    public ValuePart getValuePart(DataTreeNodeContainer parent, Part part)
    {
        return new IntBCValuePart(parent, part, _type);
    }

}
