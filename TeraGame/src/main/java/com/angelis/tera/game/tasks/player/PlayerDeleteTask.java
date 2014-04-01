package com.angelis.tera.game.tasks.player;

import com.angelis.tera.game.delegate.PlayerDelegate;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.tasks.AbstractTask;
import com.angelis.tera.game.tasks.TaskTypeEnum;

public class PlayerDeleteTask extends AbstractTask<Player> {

    public PlayerDeleteTask(Player linkedObject, int delay) {
        super(linkedObject, TaskTypeEnum.PLAYER_DELETE, delay);
    }

    @Override
    public void execute() {
        PlayerDelegate.deletePlayerModel(this.linkedObject);
    }

}
