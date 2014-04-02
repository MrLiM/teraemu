package com.angelis.tera.game.models.player.request;

import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.game.models.enums.ObjectFamilyEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.player.request.enums.RequestTypeEnum;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_REQUEST_ALLOWED;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_REQUEST_WAIT_WINDOW;
import com.angelis.tera.game.services.ObjectIDService;

public abstract class Request extends AbstractModel {
    
    protected final Player initiator;
    protected final RequestTypeEnum requestType;
    private boolean canceled;

    public Request(final Player initiator, final RequestTypeEnum requestType) {
        super(null, ObjectIDService.getInstance().generateId(ObjectFamilyEnum.fromClass(Request.class)));
        this.initiator = initiator;
        this.requestType = requestType;
    }

    public final Player getInitiator() {
        return initiator;
    }

    public final RequestTypeEnum getRequestType() {
        return requestType;
    }

    public final void doAction() {
        this.initiator.getConnection().sendPacket(new SM_PLAYER_REQUEST_ALLOWED(this.requestType));
        
        if (this.check()) {
            this.initiator.getConnection().sendPacket(new SM_PLAYER_REQUEST_WAIT_WINDOW(this));
            this.initiator.getController().setRequest(this);
            
            this.onAction();
        }
    }
    
    public final void doCancel() {
        this.canceled = true;
        this.onCancel();
    }
    
    public final boolean isCanceled() {
        return this.canceled;
    }
    
    public abstract boolean check();
    public abstract void onAction();
    public abstract void onAccept();
    public abstract void onDecline();
    public abstract void onCancel();
}
