package com.angelis.tera.game.domain.mapper.database;

import java.util.List;
import java.util.Set;

import javolution.util.FastList;
import javolution.util.FastSet;

import com.angelis.tera.common.domain.mapper.DatabaseMapper;
import com.angelis.tera.common.domain.mapper.MapperManager;
import com.angelis.tera.common.entity.AbstractEntity;
import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.database.entity.AccountEntity;
import com.angelis.tera.game.database.entity.PlayerEntity;
import com.angelis.tera.game.models.account.Account;
import com.angelis.tera.game.models.account.Options;
import com.angelis.tera.game.models.player.Player;

public class AccountMapper extends DatabaseMapper<AccountEntity, Account> {
    
    @Override
    public AccountEntity map(final Account model, AbstractEntity linkedEntity) {
        AccountEntity accountEntity = new AccountEntity(model.getId());
        
        // DIRECT
        accountEntity.setLogin(model.getLogin());
        accountEntity.setPassword(model.getPassword());
        accountEntity.setBanned(model.isBanned());
        accountEntity.setAccess(model.getAccess());
        accountEntity.setAccountType(model.getAccountType());
        
        // OPTION
        Options option = model.getOptions();
        accountEntity.setDisplayRange(option.getDisplayRange());
        
        // PLAYER
        Set<PlayerEntity> players = new FastSet<>();
        for (Player player : model.getPlayers()) {
            if (linkedEntity instanceof PlayerEntity && this.equals(player, linkedEntity)) {
                players.add((PlayerEntity) linkedEntity);
            } else {
                players.add(MapperManager.getMapper(PlayerMapper.class).map(player, accountEntity));
            }
        }
        accountEntity.setPlayers(players);
        
        return accountEntity;
    }
    
    @Override
    public Account map(final AccountEntity model, AbstractModel linkedModel) {
        Account account = new Account(model.getId());
        
        // DIRECT
        account.setLogin(model.getLogin());
        account.setPassword(model.getPassword());
        account.setBanned(model.isBanned());
        account.setAccess(model.getAccess());
        account.setAccountType(model.getAccountType());
        
        // OPTION
        final Options option = new Options();
        option.setDisplayRange(model.getDisplayRange());
        account.setOptions(option);
        
        // PLAYER
        List<Player> players = new FastList<Player>();
        for (PlayerEntity playerEntity : model.getPlayers()) {
            if (linkedModel instanceof Player && this.equals(linkedModel, playerEntity)) {
                players.add((Player) linkedModel);
            } else {
                players.add(MapperManager.getMapper(PlayerMapper.class).map(playerEntity, account));
            }
            
        }
        account.setPlayers(players);
        
        return account;
    }

    @Override
    public boolean equals(AbstractModel model, AbstractEntity entity) {
        return model.getId() == entity.getId();
    }
}
