package com.angelis.tera.game.services;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.angelis.tera.common.config.ConfigurableProcessor;
import com.angelis.tera.common.utils.PropertiesUtils;
import com.angelis.tera.game.config.AccountConfig;
import com.angelis.tera.game.config.AdminConfig;
import com.angelis.tera.game.config.GlobalConfig;
import com.angelis.tera.game.config.NetworkConfig;
import com.angelis.tera.game.config.PlayerConfig;
import com.angelis.tera.game.config.UserConfig;

public class ConfigService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(ConfigService.class.getName());
    
    /** INSTANCE */
    private static ConfigService instance;
    
    private ConfigService() {}
    
    public void onInit() {
        PropertyConfigurator.configure("./conf/log4j.properties");
        
        try {
            Properties[] properties = PropertiesUtils.loadAllFromDirectory("conf");
            ConfigurableProcessor.process(AccountConfig.class, properties);
            ConfigurableProcessor.process(AdminConfig.class, properties);
            ConfigurableProcessor.process(GlobalConfig.class, properties);
            ConfigurableProcessor.process(NetworkConfig.class, properties);
            ConfigurableProcessor.process(PlayerConfig.class, properties);
            ConfigurableProcessor.process(UserConfig.class, properties);
        }
        catch (Exception e) {
            log.fatal("Can't load gameserver configurations", e);
            throw new Error("Can't load gameserver configurations", e);
        }
        log.info("ConfigService started");
    }
    
    public void onDestroy() {}

    public static ConfigService getInstance() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }
}
