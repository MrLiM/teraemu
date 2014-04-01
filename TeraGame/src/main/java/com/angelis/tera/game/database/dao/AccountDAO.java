package com.angelis.tera.game.database.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.angelis.tera.common.database.dao.AbstractDAO;
import com.angelis.tera.game.database.entity.AccountEntity;
import com.angelis.tera.game.services.DatabaseService;

public class AccountDAO extends AbstractDAO<AccountEntity> {
    
    @Override
    public void create(AccountEntity entity) {
        if (entity.getId() != null) {
            return;
        }
        
        Transaction transaction = session.beginTransaction();
        
        Integer id = (Integer) session.save(session.merge(entity));
        entity.setId(id);
        
        transaction.commit();
    }

    @Override
    public AccountEntity read(Integer id) {
        AccountEntity account = null;
        
        Transaction transaction = session.beginTransaction();
        
        account = (AccountEntity) session.get(AccountEntity.class, id);
        
        transaction.commit();
        
        return account;
    }
    
    public AccountEntity readByLogin(String login) {
        AccountEntity account = null;
        
        Transaction transaction = session.beginTransaction();
        
        Query query = session.createQuery("from AccountEntity where login = :login");
        query.setString("login", login);
        
        account = (AccountEntity) query.uniqueResult();
        
        transaction.commit();
        
        return account;
    }

    @Override
    public void update(AccountEntity entity) {
        Transaction transaction = session.beginTransaction();
        
        session.update(session.merge(entity));
        
        transaction.commit();
    }

    @Override
    public void delete(AccountEntity entity) {
        Transaction transaction = session.beginTransaction();
        
        session.delete(session.merge(entity));

        transaction.commit();
    }

    @Override
    protected Session getSession() {
        return DatabaseService.getInstance().getSessionFactory().openSession();
    }
}
