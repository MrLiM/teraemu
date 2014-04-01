package com.angelis.tera.game.services;

import com.angelis.tera.game.event.ServiceEvent;


public abstract class AbstractService implements ServiceEvent {
    
    private boolean started;
    
    public AbstractService() {}
    
    public final void start() {
        if (this.started) {
            return;
        }

        this.onInit();
        this.started = true;
    }
    
    public final void stop() {
        if (!this.started) {
            return;
        }

        this.onDestroy();
        this.started = false;
    }
    
    public final void restart() {
        this.stop();
        this.start();
    }
}
