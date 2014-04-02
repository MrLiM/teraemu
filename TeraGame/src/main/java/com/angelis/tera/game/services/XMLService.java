package com.angelis.tera.game.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import javolution.util.FastMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.angelis.tera.common.xml.entity.AbstractXMLEntity;
import com.angelis.tera.game.config.GlobalConfig;
import com.angelis.tera.game.xml.entity.BaseStatsEntityHolder;
import com.angelis.tera.game.xml.entity.CreatureEntityHolder;
import com.angelis.tera.game.xml.entity.ExperienceEntityHolder;
import com.angelis.tera.game.xml.entity.GatherEntityHolder;
import com.angelis.tera.game.xml.entity.ItemSetEntityHolder;
import com.angelis.tera.game.xml.entity.QuestEntityHolder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class XMLService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(XMLService.class.getName());
    
    /** INSTANCE */
    private static XMLService instance;
    
    /** CONSTANTS */
    private static enum Path {
        DATA("./data"),
        TEMP("./temp");
        
        public final String path;
        
        Path(String path) {
            this.path = path;
        }
    }
    
    private Map<Class<? extends AbstractXMLEntity>, AbstractXMLEntity> cache = new FastMap<Class<? extends AbstractXMLEntity>, AbstractXMLEntity>();
    
    private XMLService() {}
    
    public void onInit() {
        File data = new File(Path.DATA.path);
        if (!data.exists()) {
            data.mkdir();
        }
        
        File bin = new File(Path.TEMP.path);
        if (!bin.exists()) {
            bin.mkdir();
        }
        
        this.checkBinFile(ExperienceEntityHolder.class, "experiences");
        this.checkBinFile(QuestEntityHolder.class, "quests");
        this.checkBinFile(ItemSetEntityHolder.class, "itemsets");
        this.checkBinFile(BaseStatsEntityHolder.class, "basestats");
        this.checkBinFile(CreatureEntityHolder.class, "creatures");
        this.checkBinFile(GatherEntityHolder.class, "gathers");
        log.info("XMLService started");
    }
    
    public void onDestroy() {
        this.cache.clear();
    }
    
    public void readXMLData(Class<? extends AbstractXMLEntity> entityClass, File xml) {
        if (!xml.exists()) {
            log.error("Can't find xml file : "+xml.getPath());
            return;
        }
        
        try {
            File schemaFile = new File("data/schema/"+FilenameUtils.removeExtension(xml.getName())+".xsd");
            
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);
                    
            JAXBContext jaxbContext = JAXBContext.newInstance(entityClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);
            
            AbstractXMLEntity entity = (AbstractXMLEntity) unmarshaller.unmarshal(xml);
            
            if (entity == null) {
                log.error("Unable to unmarshal entity "+entityClass.getName());
                return;
            }
            
            entity.afterUnmarshalling(unmarshaller);
            entity.onLoad();
            this.cache.put(entityClass, entity);
        }
        catch (JAXBException | SAXException e) {
            log.error(e.getMessage(), e);
        }
    }
    
    private void readBinData(Class<? extends AbstractXMLEntity> entityClass, File bin) {
        if (!bin.exists()) {
            log.error("Can't find bin file : "+bin.getPath());
            return;
        }
        
        Kryo kryo = new Kryo();
        try {
            Input input = new Input(new FileInputStream(bin));
            AbstractXMLEntity entity = kryo.readObject(input, entityClass);
            input.close();
            
            entity.onLoad();
            this.cache.put(entityClass, entity);
        }
        catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
    }
    
    public void writeXMLData(AbstractXMLEntity entity, File xml) {
        try {
            FileWriter out = new FileWriter(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(entity, out);
            out.close();
        }
        catch (JAXBException | IOException e) {
            log.error(e.getMessage(), e);
        }
    }
    
    public void writeBinData(AbstractXMLEntity abstractXMLEntity, File bin) {
        if (abstractXMLEntity == null) {
            return;
        }
        
        Kryo kryo = new Kryo();

        try {
            Output output = new Output(new FileOutputStream(bin));
            kryo.writeObject(output, abstractXMLEntity);
            output.close();
        }
        catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
    }

    public <T extends AbstractXMLEntity> T getEntity(Class<T> entityClass) {
        @SuppressWarnings("unchecked")
        T entity = (T) this.cache.get(entityClass);
        if (entity == null) {
            log.error(entityClass.getName()+" has no data");
        }
        
        return entity;
    }
    
    private void checkBinFile(Class<? extends AbstractXMLEntity> entityClass, String... files) {
        for (String file : files) {
            File xml = new File(Path.DATA.path+File.separator+"xml"+File.separator+file+".xml");
            if (!xml.exists()) {
                log.error("Can't find xml file : "+xml.getPath());
                return;
            }
            
            File bin = new File(Path.TEMP.path + File.separator + file + ".bin");
            if (bin.exists() && GlobalConfig.GLOBAL_DATA_MANAGEMENT_USE_CACHE) {
                if (bin.lastModified() > xml.lastModified()) {
                    this.readBinData(entityClass, bin);
                    return;
                }
            }
            
            this.readXMLData(entityClass, xml);
            
            if (GlobalConfig.GLOBAL_DATA_MANAGEMENT_USE_CACHE) {
                this.writeBinData(this.cache.get(entityClass), bin);
            }
        }
    }
    
    /** SINGLETON */
    public static final XMLService getInstance() {
        if (instance == null) {
            instance = new XMLService();
        }
        
        return instance;
    }
}
