package com.bth.Game.Util;

import com.bth.Game.Cave.Ground;
import com.bth.Game.Cave.Rock;
import com.bth.Game.Character.Spider;
import com.bth.Game.Character.Wizard;
import com.bth.Game.Item.Potion;

import java.util.HashMap;

class MapEntityMapper {
    final static HashMap<Character, Entity> entityMap = new HashMap<Character, Entity>()
    {
        {
//            put('f', "finish");
            put('-', new Ground());
            put('s', new Spider());
            put('|', new Rock());
            put('p', new Potion());
            put('w', new Wizard());
        }
    };
}
