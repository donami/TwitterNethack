package com.bth.Game.Item;

import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Observer.Action;

import java.util.concurrent.ThreadLocalRandom;

public class Potion extends Item {
    private int health;

    public Potion() {
        this.health = this.randomHealth(1, 50);
        this.name = "Potion";
        this.description = "Use this to increase your health by " + this.health;
    }

    public void use() {
        this.notifyObservers(Action.INCREASE_HEALTH, this.health);
    }

    public Collision.Dialogs collide() {
        System.out.println("You found a potion!");

        return Collision.Dialogs.ITEM_SAVE_DIALOG;
    }

    /**
     * Get random health by min and max values
     * @param min   Min value
     * @param max   Max value
     * @return  Random number
     */
    private int randomHealth(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
