package com.angelis.tera.game.services;

import org.apache.log4j.Logger;

import com.angelis.tera.common.network.config.ServerConfig;
import com.angelis.tera.game.config.NetworkConfig;
import com.angelis.tera.game.network.TeraGameServer;
import com.angelis.tera.game.network.factory.TeraGameAcceptFactory;
import com.angelis.tera.game.network.packet.ClientPacketHandler;
import com.angelis.tera.game.network.packet.ServerPacketHandler;

public class NetworkService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(DatabaseService.class.getName());
    
    /** INSTANCE */
	private static NetworkService instance;
	
	private TeraGameServer teraServer;
	
	private NetworkService() {}
	
	public void onInit() {
	    ServerConfig serverConfig = new ServerConfig(
            NetworkConfig.GAME_BIND_ADDRESS,
            NetworkConfig.GAME_BIND_PORT,
            new TeraGameAcceptFactory(),
            NetworkConfig.GAME_READ_WRITE_PROCESSOR_COUNT
        );
        
        teraServer = new TeraGameServer(serverConfig);
        
        new Thread(teraServer).start();
        
        ServerPacketHandler.init();
        ClientPacketHandler.init();
	    log.info("NetworkService started");
	}
	
	public void onDestroy() {}

	public static NetworkService getInstance() {
		if (instance == null) {
			instance = new NetworkService();
		}
		return instance;
	}
}
