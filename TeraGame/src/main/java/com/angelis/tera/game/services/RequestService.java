package com.angelis.tera.game.services;

import java.util.Collections;
import java.util.Map;

import javolution.util.FastMap;

import org.apache.log4j.Logger;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.player.request.Request;
import com.angelis.tera.game.tasks.TaskTypeEnum;

public class RequestService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(RequestService.class.getName());
    
    /** INSTANCE */
    private static RequestService instance;
    
    private Map<Integer, Request> requests = Collections.synchronizedMap(new FastMap<Integer, Request>());
    
    private RequestService() {}
    
    @Override
    public void onInit() {
        log.info("RequestService started");
    }

    @Override
    public void onDestroy() {
        
    }
    
    public void onPlayerRequest(Player player, Request request) {
        Request currentRequest = player.getController().getRequest();
        if (currentRequest != null) {
            // TODO
        }
        
        requests.put(request.getUid(), request);
        request.doAction();
    }
    
    public void onPlayerRespond(int requestUid, boolean accepted) {
        Request request = this.requests.get(requestUid);
        if (accepted) {
            request.onAccept();
        } else {
            request.onDecline();
        }
    }
    
    public void onPlayerCancel(int requestUid) {
        Request request = this.requests.get(requestUid);
        ThreadPoolService.getInstance().cancelTask(request, TaskTypeEnum.PLAYER_REQUEST);
        request.doCancel();
    }

    public void cancelRequest(Request request) {
        request.doCancel();
    }
    
    /** SINGLETON */
    public static RequestService getInstance() {
        if (instance == null) {
            instance = new RequestService();
        }

        return instance;
    }
}
