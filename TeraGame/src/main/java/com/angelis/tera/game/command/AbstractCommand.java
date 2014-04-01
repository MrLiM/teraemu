package com.angelis.tera.game.command;

import com.angelis.tera.game.network.connection.TeraGameConnection;

public abstract class AbstractCommand {
    public abstract void execute(TeraGameConnection connection, String[] arguments);
    public abstract int getArgumentCount();
    public abstract boolean checkArguments(String[] arguments);
    public abstract String getSyntax();
}
