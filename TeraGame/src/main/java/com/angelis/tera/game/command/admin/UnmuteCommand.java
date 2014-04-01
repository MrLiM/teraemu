package com.angelis.tera.game.command.admin;

import com.angelis.tera.game.controllers.enums.Right;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.services.PlayerService;

public class UnmuteCommand extends AbstractAdminCommand {

    @Override
    public void execute(TeraGameConnection connection, String[] arguments) {
        Player targetPlayer = PlayerService.getInstance().findPlayerByName(arguments[0]);
        if (targetPlayer == null || !targetPlayer.isOnline()) {
            return;
        }
        
        targetPlayer.getPlayerController().addRight(Right.TALK);
    }

    @Override
    public int getAccessLevel() {
        return 0;
    }

    @Override
    public int getArgumentCount() {
        return 1;
    }
    
    @Override
    public boolean checkArguments(String[] arguments) {
        return true;
    }

    @Override
    public String getSyntax() {
        // TODO Auto-generated method stub
        return null;
    }
}
