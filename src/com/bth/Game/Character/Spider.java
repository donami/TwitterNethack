package com.bth.Game.Character;

public class Spider extends Creature {
    public Spider() {
        this.name = "Spider";
        this.health = 80;
        this.damageMin = 30;
        this.damageMax = 40;
        this.damageText.add("%s bites you and deals %d damage");
        this.damageText.add("Ouuuch! %s scratch you and deals %d damage");
    }
}
