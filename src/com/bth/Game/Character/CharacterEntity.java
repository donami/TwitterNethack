package com.bth.Game.Character;

import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Entity;


public class CharacterEntity implements Entity {
    CharacterEntity() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {

    }

    public Collision.Dialogs collide() {
        return Collision.Dialogs.DO_NOTHING;
    }
}
