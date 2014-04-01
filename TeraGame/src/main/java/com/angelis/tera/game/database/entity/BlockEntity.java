package com.angelis.tera.game.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.angelis.tera.common.database.entity.AbstractDatabaseEntity;

@Entity
@Table(name = "player_blockeds")
public class BlockEntity extends AbstractDatabaseEntity {

    private static final long serialVersionUID = 6582202985711800117L;

    @Column
    private String note;
    
    @OneToOne
    private PlayerEntity owner;

    @OneToOne
    private PlayerEntity target;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PlayerEntity getOwner() {
        return owner;
    }

    public void setOwner(PlayerEntity owner) {
        this.owner = owner;
    }

    public PlayerEntity getTarget() {
        return target;
    }

    public void setTarget(PlayerEntity target) {
        this.target = target;
    }
}
