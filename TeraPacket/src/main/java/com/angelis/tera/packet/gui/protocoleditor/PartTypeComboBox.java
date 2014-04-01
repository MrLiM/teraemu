package com.angelis.tera.packet.gui.protocoleditor;

import javax.swing.JComboBox;

import com.angelis.tera.packet.gui.IconComboBoxRenderer;

/**
 * 
 * @author Gilles Duboscq
 * 
 */
@SuppressWarnings("serial")
public class PartTypeComboBox extends JComboBox<Object> {
    public int offset;

    public PartTypeComboBox() {
        super(IconComboBoxRenderer.typesButBlocks);
    }

    public void setBounds(int x, int y, int w, int h) {
        System.out.println("reshaping, offset = " + offset);
        int newX = Math.max(x, offset);
        super.setBounds(newX, y, w - (newX - x), h);
    }
}