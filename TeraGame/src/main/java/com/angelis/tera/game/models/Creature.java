package com.angelis.tera.game.models;


public abstract class Creature extends TeraObject {
    
    private CreatureBaseStats creatureBaseStats;
    private CreatureCurrentStats creatureCurrentStats;
    private int level;
    
    private Creature target;
    
    public Creature(Integer id, int uid) {
        super(id, uid);
    }

    public Creature(int uid) {
        super(uid);
    }

    public CreatureBaseStats getBaseStats() {
        return creatureBaseStats;
    }

    public void setBaseStats(CreatureBaseStats baseStats) {
        this.creatureBaseStats = baseStats;
    }

    public CreatureCurrentStats getCreatureCurrentStats() {
        return creatureCurrentStats;
    }

    public void setCreatureCurrentStats(CreatureCurrentStats currentStats) {
        this.creatureCurrentStats = currentStats;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Creature getTarget() {
        return target;
    }

    public void setTarget(Creature target) {
        this.target = target;
    }
}
