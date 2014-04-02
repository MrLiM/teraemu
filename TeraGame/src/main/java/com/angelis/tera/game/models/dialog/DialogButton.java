package com.angelis.tera.game.models.dialog;

import com.angelis.tera.game.models.dialog.enums.DialogIconEnum;
import com.angelis.tera.game.models.dialog.enums.DialogQuestEnum;
import com.angelis.tera.game.models.dialog.enums.DialogStringEnum;

public class DialogButton {
    public final DialogIconEnum dialogIcon;
    public final String text;
    
    public DialogButton(DialogIconEnum dialogIcon, String text) {
        this.dialogIcon = dialogIcon;
        this.text = text;
    }
    
    public DialogButton(DialogIconEnum dialogIcon, DialogStringEnum dialogString) {
        this.dialogIcon = dialogIcon;
        this.text = "@npc:"+dialogString.value;
    }

    public DialogButton(DialogIconEnum dialogIcon, DialogQuestEnum dialogQuest) {
        this.dialogIcon = dialogIcon;
        this.text = "@quest:"+dialogQuest.value;
    }

    public DialogIconEnum getDialogIcon() {
        return dialogIcon;
    }

    public String getText() {
        return text;
    }
}
