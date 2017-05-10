package com.bth.Game.Player;

import com.bth.Game.Game;
import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Constants;
import com.bth.Game.Util.Entity;
import com.bth.Game.Util.UI;

import java.util.concurrent.ThreadLocalRandom;

public class Player implements Entity {
    private int health;
    private Backpack backpack;
    private int minDamage;
    private int maxDamage;

    public Player() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.health = 100;
        this.backpack = new Backpack(this);
        this.minDamage = 30;
        this.maxDamage = 75;
    }

    /**
     * Return whether or not the player is alive
     * @return  Is player alive?
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    /**
     * Player dies
     */
    public void die() {
        this.health = 0;

        UI.write(Constants.PLAYER_DEAD.getText());
        Game.pause(1000);
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

        if (this.health <= 0) {
            this.die();
        }

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
     * Increase the player's damage
     * @param damage    Damage
     */
    void increaseDamage(int damage) {
        UI.write(Constants.DAMAGE_INCREASED_BY.getText(), damage);
        this.minDamage += damage;
        this.maxDamage += damage;
    }

    /**
     * Player takes damage
     * @param damage    The amount of damage
     */
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public int attack() {
        return ThreadLocalRandom.current().nextInt(this.minDamage, this.maxDamage + 1);
    }

    /**
     * Open the backpack
     * @return  True if backpack is open, else false
     */
    public Backpack openBackpack() {
        return this.backpack.setOpen(true);
    }

    public String[] stats() {
        String health = String.format("%d/%d", this.health, 100);
        String damage = String.format("%d-%d", this.minDamage, this.maxDamage);

        return new String[]{
            "=========STATS==========",
            " Health:\t\t" + health,
            " Damage:\t\t" + damage,
            "========================",
        };
    }


    public Collision.Dialogs collide() {
        return Collision.Dialogs.DO_NOTHING;
    }
}
