package com.angelis.tera.game.models.creature;

import com.angelis.tera.game.models.enums.CreatureTitleEnum;

public class CreatureTemplate {
    
    private CreatureTitleEnum creatureTitle;
    private int huntingZoneId;

    public CreatureTitleEnum getCreatureTitle() {
        return creatureTitle;
    }

    public void setCreatureTitle(CreatureTitleEnum creatureTitle) {
        this.creatureTitle = creatureTitle;
    }

    public int getHuntingZoneId() {
        return huntingZoneId;
    }
    
    public void setHuntingZoneId(int huntingZoneId) {
        this.huntingZoneId = huntingZoneId;
    }
}
