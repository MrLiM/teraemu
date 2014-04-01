package com.angelis.tera.game.database.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.angelis.tera.common.database.dao.AbstractDAO;
import com.angelis.tera.game.database.entity.PlayerEntity;
import com.angelis.tera.game.services.DatabaseService;

public class PlayerDAO extends AbstractDAO<PlayerEntity> {

    @Override
    public void create(PlayerEntity entity) {
        if (entity.getId() != null) {
            return;
        }
        
        Transaction transaction = session.beginTransaction();
        
        Integer id = (Integer) session.save(session.merge(entity));
        entity.setId(id);
        
        transaction.commit();
    }

    @Override
    public PlayerEntity read(Integer id) {
        PlayerEntity player = null;
        
        Transaction transaction = session.beginTransaction();
        
        player = (PlayerEntity) session.get(PlayerEntity.class, id);
        
        transaction.commit();
        
        return player;
    }
    
    public PlayerEntity readByName(String name) {
        PlayerEntity player = null;
        
        Transaction transaction = session.beginTransaction();
        
        Query query = session.createQuery("from PlayerEntity where name = :name");
        query.setString("name", name);
        
        player = (PlayerEntity) query.uniqueResult();
        
        transaction.commit();
        
        return player;
    }

    @Override
    public void update(PlayerEntity entity) {
        Transaction transaction = session.beginTransaction();
        
        session.update(session.merge(entity));
        
        transaction.commit();
    }

    @Override
    public void delete(PlayerEntity entity) {
        Transaction transaction = session.beginTransaction();
        
        session.delete(session.merge(entity));
        
        transaction.commit();
    }
    
    @Override
    protected Session getSession() {
        return DatabaseService.getInstance().getSessionFactory().openSession();
    }
}
