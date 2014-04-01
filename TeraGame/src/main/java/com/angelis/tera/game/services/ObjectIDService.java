package com.angelis.tera.game.services;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javolution.util.FastMap;

import org.apache.log4j.Logger;

import com.angelis.tera.game.models.enums.ObjectFamilyEnum;

public class ObjectIDService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(ObjectIDService.class.getName());

    /** INSTANCE */
    private static ObjectIDService instance;
    
    private final Map<ObjectFamilyEnum, AtomicInteger> generators = new FastMap<>();
    
    @Override
    public void onInit() {
        log.info("ObjectIDService started");
    }

    @Override
    public void onDestroy() {}

    public final int generateId(ObjectFamilyEnum objectFamilly) {
        AtomicInteger generator = this.generators.get(objectFamilly);
        if (generator == null) {
            generator = new AtomicInteger();
            this.generators.put(objectFamilly, generator);
        }
        return generator.getAndIncrement();
    }
    
    public void releaseId(ObjectFamilyEnum objectFamilly, Integer id) {
        // TODO
    }
    
    /** SINGLETON */
    public static ObjectIDService getInstance() {
        if (instance == null) {
            instance = new ObjectIDService();
        }

        return instance;
    }
}
