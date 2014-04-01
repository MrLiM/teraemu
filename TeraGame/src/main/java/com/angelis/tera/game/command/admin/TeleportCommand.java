package com.angelis.tera.game.command.admin;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_TELEPORT;
import com.angelis.tera.game.services.WorldService;

public class TeleportCommand extends AbstractAdminCommand {

    @Override
    public void execute(TeraGameConnection connection, String[] arguments) {
        Player targetPlayer = WorldService.getInstance().getOnlinePlayerWithName(arguments[0]);
        if (targetPlayer == null) {
            return;
        }
        
        targetPlayer.getWorldPosition().setXYZ(Float.parseFloat(arguments[0]), Float.parseFloat(arguments[1]), Float.parseFloat(arguments[2]));
        targetPlayer.getConnection().sendPacket(new SM_PLAYER_TELEPORT(targetPlayer));
    }

    @Override
    public int getAccessLevel() {
        return 1;
    }

    @Override
    public int getArgumentCount() {
        return 4;
    }
    
    @Override
    public boolean checkArguments(String[] arguments) {
        try {
            Float.parseFloat(arguments[1]);
            Float.parseFloat(arguments[2]);
            Float.parseFloat(arguments[3]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getSyntax() {
        // TODO Auto-generated method stub
        return null;
    }
}
