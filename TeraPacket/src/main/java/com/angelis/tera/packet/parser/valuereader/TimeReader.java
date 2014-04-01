/*
 * This file is part of aion-unique <aion-unique.com>.
 *
 *     Aion-unique is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Aion-unique is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.angelis.tera.packet.parser.valuereader;

import java.sql.Timestamp;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.angelis.tera.packet.parser.datatree.IntValuePart;
import com.angelis.tera.packet.parser.datatree.ValuePart;

public class TimeReader implements Reader {
    
    public boolean loadReaderFromXML(Node n) {
        return true;
    }

    public boolean saveReaderToXML(Element element, Document doc) {
        return true;
    }

    public JComponent readToComponent(ValuePart part) {
        return new JLabel(read(part));
    }

    public String read(ValuePart part) {
        if (!(part instanceof IntValuePart))
            return "";

        long result = ((IntValuePart) part).getIntValue();

        Timestamp tt = new Timestamp(result);

        return tt.toString();
    }

    public boolean supportsEnum() {
        return false;
    }

    public <T extends Enum<T>> T getEnum(ValuePart part) {
        return null;
    }
}
