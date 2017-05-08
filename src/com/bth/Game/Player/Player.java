package com.bth.Game.Player;

import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Constants;
import com.bth.Game.Util.Entity;
import com.bth.Game.Util.UI;

public class Player implements Entity {
    private int health;
    private Backpack backpack;

    public Player() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.health = 100;
        this.backpack = new Backpack(this);
    }

    /**
     * Player dies
     */
    public void die() {
        this.health = 0;

        UI.write(Constants.PLAYER_DEAD.getText());
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

    void increaseHealth(int health) {
        UI.write(Constants.HEALTH_INCREASED_BY.getText(), health);
        this.setHealth(this.health + health);
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
