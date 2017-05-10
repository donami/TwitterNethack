package com.bth.Game.Character;

import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Creature implements Entity {
    String name;
    protected int health;
    int damageMin;
    int damageMax;
    private String description;
    List<String> damageText;
    private Random rand = new Random();

    Creature() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.description = "";
        this.damageText = new ArrayList<>();
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
     * Get random string representation of the attack event
     * @return  String
     */
    public String getDamageText() {
        if (this.damageText.size() <= 0) {
            return "";
        }

        // Randomize the attack text
        return this.damageText.get(rand.nextInt(this.damageText.size()));
    }

    /**
     * Increase the creatures damage
     * @param damage    Damage
     */
    void increaseDamage(int damage) {
        this.damageMin += damage;
        this.damageMax += damage;
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

    /**
     * Information text about the creature
     * @return  String
     */
    public String getInfo() {
        return String.format(
            "============ %s =========\n" +
            "\tName:\t\t %s\n" +
            "\tHealth:\t\t %d\n" +
            "\tMin damage:\t %d\n" +
            "\tMax damage:\t %d\n" +
            "\t=============================", this.name, this.name, this.health, this.damageMin, this.damageMax);
    }
}
