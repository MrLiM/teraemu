package com.angelis.tera.game.tasks.player;

import com.angelis.tera.game.delegate.PlayerDelegate;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.tasks.AbstractTask;
import com.angelis.tera.game.tasks.TaskTypeEnum;

public class PlayerAutoSaveTask extends AbstractTask<Player> {

    public PlayerAutoSaveTask(Player linkedObject, int delay) {
        super(linkedObject, TaskTypeEnum.PLAYER_AUTO_SAVE, delay);
    }

    @Override
    public void execute() {
        PlayerDelegate.updatePlayerModel(this.linkedObject);
    }
}
