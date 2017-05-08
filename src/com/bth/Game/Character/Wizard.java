package com.bth.Game.Character;

import com.bth.Game.Util.Collision;

public class Wizard extends Creature {
    public Wizard() {
        this.name = "Wizard";
        this.health = 120;
        this.damageMin = 20;
        this.damageMax = 40;
        this.description = String.format(
                "============ Wizard =========\n" +
                "\tName:\t\t %s\n" +
                "\tHealth:\t\t %d\n" +
                "\tMin damage:\t %d\n" +
                "\tMax damage:\t %d\n" +
                "\t=============================", this.name, this.health, this.damageMin, this.damageMax);
        this.damageText = "%s throws a fireball at you and deals %d damage";
    }

    public Collision.Dialogs collide() {
        return Collision.Dialogs.INTERACT_ENEMY;
    }
}
