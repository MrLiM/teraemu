package com.angelis.tera.game.services;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javolution.util.FastList;

import org.apache.log4j.Logger;

import com.angelis.tera.common.lang.IsObserver;
import com.angelis.tera.game.models.Creature;
import com.angelis.tera.game.models.CreatureEventTypeEnum;
import com.angelis.tera.game.models.TeraObject;
import com.angelis.tera.game.models.account.enums.DisplayRangeEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class VisibleService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(VisibleService.class.getName());

    /** INSTANCE */
    private static VisibleService instance;
    
    @Override
    public void onInit() {
        log.info("VisibleService started");
    }

    @Override
    public void onDestroy() {
        log.info("VisibleService stopped");
    }
    
    public void onPlayerDisconnect(Player player) {
        this.clearPlayerObservables(player);
    }
    
    public void onPlayerMove(Player player) {   
        this.updatePlayerObservers(player);
        this.updatePlayerObservables(player);
    }

    public void updatePlayerObservers(Player player) {
        List<Player> playerObservers = this.computePlayerObserversForPlayer(player);
        List<? extends TeraObject> teraObjectObservers = this.computeCreatureObserversForPlayer(player);
        
        Set<Player> onlinePlayers = WorldService.getInstance().getAllOnlinePlayers();
        
        // Delete old player observers
        Iterator<IsObserver<CreatureEventTypeEnum>> iterator = player.getObservers().iterator();
        while (iterator.hasNext()) {
            IsObserver<CreatureEventTypeEnum> observer = iterator.next();
            if (!onlinePlayers.contains(player) || (playerObservers.indexOf(observer) == -1 && teraObjectObservers.indexOf(observer) == -1)) {
                iterator.remove();
                observer.onObservableUpdate(CreatureEventTypeEnum.DISAPPEAR, player);
            }
        }
        
        // Add new players observers
        for (Player observer : playerObservers) {
            if (!player.getObservers().contains(observer)) {
                player.getObservers().add(observer);
                observer.onObservableUpdate(CreatureEventTypeEnum.APPEAR, player);
            }
        }
    }

    public void updatePlayerObservables(Player player) {
        List<Player> playerObservables = this.computePlayerObservablesForPlayer(player);
        List<? extends TeraObject> teraObjectObservables = this.computeTeraObjectObservablesForPlayer(player);
        
        List<Player> playerObservers = this.getPlayerObserversForPlayer(player);
        List<TeraObject> teraObjectObservers = this.getTeraObjectObserversForPlayer(player);
        
        for (Player observable : playerObservables) {
            if (!observable.getObservers().contains(player)) {
                observable.getObservers().add(player);
                player.onObservableUpdate(CreatureEventTypeEnum.APPEAR, observable);
            }
        }
        
        for (TeraObject observable : teraObjectObservables) {
            if (!observable.getObservers().contains(player)) {
                observable.getObservers().add(player);
                player.onObservableUpdate(CreatureEventTypeEnum.APPEAR, observable);
            }
        }
        
        Set<Player> onlinePlayers = WorldService.getInstance().getAllOnlinePlayers();
        for (Player observer : playerObservers) {
            if (!onlinePlayers.contains(player) || playerObservables.indexOf(observer) == -1) {
                observer.removeObserver(player);
                player.onObservableUpdate(CreatureEventTypeEnum.DISAPPEAR, observer);
            }
        }
        for (TeraObject observer : teraObjectObservers) {
            if (teraObjectObservables.indexOf(observer) == -1) {
                observer.removeObserver(player);
                player.onObservableUpdate(CreatureEventTypeEnum.DISAPPEAR, observer);
            }
        }
    }
    
    public void clearPlayerObservables(Player player) {
        List<Player> playerObservers = this.getPlayerObserversForPlayer(player);
        List<TeraObject> creatureObservers = this.getTeraObjectObserversForPlayer(player);
        
        for (Player observer : playerObservers) {
            observer.removeObserver(player);
            player.onObservableUpdate(CreatureEventTypeEnum.DISAPPEAR, observer);
            observer.onObservableUpdate(CreatureEventTypeEnum.DISAPPEAR, player);
        }
        for (TeraObject observer : creatureObservers) {
            observer.removeObserver(player);
            player.onObservableUpdate(CreatureEventTypeEnum.DISAPPEAR, observer);
        }
    }
    
    public List<Player> computePlayerObserversForPlayer(Player player) {
        List<Player> playersObservers = WorldService.getInstance().getPlayersByMap(player.getWorldPosition().getMapId());
        Iterator<Player> iterator = playersObservers.iterator();
        while (iterator.hasNext()) {
            Player observer = iterator.next();
            if (player.getWorldPosition().distanceTo(observer.getWorldPosition()) > observer.getAccount().getOptions().getDisplayRange().value || player.equals(observer)) {
                iterator.remove();
            }
        }
        
        return playersObservers;
    }
    private List<? extends TeraObject> computeCreatureObserversForPlayer(Player player) {
        List<TeraObject> teraObjectObservers = new FastList<>();
        teraObjectObservers.addAll(SpawnService.getInstance().getCreaturesByMapId(player.getWorldPosition().getMapId()));
        teraObjectObservers.addAll(SpawnService.getInstance().getGathersByMapId(player.getWorldPosition().getMapId()));
        
        Iterator<TeraObject> iterator = teraObjectObservers.iterator();
        while (iterator.hasNext()) {
            TeraObject observer = iterator.next();
            if (player.getWorldPosition().distanceTo(observer.getWorldPosition()) > DisplayRangeEnum.MAX.value || player.equals(observer)) {
                iterator.remove();
            }
        }
        
        return teraObjectObservers;
    }
    
    public List<Player> computePlayerObservablesForPlayer(Player player) {
        List<Player> playersInMap = WorldService.getInstance().getPlayersByMap(player.getWorldPosition().getMapId());
        Iterator<Player> iterator = playersInMap.iterator();
        while (iterator.hasNext()) {
            Player observer = iterator.next();
            if (player.getWorldPosition().distanceTo(observer.getWorldPosition()) > player.getAccount().getOptions().getDisplayRange().value+1000 || player.equals(observer)) {
                iterator.remove();
            }
        }
        
        return playersInMap;
    }
    
    public List<? extends TeraObject> computeTeraObjectObservablesForPlayer(Player player) {
        List<TeraObject> teraObjectInMap = new FastList<>();
        teraObjectInMap.addAll(SpawnService.getInstance().getCreaturesByMapId(player.getWorldPosition().getMapId()));
        teraObjectInMap.addAll(SpawnService.getInstance().getGathersByMapId(player.getWorldPosition().getMapId()));
        
        Iterator<? extends TeraObject> iterator = teraObjectInMap.iterator();
        while (iterator.hasNext()) {
            TeraObject observer = iterator.next();
            if (player.getWorldPosition().distanceTo(observer.getWorldPosition()) > player.getAccount().getOptions().getDisplayRange().value+1000) {
                iterator.remove();
            }
        }
        
        return teraObjectInMap;
    }
    
    public List<Player> getPlayerObserversForPlayer(Player player) {
        List<Player> playerObservers = WorldService.getInstance().getPlayersByMap(player.getWorldPosition().getMapId());
        Iterator<Player> iterator = playerObservers.iterator();
        while (iterator.hasNext()) {
            Creature observer = iterator.next();
            if (!observer.getObservers().contains(player)) {
                iterator.remove();
            }
        }
        
        return playerObservers;
    }
    
    public List<TeraObject> getTeraObjectObserversForPlayer(Player player) {
        List<TeraObject> teraObjectObservers = new FastList<>();
        teraObjectObservers.addAll(SpawnService.getInstance().getCreaturesByMapId(player.getWorldPosition().getMapId()));
        teraObjectObservers.addAll(SpawnService.getInstance().getGathersByMapId(player.getWorldPosition().getMapId()));
        Iterator<TeraObject> iterator = teraObjectObservers.iterator();
        while (iterator.hasNext()) {
            TeraObject observer = iterator.next();
            if (!observer.getObservers().contains(player)) {
                iterator.remove();
            }
        }
        
        return teraObjectObservers;
    }
    
    public void sendPacketForVisible(Player player, TeraServerPacket packet) {
        this.sendPacketForVisible(player, packet, true);
    }
    
    public void sendPacketForVisible(Player player, TeraServerPacket packet, boolean includeMe) {
        for (Player playerObserver : this.getPlayerObserversForPlayer(player)) {
            playerObserver.getConnection().sendPacket(packet);
        }
        
        if (includeMe) {
            player.getConnection().sendPacket(packet);
        }
    }
    
    public static VisibleService getInstance() {
        if (instance == null) {
            instance = new VisibleService();
        }
        return instance;
    }

    
}
