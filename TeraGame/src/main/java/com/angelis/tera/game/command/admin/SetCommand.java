package com.angelis.tera.game.command.admin;

import com.angelis.tera.game.models.account.Account;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_STATS;
import com.angelis.tera.game.services.PlayerService;
import com.angelis.tera.game.services.WorldService;

public class SetCommand extends AbstractAdminCommand {

    private enum Commands {
        LEVEL,
        SPEED
    }

    @Override
    public void execute(TeraGameConnection connection, String[] arguments) {
        Player targetPlayer = WorldService.getInstance().getOnlinePlayerWithName(arguments[1]);
        if (targetPlayer == null) {
            return;
        }
        
        Account targetAccount = targetPlayer.getAccount();
        if (targetAccount.getAccess() > connection.getAccount().getAccess()) {
            return;
        }
        
        Commands commands = Commands.valueOf(arguments[0].toUpperCase());
        switch (commands) {
            case LEVEL:
                PlayerService.getInstance().levelUpPlayer(targetPlayer, Integer.parseInt(arguments[2]));
            break;
            
            case SPEED:
                targetPlayer.getCreatureCurrentStats().setSpeed(Integer.parseInt(arguments[2]));
                targetPlayer.getConnection().sendPacket(new SM_PLAYER_STATS(targetPlayer));
            break;
        }
    }

    @Override
    public int getAccessLevel() {
        return 1;
    }

    @Override
    public int getArgumentCount() {
        return 3;
    }

    @Override
    public boolean checkArguments(String[] arguments) {
        try {
            Commands.valueOf(arguments[0].toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getSyntax() {
        return "set {level|speed} [targetName] [value]";
    }
}
