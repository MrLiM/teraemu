package com.angelis.tera.game.services;

import org.apache.log4j.Logger;

public class QuestService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(QuestService.class.getName());

    /** INSTANCE */
    private static QuestService instance;
    
    @Override
    public void onInit() {
        log.info("QuestService started");
    }

    @Override
    public void onDestroy() {
    }

    /** SINGLETON */
    public static QuestService getInstance() {
        if (instance == null) {
            instance = new QuestService();
        }

        return instance;
    }
}
