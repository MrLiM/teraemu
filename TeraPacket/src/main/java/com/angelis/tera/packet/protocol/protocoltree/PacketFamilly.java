package com.angelis.tera.packet.protocol.protocoltree;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javolution.util.FastMap;

import com.angelis.tera.packet.MainPacket;
import com.angelis.tera.packet.captor.Util;
import com.angelis.tera.packet.parser.PartType;
import com.angelis.tera.packet.parser.parttypes.IntPartType;

/**
 * This class is a container for {@link ProtocolNode ProtocolNodes} and also is
 * a ProtocolNode thus allowing an infinite depth tree structure for the
 * protocol definition.
 * 
 * @author Gilles Duboscq
 * 
 */
public class PacketFamilly extends ProtocolNode {
    private Map<Integer, ProtocolNode> _nodes;
    private PartType _switchType;
    private PacketDirectionEnum _direction;
    private int _id;
    private String _name;

    public enum PacketDirectionEnum {
        CLIENT, SERVER
    }

    public PacketFamilly() {
        this((String) null);
    }

    public PacketFamilly(String name) {
        super();
        _name = name;
        _nodes = new FastMap<Integer, ProtocolNode>();
    }

    public PacketFamilly(int id) {
        this(id, null);
    }

    public PacketFamilly(int id, String name) {
        super(id);
        _id = id;
        _name = name;
        _nodes = new FastMap<Integer, ProtocolNode>();
    }

    public void addNode(ProtocolNode node) {
        ProtocolNode old;
        if ((old = _nodes.put(node.getID(), node)) != null) {
            MainPacket.getUserInterface().log("WARNING: More then one packet in familly '" + _name + "' with id 0x" + Integer.toHexString(node.getID()) + " (" + old + " && " + node + ")");
        }
    }

    /**
     * @return Returns the switchType.
     */
    public PartType getSwitchType() {
        return _switchType;
    }

    /**
     * @param type
     *            The switchType to set.
     */
    public void setSwitchType(PartType type) {
        if (!(type instanceof IntPartType)) {
            MainPacket.getUserInterface().log("Warrning!: Bad switchType For a PacketFamilly!");
            return;
        }
        _switchType = type;
    }

    /**
     * @return Returns the _direction.
     */
    public PacketDirectionEnum getDirection() {
        return _direction;
    }

    /**
     * @param _direction
     *            The _direction to set.
     */
    public void setDirection(PacketDirectionEnum direction) {
        _direction = direction;
    }

    /**
     * @return Returns the _nodes.
     */
    public Map<Integer, ProtocolNode> getNodes() {
        return _nodes;
    }

    public void setName(String name) {
        _name = name;
    }

    public String toString() {
        return (_name != null ? _name : Util.zeropad(Integer.toHexString(_id), 2).toUpperCase()) + " familly";
    }

    public String getName() {
        return _name;
    }

    /**
     * Used to remove a node in the tree (can be in sub-packetfamillies) Node
     * can be inexistant.
     * 
     * @param node
     */
    public void remove(ProtocolNode node) {
        if (node == null)
            return;
        Iterator<Entry<Integer, ProtocolNode>> i = _nodes.entrySet().iterator();
        while (i.hasNext()) {
            ProtocolNode testNode = i.next().getValue();
            if (node == testNode) {
                i.remove();
            }
            else if (testNode instanceof PacketFamilly) {
                ((PacketFamilly) testNode).remove(node);
            }
        }
    }

}