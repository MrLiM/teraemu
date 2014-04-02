package com.angelis.tera.game;

import com.angelis.tera.common.utils.PrintUtils;
import com.angelis.tera.game.services.AccountService;
import com.angelis.tera.game.services.AdminService;
import com.angelis.tera.game.services.ChatService;
import com.angelis.tera.game.services.ConfigService;
import com.angelis.tera.game.services.DatabaseService;
import com.angelis.tera.game.services.DialogService;
import com.angelis.tera.game.services.NetworkService;
import com.angelis.tera.game.services.ObjectIDService;
import com.angelis.tera.game.services.PlayerService;
import com.angelis.tera.game.services.QuestService;
import com.angelis.tera.game.services.RequestService;
import com.angelis.tera.game.services.SpawnService;
import com.angelis.tera.game.services.StorageService;
import com.angelis.tera.game.services.ThreadPoolService;
import com.angelis.tera.game.services.UserService;
import com.angelis.tera.game.services.VisibleService;
import com.angelis.tera.game.services.WorldService;
import com.angelis.tera.game.services.XMLService;

public class MainGame {
    
	public static void main(String[] args) {
	    long start = System.currentTimeMillis();
	    
	    PrintUtils.printSection("Services");
	    ConfigService.getInstance().start();
	    ObjectIDService.getInstance().start();
	    DatabaseService.getInstance().start();
	    XMLService.getInstance().start();
	    ChatService.getInstance().start();
		AccountService.getInstance().start();
		PlayerService.getInstance().start();
		QuestService.getInstance().start();
		AdminService.getInstance().start();
		UserService.getInstance().start();
		ThreadPoolService.getInstance().start();
		WorldService.getInstance().start();
		StorageService.getInstance().start();
		SpawnService.getInstance().start();
		DialogService.getInstance().start();
		RequestService.getInstance().start();
		VisibleService.getInstance().start();
		NetworkService.getInstance().start();

		PrintUtils.printSection("Launching");
		System.out.println("Server started in "+((System.currentTimeMillis()-start)/1000)+" seconds");
	}
}
