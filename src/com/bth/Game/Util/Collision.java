package com.bth.Game.Util;

public class Collision {
    public Collision() {

    }

    public enum Dialogs {
        ITEM_SAVE_DIALOG,
        DO_NOTHING
    }

    /**
     * Handle collision with entity
     * @param other The entity
     */
    public Dialogs isColliding(Entity other) {
        return other.collide();
    }

}
