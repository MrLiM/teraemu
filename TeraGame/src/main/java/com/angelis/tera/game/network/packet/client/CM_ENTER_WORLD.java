package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.network.packet.server.SM_ENTER_WORLD;
import com.angelis.tera.game.network.packet.server.SM_OPCODE_LESS_PACKET;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_BIND;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_INIT;
import com.angelis.tera.game.services.PlayerService;

public class CM_ENTER_WORLD extends TeraClientPacket {

    private int playerId;
    private boolean withProlog;
    
    public CM_ENTER_WORLD(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.playerId = readD();
        this.withProlog = readC() == 1;
    }

    @Override
    protected void runImpl() {
        Player player = null;
        // TODO better
        for (Player accountPlayer : this.getConnection().getAccount().getPlayers()) {
            if (accountPlayer.getId() == this.playerId) {
                player = accountPlayer;
                break;
            }
        }
        
        if (player == null) {
            return;
        }
        
        PlayerService.getInstance().registerPlayer(this.getConnection(), player);

        this.getConnection().sendPacket(new SM_ENTER_WORLD());
        
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        this.getConnection().sendPacket(new SM_PLAYER_INIT(player));
        
        // first
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("33D111003300914B0E000080000193180000000000000001002800000006000000050000000000000000000000000000003300C50000000000C3007D0000008A8E690300000000D855040000000000160000000000000005000000000000001E0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000C5005701000000005501471F00008B8E690300000000D855040000000000190000000000000005000000000000001E0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000000000000000005701E90100000000E701EC0300003ED3880300000000D8550400000000001A000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000E9017B02000000007902284500002DEF880300000000D85504000000000003000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000300000000000000000000000000000000000000000000000000000000007B020D03000000000B037B300000A5EF880300000000D85504000000000001000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000900000000000000000000000000000000000000000000000000000000000D039F03000000009D03A017000078F6880300000000D85504000000000017000000000000000500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000000000000000009F033104000000002F042E1C00005B10890300000000D85504000000000015000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000000000000000003104C30400000000C104EA0300000711890300000000D8550400000000001B000000000000000700000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000C3045505000000005305294500006515890300000000D85504000000000004000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000500000000000000000000000000000000000000000000000000000000005505E70500000000E505BC1B00007A1B890300000000D85504000000000014000000000000000200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000E70579060000000077063E8B02002727890300000000D8550400000000001D000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000000000000000000000000000000000000000000000000000000000000000000000000079060B07000000000907E9030000C34E920300000000D8550400000000001C000000000000000A00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000B079D07000000009B07820000004763920300000000D85504000000000018000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000000000000000009D072F08000000002D08334500001D75920300000000D85504000000000005000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000900000000000000000000000000000000000000000000000000000000002F08C10800000000BF08B0170000C582920300000000D8550400000000001E000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000C108530900000000510931450000E185920300000000D85504000000000020000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000900000000000000000000000000000000000000000000000000000000005309000000000000E309EB030000FBE6170900000000D85504000000000021000000000000000200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("C4F30900080008001200D8270000010012001C00844E000001001C00260094750000010026003000A49C0000010030003A00C4A2890001003A00440012270000000044004E009C4A000000004E0058009D4A00000000580000009E4A00000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("8C8600000000"));
        
        // QUEST
        //this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("428901000E000C00000100000E00000001004500010061006E050000EEF37601030000000100000000000000010000000001010000000000000000000000000000000045000000C7EE4F47D8DD87C768DCB0C5D5000000750400000D000000610000000A000000"));
        //this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("428901000E000C00000100000E00000000000000010045001A0500004EF4760105000000010000000000000001000000000101000000000000000000000000000000004500000000000000"));
        //this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("428901000E000C00000100000E00000001004500010061001F050000851EB503020000000100000000000000010000000001010300000000000000000000000000000045000000D2D18247A7FE92C700B63AC540000000090400000D0000006100000000000000"));
        //this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("428901000E000C00000100000E00000001004500010061003005000025861C04010000000100000000000000010000000000010200000000000000000000000000000045000000A1648747F9FD9AC7E07820C540000000120400000D0000006100000000000000"));
        
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("937100000A0000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("E676040008000800100015050000100018001705000018002000180500002000000019050000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("29FA070019000000000000000000009A0100000E00000019002900010000000100000001000000290039000200000002000000010000003900490003000000030000000100000049005900040000000400000001000000590069000500000005000000010000006900790006000000060000000100000079000000070000000700000001000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("3FF8000000000001"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("CE6400000000914B0E0000800001"));
        // CE6400000000914B0E0000800001
        // CE6400000000C44C0E0000800001
        // CE6400000000ED3E0E0000800000
        // CE6400000000ED3E0E0000800000
        
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("1FF60500160001000000000000003C000000000016002200000000000000000022002E00FFFFFFFF000000002E003A00FFFFFFFF000000003A004600FFFFFFFF0000000046000000FFFFFFFF00000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("77E2"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("C7BE0000000000000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("027700004843"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("85CA06000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("67BB01001400050000000000803F0000803F1400000001000000"));
        
        // System message
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("A3AD0600400032003800310030000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("A6E7000000000000000000000000000000000000000000000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("3FB2C44C0E00008000017B3000002845000029450000334500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("8BB30200080008001000040000001000000005000000"));
        
        this.getConnection().sendPacket(new SM_PLAYER_BIND(player));
        
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("129900000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("7656010009000009000000B2010000CCC3D82C00000000FFFFFF7F0000000000000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("107ECD1B0000333C275300000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("43B400000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("4D9C0000000000000000"));
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("D65700000000C44C0E0000800001"));
        
        // second
        this.getConnection().sendPacket(new SM_OPCODE_LESS_PACKET("33D111003300C44C0E000080000193180000000000000001002800000006000000050000000000000000000000000000003300C50000000000C3007D0000008A8E690300000000D855040000000000160000000000000005000000000000001E0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000C5005701000000005501471F00008B8E690300000000D855040000000000190000000000000005000000000000001E0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000000000000000005701E90100000000E701EC0300003ED3880300000000D8550400000000001A000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000E9017B02000000007902284500002DEF880300000000D85504000000000003000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000300000000000000000000000000000000000000000000000000000000007B020D03000000000B037B300000A5EF880300000000D85504000000000001000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000900000000000000000000000000000000000000000000000000000000000D039F03000000009D03A017000078F6880300000000D85504000000000017000000000000000500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000000000000000009F033104000000002F042E1C00005B10890300000000D85504000000000015000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000000000000000003104C30400000000C104EA0300000711890300000000D8550400000000001B000000000000000700000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000C3045505000000005305294500006515890300000000D85504000000000004000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000500000000000000000000000000000000000000000000000000000000005505E70500000000E505BC1B00007A1B890300000000D85504000000000014000000000000000200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000E70579060000000077063E8B02002727890300000000D8550400000000001D000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000000000000000000000000000000000000000000000000000000000000000000000000079060B07000000000907E9030000C34E920300000000D8550400000000001C000000000000000A00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000B079D07000000009B07820000004763920300000000D85504000000000018000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000000000000000000000000000000000000000000000009D072F08000000002D08334500001D75920300000000D85504000000000005000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000900000000000000000000000000000000000000000000000000000000002F08C10800000000BF08B0170000C582920300000000D8550400000000001E000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000C108530900000000510931450000E185920300000000D85504000000000020000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000900000000000000000000000000000000000000000000000000000000005309000000000000E309EB030000FBE6170900000000D85504000000000021000000000000000200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000000000000000000000000000000000000000000000000000000000000"));
    }
}