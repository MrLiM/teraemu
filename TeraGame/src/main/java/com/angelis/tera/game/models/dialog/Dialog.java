package com.angelis.tera.game.models.dialog;

import java.util.List;

import javolution.util.FastList;

import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.common.utils.Rnd;
import com.angelis.tera.game.models.TeraCreature;
import com.angelis.tera.game.models.enums.EmotionEnum;
import com.angelis.tera.game.models.enums.ObjectFamilyEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.quest.Quest;
import com.angelis.tera.game.network.packet.server.SM_CREATURE_EMOTE;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_DIALOG_SHOW;
import com.angelis.tera.game.services.ObjectIDService;
import com.angelis.tera.game.services.VisibleService;

public class Dialog extends AbstractModel {
    
    private final Player player;
    private final TeraCreature teraCreature;
    private final List<DialogButton> buttons;
    private final Quest quest;
    private final int special1;

    public Dialog(Player player, TeraCreature teraCreature, Quest quest, int special1) {
        super(null, ObjectIDService.getInstance().generateId(ObjectFamilyEnum.fromClass(Dialog.class)));
        this.player = player;
        this.teraCreature = teraCreature;
        this.quest = quest;
        this.special1 = special1;
        this.buttons = new FastList<>();
    }
    
    public final void progress(int choice) {
        if (choice > this.buttons.size()) {
            return;
        }
        
        DialogButton button = this.buttons.get(choice-1);
        String command = button.getText().substring(0, button.getText().indexOf(":"));
        switch (command) {
            // TODO compute new button, page ...
            case "@npc":
                
            break;
            
            case "@quest":
                
            break;
        }
    }

    public void send() {
        player.getConnection().sendPacket(new SM_PLAYER_DIALOG_SHOW(this));
        VisibleService.getInstance().sendPacketForVisible(player, new SM_CREATURE_EMOTE(this.teraCreature, player, EmotionEnum.fromValue(Rnd.get(1, 2, false)), false));
    }

    public Player getPlayer() {
        return player;
    }

    public TeraCreature getTeraCreature() {
        return teraCreature;
    }

    public List<DialogButton> getButtons() {
        return buttons;
    }
    
    public Quest getQuest() {
        return this.quest;
    }

    public int getSpecial1() {
        return this.special1;
    }
}
