package com.bth.Game.Character;

import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Entity;

import java.util.concurrent.ThreadLocalRandom;


public abstract class Creature implements Entity {
    String name;
    protected int health;
    int damageMin;
    int damageMax;
    String description;
    String damageText;

    Creature() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.description = "";
        this.damageText = "";
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public int dealDamage() {
        return ThreadLocalRandom.current().nextInt(this.damageMin, this.damageMax + 1);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return this.health;
    }

    /**
     * Get string representation of the attack event
     * @return  String
     */
    public String getDamageText() {
        return this.damageText;
    }

    /**
     * Getter for description
     * @return  Creature's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return whether or not the creature is alive
     * @return  Is creature alive?
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collision.Dialogs collide() {
        return Collision.Dialogs.INTERACT_ENEMY;
    }
}
