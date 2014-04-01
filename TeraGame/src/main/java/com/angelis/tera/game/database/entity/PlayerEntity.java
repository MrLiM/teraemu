package com.angelis.tera.game.database.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.angelis.tera.common.database.entity.AbstractDatabaseEntity;
import com.angelis.tera.game.models.player.enums.GenderEnum;
import com.angelis.tera.game.models.player.enums.PlayerClassEnum;
import com.angelis.tera.game.models.player.enums.PlayerModeEnum;
import com.angelis.tera.game.models.player.enums.RaceEnum;

@Entity
@Table(name = "players")
public class PlayerEntity extends AbstractDatabaseEntity {

    private static final long serialVersionUID = 1630861678711998407L;

    @Column()
    private String name;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Enumerated(EnumType.STRING)
    private RaceEnum race;

    @Enumerated(EnumType.STRING)
    private PlayerClassEnum playerClass;

    @Column
    private int level;

    @Column
    private long experience;

    @Column()
    private int money;
    
    @Column
    private int hp;
    
    @Column
    private int maxHp;
    
    @Column
    private int mp;
    
    @Column
    private int maxMp;
    
    @Column
    private int stamina;

    @Column
    private int attack;
    
    @Column
    private int defense;
    
    @Column
    private int impact;
    
    @Column
    private int balance;
    
    @Column
    private int speed;
    
    @Column
    private int critChance;
    
    @Column
    private int critResist;

    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletionTime;
    
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOnlineTime;

    @Column()
    private int mapId;

    @Column()
    private float x;

    @Column()
    private float y;

    @Column()
    private float z;

    @Column()
    private short heading;

    @Column()
    private byte[] data;

    @Column()
    private byte[] details1;

    @Column()
    private byte[] details2;

    @Column()
    private boolean online;
    
    @Column
    private int title;
    
    @Column()
    private PlayerModeEnum playerMode;
    
    @Column()
    private byte[] zoneData;
    
    @ManyToOne(optional=true)
    private GuildEntity guild;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<StorageEntity> storages;

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;
    
    //@ManyToMany(mappedBy="owner")
    @Transient
    private Set<FriendEntity> friends;
    
    //@ManyToMany(mappedBy="owner")
    @Transient
    private Set<BlockEntity> blockeds;

    public PlayerEntity(Integer id) {
        super(id);
    }
    
    public PlayerEntity() {
        super();
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int i) {
        this.level = i;
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
    
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public int getCritResist() {
        return critResist;
    }

    public void setCritResist(int critResist) {
        this.critResist = critResist;
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

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public short getHeading() {
        return heading;
    }

    public void setHeading(short heading) {
        this.heading = heading;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getDetails1() {
        return details1;
    }

    public void setDetails1(byte[] details1) {
        this.details1 = details1;
    }

    public byte[] getDetails2() {
        return details2;
    }

    public void setDetails2(byte[] details2) {
        this.details2 = details2;
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

    public byte[] getZoneData() {
        return zoneData;
    }

    public void setZoneData(byte[] zoneData) {
        this.zoneData = zoneData;
    }

    public GuildEntity getGuild() {
        return guild;
    }

    public void setGuild(GuildEntity guild) {
        this.guild = guild;
    }

    public Set<StorageEntity> getStorages() {
        return storages;
    }

    public void setStorages(Set<StorageEntity> storages) {
        this.storages = storages;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
    
    public Set<FriendEntity> getFriends() {
        return friends;
    }

    public void setFriends(Set<FriendEntity> friends) {
        this.friends = friends;
    }

    public Set<BlockEntity> getBlockeds() {
        return blockeds;
    }

    public void setBlockeds(Set<BlockEntity> blockeds) {
        this.blockeds = blockeds;
    }
}
