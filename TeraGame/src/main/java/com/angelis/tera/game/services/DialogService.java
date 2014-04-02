package com.angelis.tera.game.services;

import java.util.Map;

import javolution.util.FastMap;

import org.apache.log4j.Logger;

import com.angelis.tera.game.models.TeraCreature;
import com.angelis.tera.game.models.creature.CreatureTemplate;
import com.angelis.tera.game.models.dialog.Dialog;
import com.angelis.tera.game.models.dialog.DialogButton;
import com.angelis.tera.game.models.dialog.enums.DialogIconEnum;
import com.angelis.tera.game.models.dialog.enums.DialogStringEnum;
import com.angelis.tera.game.models.enums.CreatureTitleEnum;
import com.angelis.tera.game.models.player.Player;

public class DialogService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(DialogService.class.getName());
    
    /** INSTANCE */
    private static DialogService instance;
    
    private Map<Integer, Dialog> dialogs = new FastMap<>();
    
    @Override
    public void onInit() {
        log.info("DialogService started");
    }

    @Override
    public void onDestroy() {
    }
    
    public void onPlayerTalk(Player player, int creatureUId, int objectFamilly) {
        TeraCreature creature = SpawnService.getInstance().getCreatureByUid(creatureUId);
        if (creature == null) {
            return;
        }
        
        final CreatureTemplate creatureTemplate = creature.getCreatureTemplate();
        final Dialog dialog = new Dialog(player, creature, null, creatureTemplate.getHuntingZoneId());

        // TODO creature template
        final CreatureTitleEnum creatureTitle = creatureTemplate.getCreatureTitle();
        if (creatureTitle != null) {
            switch (creatureTitle) {
                case FLIGHT_MASTER:
                    dialog.getButtons().add(new DialogButton(DialogIconEnum.CENTERED_GRAY, DialogStringEnum.FLIGHTPOINTS));
                break;
                
                case BANK:
                    dialog.getButtons().add(new DialogButton(DialogIconEnum.CENTERED_GRAY, DialogStringEnum.BANK));
                break;
            }
        }
        
        dialog.send();
        dialogs.clear();
        //dialogs.put(dialog.getUid(), dialog);
    }
    
    public void onPlayerTalkProgress(Player player, int dialogUid, int choice) {
        Dialog dialog = dialogs.get(dialogUid);
        if (dialog == null) {
            return;
        }
        
        dialog.progress(choice);
    }

    /** SINGLETON */
    public static DialogService getInstance() {
        if (instance == null) {
            instance = new DialogService();
        }
        return instance;
    }

    
}
