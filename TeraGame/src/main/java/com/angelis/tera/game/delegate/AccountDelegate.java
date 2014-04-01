package com.angelis.tera.game.delegate;

import com.angelis.tera.common.database.dao.DAOManager;
import com.angelis.tera.common.domain.mapper.MapperManager;
import com.angelis.tera.game.database.dao.AccountDAO;
import com.angelis.tera.game.database.entity.AccountEntity;
import com.angelis.tera.game.domain.mapper.database.AccountMapper;
import com.angelis.tera.game.models.account.Account;

public class AccountDelegate {
    //------------------------------------------------------------
    //                       AccountEntity
    //------------------------------------------------------------
    public static void createAccountEntity(AccountEntity entity) {
        DAOManager.getDAO(AccountDAO.class).create(entity);
    }
    
    public static AccountEntity readAccountEntityById(Integer id) {
        return DAOManager.getDAO(AccountDAO.class).read(id);
    }
    
    public static AccountEntity readAccountEntityByLogin(String login) {
        return DAOManager.getDAO(AccountDAO.class).readByLogin(login);
    }
    
    public static void updateAccountEntity(AccountEntity entity) {
        DAOManager.getDAO(AccountDAO.class).update(entity);
    }
    
    //------------------------------------------------------------
    //                          Account
    //------------------------------------------------------------
    public static void createAccountModel(Account model) {
        AccountEntity accountEntity = MapperManager.getMapper(AccountMapper.class).map(model);
        AccountDelegate.createAccountEntity(accountEntity);
        model.setId(accountEntity.getId());
    }
    
    public static Account readAccountModelById(Integer id) {
        Account player = null;
        AccountEntity accountEntity = AccountDelegate.readAccountEntityById(id);
        if (accountEntity != null) {
            player = MapperManager.getMapper(AccountMapper.class).map(accountEntity);
        }
        return player;
    }
    
    public static Account readAccountModelByLogin(String login) {
        Account account = null;
        AccountEntity accountEntity = AccountDelegate.readAccountEntityByLogin(login);
        if (accountEntity != null) {
            account = MapperManager.getMapper(AccountMapper.class).map(accountEntity);
        }
        return account;
    }
    
    public static void updateAccountModel(Account account) {
        AccountEntity accountEntity = MapperManager.getMapper(AccountMapper.class).map(account);
        AccountDelegate.updateAccountEntity(accountEntity);
    }
}
