package com.angelis.tera.game.database.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.angelis.tera.common.database.entity.AbstractDatabaseEntity;
import com.angelis.tera.game.models.account.enums.AccountTypeEnum;
import com.angelis.tera.game.models.account.enums.DisplayRangeEnum;

@Entity
@Table(name = "accounts")
public class AccountEntity extends AbstractDatabaseEntity {

    private static final long serialVersionUID = -4034792019245322102L;

    @Column()
    private String login;
    
    @Column()
    private String password;
    
    @Column()
    private boolean banned;
    
    @Column()
    private int access;
    
    @Column
    private DisplayRangeEnum displayRange;
    
    @Column()
    private AccountTypeEnum accountType;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade=CascadeType.ALL)
    private Set<PlayerEntity> players;

    public AccountEntity(Integer id) {
        super(id);
    }
    
    public AccountEntity() {
        super();
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    
    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public DisplayRangeEnum getDisplayRange() {
        return displayRange;
    }

    public void setDisplayRange(DisplayRangeEnum displayRange) {
        this.displayRange = displayRange;
    }

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public Set<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerEntity> players) {
        this.players = players;
    }

    public void addPlayer(PlayerEntity player) {
        this.getPlayers().add(player);
        player.setAccount(this);
    }
}
