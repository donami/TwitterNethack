package com.bth.Game.Player;

public class Player {
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
        this.backpack = new Backpack();
    }

    /**
     * Player dies
     */
    public void die() {
        this.health = 0;

        System.out.println("\tUh-oh! You are now dead.");
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
    public boolean openBackpack() {
        return this.backpack.setOpen(true);
    }
}
