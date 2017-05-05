package com.bth.Game.Util;

import com.bth.Game.Cave.Ground;
import com.bth.Game.Cave.Rock;
import com.bth.Game.Item.Potion;

import java.util.HashMap;

public class MapEntityMapper {
    final static HashMap<Character, Entity> entityMap = new HashMap<Character, Entity>()
    {
        {
//            put('e', "enemy");
//            put('f', "finish");
            put('-', new Ground());
            put('|', new Rock());
            put('p', new Potion());
        }
    };
}
