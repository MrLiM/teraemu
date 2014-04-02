package com.angelis.tera.game.models.player;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javolution.util.FastList;

import com.angelis.tera.common.lang.IsObservable;
import com.angelis.tera.game.controllers.PlayerController;
import com.angelis.tera.game.models.Creature;
import com.angelis.tera.game.models.CreatureEventTypeEnum;
import com.angelis.tera.game.models.Gather;
import com.angelis.tera.game.models.Guild;
import com.angelis.tera.game.models.TeraCreature;
import com.angelis.tera.game.models.account.Account;
import com.angelis.tera.game.models.enums.DespawnTypeEnum;
import com.angelis.tera.game.models.enums.ObjectFamilyEnum;
import com.angelis.tera.game.models.enums.StorageTypeEnum;
import com.angelis.tera.game.models.player.enums.GenderEnum;
import com.angelis.tera.game.models.player.enums.PlayerClassEnum;
import com.angelis.tera.game.models.player.enums.PlayerModeEnum;
import com.angelis.tera.game.models.player.enums.PlayerMoveTypeEnum;
import com.angelis.tera.game.models.player.enums.PlayerRelationEnum;
import com.angelis.tera.game.models.player.enums.RaceEnum;
import com.angelis.tera.game.models.storage.Storage;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;
import com.angelis.tera.game.network.packet.server.SM_CREATURE_INFO;
import com.angelis.tera.game.network.packet.server.SM_CREATURE_MOVE;
import com.angelis.tera.game.network.packet.server.SM_CREATURE_REMOVE;
import com.angelis.tera.game.network.packet.server.SM_GATHER_INFO;
import com.angelis.tera.game.network.packet.server.SM_GATHER_REMOVE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_EMOTE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_INFO;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_MOVE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_REMOVE;
import com.angelis.tera.game.services.ObjectIDService;

public class Player extends Creature {

    private String name;
    private GenderEnum gender;
    private RaceEnum race;
    private PlayerClassEnum playerClass;
    private long experience;
    private int money;
    private Date creationTime;
    private Date deletionTime;
    private Date lastOnlineTime;
    private boolean online;
    private int title;
    private PlayerModeEnum playerMode;
    private PlayerAppearance playerAppearance;
    
    private Account account;
    private Guild guild;
    private Set<Storage> storages;
    private List<PlayerRelation> friends;
    private List<PlayerRelation> blockeds;
    private byte[] zoneData;

    public Player(Integer abstractId) {
        super(abstractId, ObjectIDService.getInstance().generateId(ObjectFamilyEnum.PLAYER), new PlayerController());
    }
    
    public Player() {
        this(null);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public RaceEnum getRace() {
        return race;
    }

    public void setRace(RaceEnum race) {
        this.race = race;
    }

    public PlayerClassEnum getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(PlayerClassEnum playerClass) {
        this.playerClass = playerClass;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getDeletionTime() {
        return deletionTime;
    }

    public void setDeletionTime(Date deletionTime) {
        this.deletionTime = deletionTime;
    }

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public PlayerModeEnum getPlayerMode() {
        return playerMode;
    }

    public void setPlayerMode(PlayerModeEnum playerMode) {
        this.playerMode = playerMode;
    }

    public PlayerAppearance getPlayerAppearance() {
        return playerAppearance;
    }

    public void setPlayerAppearance(PlayerAppearance playerAppearance) {
        this.playerAppearance = playerAppearance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public Set<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Set<Storage> storages) {
        this.storages = storages;
    }

    public List<PlayerRelation> getFriends() {
        if (friends == null) {
            friends = new FastList<>();
        }
        return friends;
    }
    public PlayerRelation getFriendById(Integer playerId) {
        PlayerRelation relation = null;
        for (PlayerRelation playerRelation : this.friends) {
            if (playerRelation.getPlayer().getId() == playerId) {
                relation = playerRelation;
                break;
            }
        }
        
        return relation;
    }

    public void setFriends(List<PlayerRelation> friends) {
        this.friends = friends;
    }

    public List<PlayerRelation> getBlockeds() {
        if (blockeds == null) {
            blockeds = new FastList<>();
        }
        return blockeds;
    }
    public PlayerRelation getBlockedById(Integer playerId) {
        PlayerRelation relation = null;
        for (PlayerRelation playerRelation : this.blockeds) {
            if (playerRelation.getPlayer().getId() == playerId) {
                relation = playerRelation;
                break;
            }
        }
        
        return relation;
    }

    public void setBlockeds(List<PlayerRelation> blockeds) {
        this.blockeds = blockeds;
    }
    
    public byte[] getZoneData() {
        return this.zoneData;
    }
    
    public void setZoneData(byte[] zoneData) {
        this.zoneData = zoneData;
    }
    
    @Override
    public PlayerController getController() {
        return (PlayerController) this.controller;
    }
    
    @Override
    public void onObservableUpdate(CreatureEventTypeEnum event, IsObservable<CreatureEventTypeEnum> observable, Object... data) {
        List<TeraServerPacket> packets = new FastList<>();
        switch (event) {
            case APPEAR:
                if (observable instanceof Player) {
                    packets.add(new SM_PLAYER_INFO((Player) observable, PlayerRelationEnum.FRIENDLY)); // TODO
                } else if (observable instanceof TeraCreature) {
                    packets.add(new SM_CREATURE_INFO((TeraCreature) observable));
                } else if (observable instanceof Gather) {
                    packets.add(new SM_GATHER_INFO((Gather) observable));
                }
            break;
            
            case DISAPPEAR:
                if (observable instanceof Player) {
                    packets.add(new SM_PLAYER_REMOVE((Player) observable));
                } else if (observable instanceof TeraCreature) {
                    packets.add(new SM_CREATURE_REMOVE((TeraCreature) observable, DespawnTypeEnum.DELETE));
                } else if (observable instanceof Gather) {
                    packets.add(new SM_GATHER_REMOVE((Gather) observable, DespawnTypeEnum.DELETE));
                }
            break;
            
            case MOVE:
                float x1 = (float) data[0];
                float y1 = (float) data[1];
                float z1 = (float) data[2];
                short heading = (short) data[3];
                float x2 = (float) data[4];
                float y2 = (float) data[5];
                float z2 = (float) data[6];

                if (this.getWorldPosition().distanceTo(x1, y1, z1) > this.getAccount().getOptions().getDisplayRange().value) {
                    // Creature is no more visible so it's a remove
                    if (observable instanceof Player) {
                        packets.add(new SM_PLAYER_REMOVE((Player) observable));
                    } else if (observable instanceof TeraCreature) {
                        packets.add(new SM_CREATURE_REMOVE((TeraCreature) observable, DespawnTypeEnum.DELETE));
                    }
                } else {
                    // Creature is still visible so it's a simple move
                    if (observable instanceof Player) {
                        PlayerMoveTypeEnum playerMoveType = (PlayerMoveTypeEnum) data[7];
                        byte[] unk2 = (byte[]) data[8];
                        int unk3 = (int) data[9];
                        
                        packets.add(new SM_PLAYER_MOVE((Player) observable, x1, y1, z1, heading, x2, y2, z2, playerMoveType, unk2, unk3));
                    } else if (observable instanceof TeraCreature) {
                        packets.add(new SM_CREATURE_MOVE((TeraCreature) observable, x1, y1, z1));
                    }
                }
            break;
            
            case EMOTE:
                int emoteId = (int) data[0];
                int duration = (int) data[1];
                packets.add(new SM_PLAYER_EMOTE((Player) observable, emoteId, duration));
            break;
            
            case SET_TITLE:
                int titleId = (int) data[0];
                // TODO
            break;
            
            default:
        }

        for (TeraServerPacket packet : packets) {
            this.getConnection().sendPacket(packet);
        }
    }

    public Storage getStorage(StorageTypeEnum storageType) {
        // TODO
        for (Storage storage : this.getStorages()) {
            if (storage.getStorageType() == storageType) {
                return storage;
            }
        }
        
        return null;
    }
    
    public TeraGameConnection getConnection() {
        return this.getAccount().getConnection();
    }
    
    public int getRaceGenderClassValue() {
        return (10101 + 200 * this.race.value + 100 * this.gender.getValue() + this.playerClass.value);
    }

    public long getExpShown() {
        // TODO
        return 0;
    }

    public long getExpNeeded() {
        // TODO
        return 100;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        if (!name.equals(other.name)) {
            return false;
        }
        return super.equals(obj);
    }
}
