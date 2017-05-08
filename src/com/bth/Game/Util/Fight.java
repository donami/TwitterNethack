package com.bth.Game.Util;

import com.bth.Game.Character.Creature;
import com.bth.Game.Player.Player;

import java.util.concurrent.ThreadLocalRandom;

public class Fight {
    private Player player;
    private Creature enemy;

    public Fight(Player player, Creature enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    /**
     * Attack
     */
    public void attack() {
        // Attack
        UI.write("========== FIGHT ==========");

        // Loop fight until one is dead
        while (player.isAlive() && enemy.isAlive()) {
            this.handleFight(player.attack(), enemy.dealDamage());
        }

        if (player.isAlive()) {
            UI.write("======= FIGHT ENDED =======");
        }
    }

    /**
     * Attempt to run away
     */
    public void run() {
        int random = ThreadLocalRandom.current().nextInt(1, 100);

        UI.write("You attempt to run away from the %s...", this.enemy.getName());
        this.pause(2000);

        if (random > 50) {
            UI.write("You manage to run away");
        }
        else {
            UI.write("Ahhhh... %s was to fast and caught you. You have no options rather than to fight", this.enemy.getName());
            this.attack();
        }
    }


    private void handleFight(int playerDamage, int enemyDamage) {
        UI.write("You attack the %s and deal %d", enemy.getName(), playerDamage);

        this.pause(2000);

        // Enemy takes damage
        enemy.takeDamage(playerDamage);

        if (enemy.isAlive()) {
            UI.write("%s now has %d health", enemy.getName(), enemy.getHealth());
        }
        else {
            UI.write("Phew... You killed %s", enemy.getName());
            this.pause(1500);
            return;
        }

        this.pause(1000);


        // Enemy attacks player
        UI.write(enemy.getDamageText(), enemy.getName(), enemyDamage);

        this.pause(1000);

        // Player takes damage
        player.takeDamage(enemyDamage);
        UI.write("You have %d health left", player.getHealth());

        this.pause(2000);
    }

    /**
     * Pause
     * @param time  Time to pause
     */
    private void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
