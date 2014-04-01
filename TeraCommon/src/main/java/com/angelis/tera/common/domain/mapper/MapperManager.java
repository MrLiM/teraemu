package com.angelis.tera.common.domain.mapper;

import java.util.Map;

import javolution.util.FastMap;

import org.apache.log4j.Logger;

import com.angelis.tera.common.database.dao.exception.DAONotFoundException;

public class MapperManager {
    
    /** LOGGER */
    private static Logger log = Logger.getLogger(MapperManager.class.getName());
    
    /** Collection of registered DAOs */
    private static final Map<String, AbstractMapper<?,?>> mappersMap = new FastMap<String, AbstractMapper<?,?>>();
    
    private MapperManager() {
        // empty
    }
    
    public static <T extends AbstractMapper<?,?>> T getMapper(Class<T> clazz) {
        MapperManager.ensureMapper(clazz);
        
        @SuppressWarnings("unchecked")
        T result = (T) mappersMap.get(clazz.getName());
        
        if (result == null) {
            throw new DAONotFoundException("Mapper Not found "+clazz.getName());
        }
        
        return result;
    }
    
    private static final <T extends AbstractMapper<?,?>> void ensureMapper(Class<T> clazz) {
        AbstractMapper<?,?> result = mappersMap.get(clazz.getName());
        if (result == null) {
            try {
                mappersMap.put(clazz.getName(), clazz.newInstance());
                log.info("Initiated Mapper: "+clazz.getName());
            }
            catch (Exception e) {}
        }
    }
}
