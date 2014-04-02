package com.angelis.tera.game.command.admin;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.server.SM_OPCODE_LESS_PACKET;

public class SendFakePacketCommand extends AbstractAdminCommand {

    @Override
    public void execute(TeraGameConnection connection, String[] arguments) {
        // connection.sendPacket(new
        // SM_PLAYER_CHAT(connection.getActivePlayer(), "@social:123456",
        // ChatTypeEnum.SOCIAL));
        connection.sendPacket(new SM_OPCODE_LESS_PACKET("067D0100410000000000AFA4000000800C0001000000F8030000D50000000000000001000000020000000000000000010000000000000000000000FFFFFFFF410000004F00010000002400000040006E00700063003A00310032000000"));
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
