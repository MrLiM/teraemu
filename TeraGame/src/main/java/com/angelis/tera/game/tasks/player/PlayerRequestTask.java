package com.angelis.tera.game.tasks.player;

import com.angelis.tera.game.models.player.request.Request;
import com.angelis.tera.game.services.RequestService;
import com.angelis.tera.game.tasks.AbstractTask;
import com.angelis.tera.game.tasks.TaskTypeEnum;

public class PlayerRequestTask extends AbstractTask<Request> {

    public PlayerRequestTask(Request linkedObject, int delay) {
        super(linkedObject, TaskTypeEnum.PLAYER_REQUEST, delay);
    }

    @Override
    public void execute() {
        Request request = this.getLinkedObject();
        if (request != null) {
            RequestService.getInstance().cancelRequest(request);
        }
    }
}
