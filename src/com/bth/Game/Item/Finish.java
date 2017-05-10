package com.bth.Game.Item;

import com.bth.Game.Util.Collision;

public class Finish extends Item {
    private boolean usable;

    public Finish() {
        this.usable = false;
    }

    public void use() {}

    public boolean isUsable() {
        return this.usable;
    }

    public Collision.Dialogs collide() {
        return Collision.Dialogs.FINISH_CAVE;
    }
}
