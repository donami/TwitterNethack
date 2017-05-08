package com.bth.Game.Player;

import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Entity;
import com.bth.Game.Util.Printer;

public class Player implements Entity {
    private int health;
    private Backpack backpack;
    private Printer printer;

    public Player() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.health = 100;
        this.backpack = new Backpack(this);
        this.printer = new Printer();
    }

    /**
     * Player dies
     */
    public void die() {
        this.health = 0;

        this.printer.println("\tUh-oh! You are now dead.");
    }

    /**
     * Get the player's backpack
     * @return  Player's backpack
     */
    public Backpack getBackpack() {
        return this.backpack;
    }

    /**
     * Set the health
     * @param health    health
     * @return  Current health
     */
    public int setHealth(int health) {
        this.health = health;
        return this.health;
    }

    /**
     * Getter for the health
     * @return  The player's health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Open the backpack
     * @return  True if backpack is open, else false
     */
    public Backpack openBackpack() {
        return this.backpack.setOpen(true);
    }

    public Collision.Dialogs collide() {
        return Collision.Dialogs.DO_NOTHING;
    }
}
