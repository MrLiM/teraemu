package com.angelis.tera.game.network.packet;

import java.util.Map;
import java.util.Map.Entry;

import javolution.util.FastMap;

import org.apache.log4j.Logger;

import com.angelis.tera.common.network.packet.AbstractServerPacket;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.server.SM_ATTACK;
import com.angelis.tera.game.network.packet.server.SM_CANCEL_QUIT_GAME;
import com.angelis.tera.game.network.packet.server.SM_CANCEL_QUIT_TO_CHARACTER_LIST;
import com.angelis.tera.game.network.packet.server.SM_CHARACTER_CREATE;
import com.angelis.tera.game.network.packet.server.SM_CHARACTER_DELETE;
import com.angelis.tera.game.network.packet.server.SM_CHARACTER_RESTORE;
import com.angelis.tera.game.network.packet.server.SM_CHAT_INFO;
import com.angelis.tera.game.network.packet.server.SM_CHECK_VERSION;
import com.angelis.tera.game.network.packet.server.SM_CREATURE_INFO;
import com.angelis.tera.game.network.packet.server.SM_CREATURE_MOVE;
import com.angelis.tera.game.network.packet.server.SM_CREATURE_REMOVE;
import com.angelis.tera.game.network.packet.server.SM_ENTER_WORLD;
import com.angelis.tera.game.network.packet.server.SM_GATHER_INFO;
import com.angelis.tera.game.network.packet.server.SM_GATHER_REMOVE;
import com.angelis.tera.game.network.packet.server.SM_GROUP_CREATE;
import com.angelis.tera.game.network.packet.server.SM_HP_UPDATE;
import com.angelis.tera.game.network.packet.server.SM_MAP;
import com.angelis.tera.game.network.packet.server.SM_OPCODE_LESS_PACKET;
import com.angelis.tera.game.network.packet.server.SM_OPTION_SHOW_MASK;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_BIND;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_CHAT;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_DONJON_STATS_PVP;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_EMOTE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_ENTER_CHANNEL;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_FRIEND_ADD_SUCCESS;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_FRIEND_LIST;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_FRIEND_REMOVE_SUCCESS;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_INFO;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_INIT;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_MOVE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_REMOVE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_REQUEST_ALLOWED;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_REQUEST_WAIT_WINDOW;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_REQUEST_WAIT_WINDOW_HIDE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_SELECT_CREATURE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_SET_TITLE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_STATS;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_TELEPORT;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_WHISPER;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_ZONE_CHANGE;
import com.angelis.tera.game.network.packet.server.SM_PONG;
import com.angelis.tera.game.network.packet.server.SM_QUEST;
import com.angelis.tera.game.network.packet.server.SM_QUIT;
import com.angelis.tera.game.network.packet.server.SM_QUIT_WINDOW_SHOW;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_ACCOUNT_OBJECT;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_CHARACTER_LIST;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_CHARACTER_NAME_CHECK;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_CHARACTER_NAME_CHECK_USED;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_CREATE_CHARACTER;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_PLAYER_UNLOCK;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_UNIQUE_OBJECT;
import com.angelis.tera.game.network.packet.server.SM_RETURN_TO_CHARACTER_LIST_WINDOW_SHOW;
import com.angelis.tera.game.network.packet.server.SM_RETURN_TO_PLAYER_LIST;
import com.angelis.tera.game.network.packet.server.SM_SHOP_WINDOW_OPEN;
import com.angelis.tera.game.network.packet.server.SM_STOP_ATTACK;
import com.angelis.tera.game.network.packet.server.SM_SYSTEM_INFO;
import com.angelis.tera.game.network.packet.server.SM_SYSTEM_MESSAGE;
import com.angelis.tera.game.network.packet.server.SM_WELCOME_MESSAGE;
import com.angelis.tera.game.network.packet.server.key.KeyServerPacket;

public class ServerPacketHandler {
    
    /** LOGGER */
    private static Logger log = Logger.getLogger(ClientPacketHandler.class.getName());
    
    private static Map<Short, Class<? extends AbstractServerPacket<TeraGameConnection>>> serverPackets = new FastMap<Short, Class<? extends AbstractServerPacket<TeraGameConnection>>>();
    
    public static final void init() {
        serverPackets.clear();
        
        // AUTH
        addPacket((short) 0x4DBD, SM_CHECK_VERSION.class); // OK
        addPacket((short) 0xB91F, SM_SYSTEM_INFO.class); // OK
        
        
        // RESPONSE
        addPacket((short) 0xB3BD, SM_RESPONSE_CHARACTER_LIST.class); // OK
        addPacket((short) 0xCF15, SM_RESPONSE_CREATE_CHARACTER.class); // OK
        addPacket((short) 0xCE32, SM_RESPONSE_CHARACTER_NAME_CHECK.class); // OK
        addPacket((short) 0x72C0, SM_RESPONSE_CHARACTER_NAME_CHECK_USED.class); // OK
        
        
        // CHARACTER
        addPacket((short) 0xDFCB, SM_CHARACTER_CREATE.class); // OK
        addPacket((short) 0xA426, SM_CHARACTER_DELETE.class); // OK
        addPacket((short) 0x843F, SM_CHARACTER_RESTORE.class); // OK


        // ENTER WORLD
        addPacket((short) 0x7F5F, SM_ENTER_WORLD.class); // OK


        // OPTIONS
        addPacket((short) 0xEC8C, SM_OPTION_SHOW_MASK.class); // OK
        
        
        // PLAYER
        addPacket((short) 0xE48C, SM_PLAYER_INIT.class); // OK
        addPacket((short) 0xA84B, SM_PLAYER_CHAT.class); // OK
        addPacket((short) 0xE8D8, SM_PLAYER_WHISPER.class); // OK
        addPacket((short) 0xA0BD, SM_CHAT_INFO.class); // OK
        addPacket((short) 0xF5AD, SM_PLAYER_FRIEND_LIST.class); 
        addPacket((short) 0xAB46, SM_ATTACK.class); // OK
        addPacket((short) 0xBD95, SM_STOP_ATTACK.class); // OK
        addPacket((short) 0xF32B, SM_PLAYER_ENTER_CHANNEL.class); 
        addPacket((short) 0xD576, SM_PLAYER_TELEPORT.class); // OK
        addPacket((short) 0xB8A1, SM_PLAYER_BIND.class); // OK
        addPacket((short) 0xD4CF, SM_PLAYER_STATS.class); // OK
        addPacket((short) 0x99C6, SM_HP_UPDATE.class); // OK
        addPacket((short) 0x8BDD, SM_PLAYER_INFO.class); // OK
        addPacket((short) 0xE04F, SM_PLAYER_MOVE.class); // OK
        addPacket((short) 0x570D, SM_PLAYER_ZONE_CHANGE.class); // OK
        addPacket((short) 0x7C20, SM_PLAYER_REMOVE.class); // OK
        addPacket((short) 0xFBF1, SM_PLAYER_SELECT_CREATURE.class); // OK
        
        
        // GROUP
        addPacket((short) 0xCF40, SM_GROUP_CREATE.class); // OK

        
        // PROFIL
        addPacket((short) 0xFFBC, SM_PLAYER_SET_TITLE.class); // OK
        addPacket((short) 0xA65B, SM_PLAYER_DONJON_STATS_PVP.class); // OK
        
        
        
        // QUEST
        addPacket((short) 0x8942, SM_QUEST.class); // OK
        addPacket((short) 0xE850, SM_PONG.class); // OK


        // INVENTORY
        //addPacket((short) 0xC391, SM_INVENTORY.class);


        // MAP
        addPacket((short) 0x9860, SM_MAP.class); // OK


        // ACTIVITIES
        addPacket((short) 0xC391, SM_PLAYER_EMOTE.class); // OK


        // SOCIAL
        addPacket((short) 0xFAB3, SM_PLAYER_FRIEND_LIST.class); // OK
        addPacket((short) 0x688B, SM_PLAYER_FRIEND_ADD_SUCCESS.class); // OK
        addPacket((short) 0xBF72, SM_PLAYER_FRIEND_REMOVE_SUCCESS.class); // OK
        // addPacket((short) 0xA84B, SM_PLAYER_SOCIAL.class);
        
        
        // SHOP
        addPacket((short) 0xA3F4, SM_SHOP_WINDOW_OPEN.class); // OK
        addPacket((short) 0xFB8B, SM_RESPONSE_UNIQUE_OBJECT.class); // OK
        addPacket((short) 0xF28A, SM_RESPONSE_ACCOUNT_OBJECT.class); // OK
        
        
        // SYSTEM
        addPacket((short) 0x9FD4, SM_WELCOME_MESSAGE.class); // OK
        addPacket((short) 0xC566, SM_RESPONSE_PLAYER_UNLOCK.class); // OK
        addPacket((short) 0xC578, SM_RETURN_TO_CHARACTER_LIST_WINDOW_SHOW.class); // OK
        addPacket((short) 0xC555, SM_RETURN_TO_PLAYER_LIST.class); // OK
        addPacket((short) 0xA2E1, SM_CANCEL_QUIT_TO_CHARACTER_LIST.class); // OK
        addPacket((short) 0x61A8, SM_QUIT_WINDOW_SHOW.class); // OK
        addPacket((short) 0x863B, SM_QUIT.class); // OK
        addPacket((short) 0xE989, SM_CANCEL_QUIT_GAME.class); // OK
        
        addPacket((short) 0xEDDC, SM_PLAYER_REQUEST_ALLOWED.class); // OK
        addPacket((short) 0xA944, SM_PLAYER_REQUEST_WAIT_WINDOW.class); // OK
        addPacket((short) 0xA31E, SM_PLAYER_REQUEST_WAIT_WINDOW_HIDE.class); // OK
        
        
        // OTHER
        addPacket((short) 0xADA3, SM_SYSTEM_MESSAGE.class); // OK
        
        addPacket((short) 0xC84B, SM_CREATURE_INFO.class); // OK
        addPacket((short) 0xC704, SM_CREATURE_MOVE.class); // OK
        addPacket((short) 0x541F, SM_CREATURE_REMOVE.class); // OK
        addPacket((short) 0x9F83, SM_GATHER_INFO.class); // OK
        addPacket((short) 0xC8EC, SM_GATHER_REMOVE.class); // OK
        
        // CUSTOM
        addPacket((short) 0xFFFE, SM_OPCODE_LESS_PACKET.class);
        addPacket((short) 0xFFFF, KeyServerPacket.class);
    }
    
    @SuppressWarnings("rawtypes")
    public static Short getServerPacketId(Class<? extends AbstractServerPacket> packetClass) {
        for (Entry<Short, Class<? extends AbstractServerPacket<TeraGameConnection>>> entry : serverPackets.entrySet()) {
            if (entry.getValue() == packetClass) {
                return entry.getKey();
            }
        }
        
        log.error("Can't find packet for class "+packetClass.getName());
        return null;
    }
    
    private static void addPacket(Short id, Class<? extends AbstractServerPacket<TeraGameConnection>> packetClass) {
        if (serverPackets.containsKey(id)) {
            log.error("Server packet with id "+String.format("0x%02X: ", id)+ " already exists");
            return;
        }
        serverPackets.put(id, packetClass);
    }
}
