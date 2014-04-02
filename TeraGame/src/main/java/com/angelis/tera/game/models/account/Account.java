package com.angelis.tera.game.models.account;

import java.util.List;

import javolution.util.FastList;

import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.models.account.enums.AccountTypeEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;

public class Account extends AbstractModel {
    
    private String login;

    private String password;

    private boolean banned;

    private int access;

    private AccountTypeEnum accountType;
    
    private TeraGameConnection connection;
    
    private List<Player> players;
    
    private Options options;
    
    public Account(Integer id) {
        super(id, null);
    }
    
    public Account() {
        super(null, null);
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

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public TeraGameConnection getConnection() {
        return connection;
    }

    public void setConnection(TeraGameConnection connection) {
        this.connection = connection;
    }

    public List<Player> getPlayers() {
        if (players == null) {
            players = new FastList<>();
        }
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options option) {
        this.options = option;
    }

    public void addPlayer(Player player) {
        this.getPlayers().add(player);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + access;
        result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
        result = prime * result + (banned ? 1231 : 1237);
        result = prime * result + ((connection == null) ? 0 : connection.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((players == null) ? 0 : players.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account other = (Account) obj;
        if (access != other.access) {
            return false;
        }
        if (accountType != other.accountType) {
            return false;
        }
        if (banned != other.banned) {
            return false;
        }
        if (connection == null) {
            if (other.connection != null) {
                return false;
            }
        }
        else if (!connection.equals(other.connection)) {
            return false;
        }
        if (login == null) {
            if (other.login != null) {
                return false;
            }
        }
        else if (!login.equals(other.login)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        }
        else if (!password.equals(other.password)) {
            return false;
        }
        if (players == null) {
            if (other.players != null) {
                return false;
            }
        }
        else if (!players.equals(other.players)) {
            return false;
        }
        return true;
    }
}
