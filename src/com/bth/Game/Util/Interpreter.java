package com.bth.Game.Util;

import com.bth.Game.Item.Item;
import com.bth.Game.Item.ItemHandler;
import com.bth.Game.Util.Observer.Action;

public class Interpreter {
    private ItemHandler itemHandler;

    public Interpreter(ItemHandler itemHandler) {
        this.itemHandler = itemHandler;
    }

    public Action interpretString(String string) {
        if (string.equals("")) {
            return Action.DO_NOTHING;
        }

        return this.action(string.toLowerCase().split(" "));
    }

    private Action action(String[] string) {
        if (string == null || string.length <= 0) {
            return Action.DO_NOTHING;
        }

        String s = string[0];
        Action action = Action.INVALID_COMMAND;

        for (Action a : Action.values()) {
            if (s.equals(a.getCommand())) {
                action = a;
                break;
            }
        }

        switch (action.getType()) {
            case TYPE_HASOBJECT:
                if (string.length > 1) {
                    String itemString = string[1];

                    // Get the item by name
                    Item item = this.itemHandler.getItemByCode(itemString);

                    // Make sure item exists
                    if (item == null) {
                        return Action.INVALID_COMMAND;
                    }

                    action.setObject(item);
                }
                break;
            case TYPE_UNKNOWN:
            case TYPE_DEFAULT:
                return action;
        }

        return action;
    }
}
