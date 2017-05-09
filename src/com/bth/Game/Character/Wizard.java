package com.bth.Game.Character;

public class Wizard extends Creature {
    public Wizard() {
        this.name = "Wizard";
        this.health = 120;
        this.damageMin = 20;
        this.damageMax = 40;
        this.damageText.add("%s throws a fireball at you and deals %d damage");
        this.damageText.add("%s hits you with his staff deals %d damage");
    }
}
