package com.angelis.tera.game.services;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.angelis.tera.game.config.GlobalConfig;
import com.angelis.tera.game.config.PlayerConfig;
import com.angelis.tera.game.delegate.PlayerDelegate;
import com.angelis.tera.game.models.Creature;
import com.angelis.tera.game.models.CreatureCurrentStats;
import com.angelis.tera.game.models.CreatureEventTypeEnum;
import com.angelis.tera.game.models.WorldPosition;
import com.angelis.tera.game.models.enums.CheckNameTypeEnum;
import com.angelis.tera.game.models.enums.ObjectFamilyEnum;
import com.angelis.tera.game.models.enums.StatsEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.player.PlayerRelation;
import com.angelis.tera.game.models.player.enums.GenderEnum;
import com.angelis.tera.game.models.player.enums.PlayerModeEnum;
import com.angelis.tera.game.models.player.enums.PlayerMoveTypeEnum;
import com.angelis.tera.game.models.player.enums.RaceEnum;
import com.angelis.tera.game.network.SystemMessages;
import com.angelis.tera.game.network.TeleportLocations;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.enums.CheckCharacterNameResponse;
import com.angelis.tera.game.network.packet.server.SM_CHARACTER_CREATE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_BLOCK_ADD_SUCCESS;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_EMOTE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_FRIEND_ADD_SUCCESS;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_FRIEND_LIST;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_FRIEND_REMOVE_SUCCESS;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_SELECT_CREATURE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_SET_TITLE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_STATS;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_ZONE_CHANGE;
import com.angelis.tera.game.network.packet.server.SM_RESPONSE_CHARACTER_NAME_CHECK_USED;
import com.angelis.tera.game.tasks.TaskTypeEnum;
import com.angelis.tera.game.tasks.player.PlayerAutoSaveTask;
import com.angelis.tera.game.tasks.player.PlayerDeleteTask;
import com.angelis.tera.game.xml.entity.BaseStatsEntity;
import com.angelis.tera.game.xml.entity.BaseStatsEntityHolder;

public class PlayerService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(PlayerService.class.getName());

    /** INSTANCE */
    private static PlayerService instance;

    private PlayerService() {
    }

    public void onInit() {
        log.info("PlayerService started");
    }

    public void onDestroy() {
    }

    // ---- EVENT ---- //
    public final void onPlayerCreate(Player player) {
        player.getConnection().sendPacket(new SM_CHARACTER_CREATE(true));
    }

    public final void onPlayerDelete(Player player) {

    }

    public final void onPlayerConnect(Player player) {
        player.setOnline(true);
        WorldService.getInstance().onPlayerConnect(player);
        
        ThreadPoolService.getInstance().addTask(new PlayerAutoSaveTask(player, PlayerConfig.PLAYER_AUTO_SAVE_DELAY));
        // TODO CALL QUESTSERVICE & OTHER SERVICES
    }

    public void onPlayerDisconnect(Player player) {
        player.setOnline(false);
        player.setLastOnlineTime(new Date());
        
        WorldService.getInstance().onPlayerDisconnect(player);
        
        ThreadPoolService.getInstance().cancelAllTasksByLinkedObject(player);
        ObjectIDService.getInstance().releaseId(ObjectFamilyEnum.fromClass(player.getClass()), player.getUid());

        PlayerDelegate.updatePlayerModel(player);
    }

    public void onPlayerCheckNameUsed(TeraGameConnection connection, short type, String name) {
        boolean nameFree = (this.findPlayerByName(name) == null);
        connection.sendPacket(new SM_RESPONSE_CHARACTER_NAME_CHECK_USED(nameFree));
    }

    public void onPlayerMove(Player player, float x1, float y1, float z1, short heading, float x2, float y2, float z2, PlayerMoveTypeEnum moveType, byte[] unk2, int unk3) {
        final WorldPosition worldPosition = player.getWorldPosition();
        worldPosition.setX(x1);
        worldPosition.setY(y1);
        worldPosition.setZ(z1);
        worldPosition.setHeading(heading);
        
        WorldService.getInstance().onPlayerMove(player);
        player.notifyObservers(CreatureEventTypeEnum.MOVE, x1, y1, z1, heading, x2, y2, z2, moveType, unk2, unk3);
    }

    public void onPlayerEmote(Player player, int emoteId, int duration) {
        player.notifyObservers(CreatureEventTypeEnum.EMOTE, emoteId, duration);
        player.getConnection().sendPacket(new SM_PLAYER_EMOTE(player, emoteId, duration));
    }
    
    public void onPlayerSetTitle(Player player, int title) {
        player.setTitle(title);
        
        player.getConnection().sendPacket(new SM_PLAYER_SET_TITLE(player, title));
        player.notifyObservers(CreatureEventTypeEnum.SET_TITLE, title);
    }
    
    public void onPlayerLevelUp(Player player) {
        TeraGameConnection connection = player.getConnection();
        connection.sendPacket(SystemMessages.CONGRATULATION_YOU_ARE_LEVEL(String.valueOf(player.getLevel())));
        connection.sendPacket(new SM_PLAYER_STATS(player));
    }   
    
    public void onPlayerBlock(Player player, String playerName) {
        final Player targetPlayer = this.findPlayerByName(playerName);
        final TeraGameConnection connection = player.getConnection();
        
        if (targetPlayer == null) {
            connection.sendPacket(SystemMessages.FRIEND_ADD_UNKNOWN_TARGET());
            return;
        }
        
        if (player.equals(targetPlayer)) {
            connection.sendPacket(SystemMessages.FRIEND_ADD_INVALID_TARGET());
            return;
        }
        
        player.getBlockeds().add(new PlayerRelation(targetPlayer));
        connection.sendPacket(new SM_PLAYER_BLOCK_ADD_SUCCESS(targetPlayer));
    }
    public void onPlayerBlockNote(Player player, int playerId, String note) {
        player.getBlockedById(playerId).setNote(note);
    }
    public void onPlayerUnblock(Player activePlayer, int playerId) {
        
    }
    
    public void onPlayerFriendAdd(Player player, String playerName) {
        final Player targetPlayer = this.findPlayerByName(playerName);
        final TeraGameConnection connection = player.getConnection();
        
        if (targetPlayer == null) {
            connection.sendPacket(SystemMessages.FRIEND_ADD_UNKNOWN_TARGET());
            return;
        }
        
        if (player.equals(targetPlayer)) {
            connection.sendPacket(SystemMessages.FRIEND_ADD_INVALID_TARGET());
            return;
        }
        
        player.getFriends().add(new PlayerRelation(targetPlayer));
        connection.sendPacket(new SM_PLAYER_FRIEND_ADD_SUCCESS(targetPlayer));
        connection.sendPacket(SystemMessages.FRIEND_ADD_SUCCESS(playerName));
        connection.sendPacket(new SM_PLAYER_FRIEND_LIST(player));
        
        targetPlayer.getFriends().add(new PlayerRelation(player));
        if (targetPlayer.getConnection() != null) {
            targetPlayer.getConnection().sendPacket(SystemMessages.FRIEND_ADDED_YOU(player.getName()));
            targetPlayer.getConnection().sendPacket(new SM_PLAYER_FRIEND_LIST(targetPlayer));
        }
    }
    
    public void onPlayerFriendRemove(Player player, int playerId) {
        final Player targetPlayer = this.findPlayerById(playerId);
        final TeraGameConnection connection = player.getConnection();
        
        if (targetPlayer == null) {
            connection.sendPacket(SystemMessages.FRIEND_ADD_UNKNOWN_TARGET());
            return;
        }
        
        player.getFriends().remove(targetPlayer);
        connection.sendPacket(new SM_PLAYER_FRIEND_REMOVE_SUCCESS(targetPlayer));
        connection.sendPacket(SystemMessages.FRIEND_REMOVE_SUCCESS(targetPlayer.getName()));
        connection.sendPacket(new SM_PLAYER_FRIEND_LIST(player));
        
        targetPlayer.getFriends().remove(player);
        if (targetPlayer.getConnection() != null) {
            targetPlayer.getConnection().sendPacket(SystemMessages.FRIEND_REMOVED_YOU(player.getName()));
            targetPlayer.getConnection().sendPacket(new SM_PLAYER_FRIEND_LIST(player));
        }
    }
    
    public void onPlayerFriendNote(Player player, int playerId, String note) {
        player.getFriendById(playerId).setNote(note);
    }
    
    public void onPlayerZoneChange(Player player, byte[] zoneData) {
        player.setZoneData(zoneData);
        player.getConnection().sendPacket(new SM_PLAYER_ZONE_CHANGE(zoneData));
    }
    
    public void onPlayerSelectCreature(Player player, int creatureUId, ObjectFamilyEnum objectFamilly) {
        Creature creature = SpawnService.getInstance().getCreatureByUid(creatureUId);
        player.getConnection().sendPacket(new SM_PLAYER_SELECT_CREATURE(creature));
    }

    // ---- EVENT ---- //

    public CheckCharacterNameResponse checkNamePattern(CheckNameTypeEnum type, String name) {
        if (name.length() < type.getMinLength()) {
            return CheckCharacterNameResponse.NOT_LONG_ENOUGH;
        }

        if (name.length() > type.getMaxLength()) {
            return CheckCharacterNameResponse.TOO_LONG;
        }

        if (!type.isSpaceAllowed() && StringUtils.containsWhitespace(name)) {
            return CheckCharacterNameResponse.MUST_NOT_CONTAINS_SPACE;
        }

        if (!name.matches(PlayerConfig.PLAYER_NAME_PATTERN)) {
            return CheckCharacterNameResponse.MUST_BE_ALPHABETIC;
        }

        for (String bannedWordName : GlobalConfig.GLOBAL_NAME_BANNED_WORDS) {
            if (StringUtils.contains(name.toLowerCase(), bannedWordName)) {
                return CheckCharacterNameResponse.MUST_NOT_CONTAINS_BANNED_WORD;
            }
        }

        return CheckCharacterNameResponse.OK;
    }

    public void registerPlayer(TeraGameConnection connection, Player player) {
        connection.setActivePlayer(player);
        player.setAccount(connection.getAccount());

        this.onPlayerConnect(player);
    }

    /** CRUD OPERATIONS */
    public void createPlayer(TeraGameConnection connection, Player player) {
        StorageService.getInstance().onPlayerCreate(player);
        
        player.setAccount(connection.getAccount());
        player.setCreationTime(new Date());
        player.setDeletionTime(null);
        player.setLastOnlineTime(new Date());
        player.setPlayerMode(PlayerModeEnum.NORMAL);
        player.setZoneData(new byte[]{1, 0, 0, 0, 2, 0, 0, 0, 7, 0, 0, 0});
        player.setLevel(1);
        
        // World position
        player.setWorldPosition(TeleportLocations.getStartingPoint());
        
        // Creature current stats
        CreatureCurrentStats creatureCurrentStats = new CreatureCurrentStats();
        BaseStatsEntity baseStats = XMLService.getInstance().getEntity(BaseStatsEntityHolder.class).getBaseStatsByTargetClass(player.getPlayerClass());
        creatureCurrentStats.setHp(baseStats.getHp()); // TODO better here (maybe service)
        creatureCurrentStats.setMaxHp(baseStats.getHp()); // TODO better here (maybe service)
        creatureCurrentStats.setMp(baseStats.getMp());
        creatureCurrentStats.setMaxMp(baseStats.getMp());
        creatureCurrentStats.setAttack(baseStats.getAttack());
        creatureCurrentStats.setDefense(baseStats.getDefense());
        creatureCurrentStats.setSpeed(baseStats.getSpeed());
        creatureCurrentStats.setCritChance(baseStats.getCritChance());
        creatureCurrentStats.setCritResist(baseStats.getCritResist());
        player.setCreatureCurrentStats(creatureCurrentStats);
        
        // Fix for elin
        if (player.getRace().value == 4) {
            if (player.getGender() == GenderEnum.FEMALE) {
                player.setRace(RaceEnum.ELIN);
            }
        }

        PlayerDelegate.createPlayerModel(player);
        connection.getAccount().addPlayer(player);

        this.onPlayerCreate(player);
    }

    public void deletePlayer(int playerId) {
        Player player = PlayerDelegate.readPlayerModelById(playerId);
        if (player == null) {
            return;
        }

        player.setDeletionTime(new Date()); // TODO
        ThreadPoolService.getInstance().addTask(new PlayerDeleteTask(player, PlayerConfig.PLAYER_DELETE_TIMEOUT));
        this.onPlayerDelete(player);
    }

    public void restorePlayer(int playerId) {
        Player player = this.findPlayerById(playerId);
        if (player == null) {
            return;
        }
        
        player.setDeletionTime(null);
        ThreadPoolService.getInstance().cancelTask(player, TaskTypeEnum.PLAYER_DELETE);
    }

    public Player findPlayerById(int playerId) {
        return PlayerDelegate.readPlayerModelById(playerId);
    }

    public Player findPlayerByName(String name) {
        return PlayerDelegate.readPlayerModelByName(name);
    }
    
    public void levelUpPlayer(Player player, int level) {
        if (level > PlayerConfig.PLAYER_MAX_LEVEL) {
            return;
        }
        
        player.setLevel(level);
        this.onPlayerLevelUp(player);
    }

    public double getCreatureBaseStats(Player player, StatsEnum stats) {
        // TODO
        return 100;
    }
    
    /** SINGLETON */
    public static PlayerService getInstance() {
        if (instance == null) {
            instance = new PlayerService();
        }

        return instance;
    }
}
