package com.bth.Game.Cave;

import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Entity;

public class Rock implements Entity {
    public Collision.Dialogs collide() {
        return Collision.Dialogs.DO_NOTHING;
    }
}
