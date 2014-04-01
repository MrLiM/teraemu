package com.angelis.tera.game.event;

public interface ServiceEvent extends AbstractEvent {
    public void onInit();
    public void onDestroy();
}
