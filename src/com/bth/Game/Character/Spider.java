package com.bth.Game.Character;

import com.bth.Game.Util.Collision;

public class Spider extends Creature {
    public Spider() {
        this.name = "Spider";
        this.health = 80;
        this.damageMin = 30;
        this.damageMax = 40;
        this.description = String.format(
                "============ %s =========\n" +
                        "\tName:\t\t %s\n" +
                        "\tHealth:\t\t %d\n" +
                        "\tMin damage:\t %d\n" +
                        "\tMax damage:\t %d\n" +
                        "\t=============================", this.name, this.name, this.health, this.damageMin, this.damageMax);
        this.damageText = "%s bites you and deals %d damage";
    }

    public Collision.Dialogs collide() {
        return Collision.Dialogs.INTERACT_ENEMY;
    }
}
