package com.bth.Game.Item;

import com.bth.Game.Util.Dialog;
import com.bth.Game.Util.EventInterface;
import com.bth.Game.Util.Observer.Action;

import java.util.ArrayList;

public class ItemDialog {
    private ArrayList<EventInterface> options;

    ItemDialog() {
        this.initOptions();
    }

    private void initOptions() {
        this.options = new ArrayList<>();

        this.options.add(Action.SAVE_ITEM);
        this.options.add(Action.USE_ITEM);
        this.options.add(Action.DO_NOTHING);
    }

    /**
     * Display the menu and return the option selected
     * @return  The option selected
     */
    public Action getChoice() {
        Dialog dialog = new Dialog(this.options);

        int choice = dialog.getSelection();
        return (Action) this.options.get(choice);
    }
}
