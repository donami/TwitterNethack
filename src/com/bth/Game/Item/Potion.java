package com.bth.Game.Item;

import com.bth.Game.Util.Collision;

public class Potion extends Item {
    private int health;

    public Potion() {
        this.health = 50;
        this.name = "Potion";
        this.description = "Use this to increase your health by " + this.health;
    }

    public void use() {

    }

    public Collision.Dialogs collide() {
        System.out.println("You found a potion!");

        return Collision.Dialogs.ITEM_SAVE_DIALOG;
    }
}
