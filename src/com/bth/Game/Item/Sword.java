package com.bth.Game.Item;

import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Observer.Action;

public class Sword extends Item {
    private boolean usable;
    private int damage;

    public Sword() {
        this.name = "Sword";
        this.damage = 20;
        this.description = "Equipping the sword will increase you damage by " + this.damage;
    }

    public Collision.Dialogs collide() {
        return Collision.Dialogs.ITEM_SAVE_DIALOG;
    }

    public void use() {
        this.notifyObservers(Action.INCREASE_DAMAGE, this.damage);
    }

    public boolean isUsable() {
        return this.usable;
    }
}
