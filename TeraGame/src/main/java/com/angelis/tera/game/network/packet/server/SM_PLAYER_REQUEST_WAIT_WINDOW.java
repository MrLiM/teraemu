package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.request.PartyInviteRequest;
import com.angelis.tera.game.models.player.request.Request;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_PLAYER_REQUEST_WAIT_WINDOW extends TeraServerPacket {

    private final Request request;
    
    public SM_PLAYER_REQUEST_WAIT_WINDOW(Request request) {
        this.request = request;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeH(byteBuffer, 0); // owner name start shift
        writeH(byteBuffer, 0); // owner name end shift
        writeH(byteBuffer, 0); // target name start shift
        writeH(byteBuffer, 12); // ??? position ?
        
        writeUid(byteBuffer, this.request.getInitiator());
        writeQ(byteBuffer, 0);
        
        writeD(byteBuffer, this.request.getRequestType().value);
        
        writeD(byteBuffer, this.request.getUid());
        writeQ(byteBuffer, 0);

        this.writeBufferPosition(byteBuffer, 4, Size.H);
        writeS(byteBuffer, this.request.getInitiator().getName());

        this.writeBufferPosition(byteBuffer, 6, Size.H);
        switch (this.request.getRequestType()) {
            case PARTY_INVITE:
                writeS(byteBuffer, ((PartyInviteRequest) this.request).getInvited().getName());
            break;
            
            default:
                // TODO
                /*if(Request is GuildCreate)
                    writeS(byteBuffer, ((GuildCreate)Request).GuildName);
                else if(Request is GuildInvite)
                    writeS(byteBuffer, Request.Owner.Guild != null ? Request.Owner.Guild.GuildName : "Unknown guild");*/
        }
        
        writeH(byteBuffer, 0);
    }
}
