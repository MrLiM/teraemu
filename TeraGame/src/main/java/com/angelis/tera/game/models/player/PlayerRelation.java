package com.angelis.tera.game.models.player;

import com.angelis.tera.common.model.AbstractModel;

public class PlayerRelation extends AbstractModel {
    
    private Player player;
    private String note;

    public PlayerRelation(Player player) {
        super(null, null);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof PlayerRelation)) {
            return false;
        }
        
        PlayerRelation relation = (PlayerRelation) o;
        return this.player.equals(relation.getPlayer());
    }
}
