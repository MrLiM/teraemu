package com.angelis.tera.game.services;


import java.io.File;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.angelis.tera.game.database.entity.AccountEntity;
import com.angelis.tera.game.database.entity.GuildEntity;
import com.angelis.tera.game.database.entity.PlayerEntity;
import com.angelis.tera.game.database.entity.StorageEntity;
import com.angelis.tera.game.database.entity.StorageItemEntity;

public class DatabaseService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(DatabaseService.class.getName());
    
    /** INSTANCE */
    private static DatabaseService instance = null;
    
    
    private AnnotationConfiguration annotationConfiguration;
    private SessionFactory sessionFactory;
    
    private DatabaseService() {
        try {
            this.annotationConfiguration = new AnnotationConfiguration()
                .configure(new File("conf/hibernate.cfg.xml"))
                    .addPackage("com.angelis.game.database.entity");

            this.annotationConfiguration
                .addAnnotatedClass(AccountEntity.class)
                .addAnnotatedClass(PlayerEntity.class)
                .addAnnotatedClass(StorageEntity.class)
                .addAnnotatedClass(GuildEntity.class)
                .addAnnotatedClass(StorageItemEntity.class)
            ;
            
            this.sessionFactory = this.annotationConfiguration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void onInit() {
        log.info("DatabaseService started");
    }
    
    public void onDestroy() {}

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
    
    /** SINGLETON */
    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }
}
