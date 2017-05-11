package com.bth.Game.Character;

import com.bth.Game.Cave.Ground;
import com.bth.Game.Cave.Rock;
import com.bth.Game.Item.Finish;
import com.bth.Game.Item.Potion;
import com.bth.Game.Item.Sword;
import com.bth.Game.Util.Entity;

public class EntityFactory {
    public Entity getEntity(EntityType entityType) {
        if (entityType == null) {
            return null;
        }

        Entity entity = null;

        switch (entityType) {
            case SPIDER:
                entity = new Spider();
                break;
            case WIZARD:
                entity = new Wizard();
                break;
            case SWORD:
                entity = new Sword();
                break;
            case FINISH:
                entity = new Finish();
                break;
            case ROCK:
                entity = new Rock();
                break;
            case GROUND:
                entity = new Ground();
                break;
            case POTION:
                entity = new Potion();
                break;
        }

        return entity;
    }
}
