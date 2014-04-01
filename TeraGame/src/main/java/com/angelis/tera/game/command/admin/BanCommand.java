package com.angelis.tera.game.command.admin;

import com.angelis.tera.game.models.account.Account;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.services.WorldService;

public class BanCommand extends AbstractAdminCommand {

    @Override
    public void execute(TeraGameConnection connection, String[] arguments) {
        Player targetPlayer = WorldService.getInstance().getOnlinePlayerWithName(arguments[0]);
        if (targetPlayer == null) {
            return;
        }
        
        Account targetAccount = targetPlayer.getAccount();
        if (targetAccount.getAccess() > connection.getAccount().getAccess()) {
            return;
        }
        
        // TODO
        targetAccount.getConnection().close();
    }

    @Override
    public int getAccessLevel() {
        return 1;
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
        return "Syntax: ban [playerName]";
    }
}
