package com.angelis.tera.game.command.admin;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.ClientPacketHandler;
import com.angelis.tera.game.network.packet.ServerPacketHandler;
import com.angelis.tera.game.services.XMLService;

public class ReloadCommand extends AbstractAdminCommand {

    @Override
    public void execute(TeraGameConnection connection, String[] arguments) {
        switch (arguments[0]) {
            case "network":
                ServerPacketHandler.init();
                ClientPacketHandler.init();
            break;
            
            case "xml":
                XMLService.getInstance().restart();
            break;
        }
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
        return "Syntax: !reload {network}";
    }
}
