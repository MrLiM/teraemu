package com.angelis.tera.game.models.player.request;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.player.request.enums.RequestTypeEnum;
import com.angelis.tera.game.network.SystemMessages;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_REQUEST_WAIT_WINDOW;

public class PartyInviteRequest extends Request {

    private final Player invited;
    
    public PartyInviteRequest(Player initiator, Player invited) {
        super(initiator, RequestTypeEnum.PARTY_INVITE);
        this.invited = invited;
    }
    
    @Override
    public boolean check() {
        final TeraGameConnection connection = this.initiator.getConnection();
        if (this.invited == null) {
            connection.sendPacket(SystemMessages.REQUEST_PLAYER_NOT_FOUND());
            return false;
        }
        
        if (this.invited.equals(this.initiator)) {
            connection.sendPacket(SystemMessages.REQUEST_CHOOSE_ANOTHER_PLAYER());
            return false;
        }
        
        return true;
    }

    @Override
    public void onAction() {
        this.initiator.getConnection().sendPacket(new SM_PLAYER_REQUEST_WAIT_WINDOW(this));
    }

    @Override
    public void onAccept() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onDecline() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onCancel() {
        // TODO Auto-generated method stub
    }

    public Player getInvited() {
        return invited;
    }
}
