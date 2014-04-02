package com.angelis.tera.game.services;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javolution.util.FastList;
import javolution.util.FastSet;

import org.apache.log4j.Logger;

import com.angelis.tera.common.lang.IsObserver;
import com.angelis.tera.game.models.Creature;
import com.angelis.tera.game.models.CreatureEventTypeEnum;
import com.angelis.tera.game.models.TeraObject;
import com.angelis.tera.game.models.account.enums.DisplayRangeEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;

public class WorldService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(WorldService.class.getName());
    
    /** INSTANCE */
    private static WorldService instance;
    
    private Set<Player> onlinePlayers = new FastSet<Player>();
    
    public void onInit() {
        log.info("WorldService started");
    }

    public void onDestroy() {}
    
    public void onPlayerConnect(Player player) {
        this.onlinePlayers.add(player);
    }
    
    public void onPlayerDisconnect(Player player) {
        this.onlinePlayers.remove(player);
    }
    
    public void onPlayerMove(Player player) {   
        
    }
    
    public Player getOnlinePlayerWithName(String name) {
        for (Player player : this.onlinePlayers) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) {
                return player;
            }
        }
        
        return null;
    }

    public Set<TeraGameConnection> getAllOnlineConnections() {
        Set<TeraGameConnection> allConnections = new FastSet<TeraGameConnection>();
        for (Player player : this.onlinePlayers) {
            allConnections.add(player.getConnection());
        }
        return allConnections;
    }
    
    public Set<Player> getAllOnlinePlayers() {
        return this.onlinePlayers;
    }
    
    public List<Player> getPlayersByMap(int mapId) {
        List<Player> playersInMap = new FastList<>();
        
        for (Player player : this.onlinePlayers) {
            if (player.getWorldPosition().getMapId() == mapId) {
                playersInMap.add(player);
            }
        }
        
        return playersInMap;
    }
    
    public List<Player> getPlayersByArea(int areaId) {
        List<Player> playersInArea = new FastList<>();
        
        for (Player player : this.onlinePlayers) {
            if (this.getAreaByMapId(player.getWorldPosition().getMapId()) == areaId) {
                playersInArea.add(player);
            }
        }
        
        return playersInArea;
    }
    
    public int getAreaByMapId(int mapId) {
        // TODO
        return 0;
    }

    /** SINGLETON */
    public static WorldService getInstance() {
        if (instance == null) {
            instance = new WorldService();
        }
        
        return instance;
    }
}
