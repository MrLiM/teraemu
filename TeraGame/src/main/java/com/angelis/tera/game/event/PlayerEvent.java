package com.angelis.tera.game.event;

import com.angelis.tera.game.database.entity.PlayerEntity;

public interface PlayerEvent extends AbstractEvent {
    public void onPlayerConnect(PlayerEntity player);
    public void onPLayerDisconnect(PlayerEntity player);
    public void onPlayerMove(PlayerEntity player);
}
