package com.angelis.tera.game.database.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.angelis.tera.common.database.dao.AbstractDAO;
import com.angelis.tera.game.database.entity.AccountEntity;
import com.angelis.tera.game.database.entity.GuildEntity;
import com.angelis.tera.game.services.DatabaseService;

public class GuildDAO extends AbstractDAO<GuildEntity> {

    @Override
    public void create(GuildEntity entity) {
        Transaction transaction = session.beginTransaction();
        
        Integer id = (Integer) session.save(session.merge(entity));
        entity.setId(id);
        
        transaction.commit();
    }

    @Override
    public GuildEntity read(Integer id) {
        GuildEntity guild = null;
        
        Transaction transaction = session.beginTransaction();
        
        guild = (GuildEntity) session.get(AccountEntity.class, id);
        
        transaction.commit();
        
        return guild;
    }

    @Override
    public void update(GuildEntity entity) {
        Transaction transaction = session.beginTransaction();
        
        session.update(session.merge(entity));
        
        transaction.commit();
    }

    @Override
    public void delete(GuildEntity entity) {
        Transaction transaction = session.beginTransaction();
        
        session.delete(session.merge(entity));
        
        transaction.commit();
    }

    @Override
    protected Session getSession() {
        return DatabaseService.getInstance().getSessionFactory().openSession();
    }
}
