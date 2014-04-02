package com.angelis.tera.game.command.admin;

import org.apache.commons.lang3.StringUtils;

import com.angelis.tera.game.models.enums.ChatTypeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_CHAT;
import com.angelis.tera.game.services.WorldService;

public class AnnounceCommand extends AbstractAdminCommand {

    @Override
    public void execute(TeraGameConnection connection, String[] arguments) {
        SM_PLAYER_CHAT packet = new SM_PLAYER_CHAT(connection.getActivePlayer(), StringUtils.join(arguments, " "), ChatTypeEnum.SYSTEM);
        for (TeraGameConnection con : WorldService.getInstance().getAllOnlineConnections()) {
            con.sendPacket(packet);
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
        // TODO Auto-generated method stub
        return null;
    }
}
