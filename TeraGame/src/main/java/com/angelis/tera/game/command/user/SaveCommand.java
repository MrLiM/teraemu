package com.angelis.tera.game.command.user;

import java.util.EnumSet;

import com.angelis.tera.game.delegate.PlayerDelegate;
import com.angelis.tera.game.models.account.enums.AccountTypeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;

public class SaveCommand extends AbstractUserCommand {

    @Override
    public void execute(TeraGameConnection connection, String[] arguments) {
        PlayerDelegate.updatePlayerModel(connection.getActivePlayer());
    }

    @Override
    public EnumSet<? extends AccountTypeEnum> getAllowedAccountTypes() {
        return EnumSet.allOf(AccountTypeEnum.class);
    }
    
    @Override
    public int getArgumentCount() {
        return 0;
    }

    @Override
    public boolean checkArguments(String[] arguments) {
        return true;
    }

    @Override
    public String getSyntax() {
        return null;
    }
}
