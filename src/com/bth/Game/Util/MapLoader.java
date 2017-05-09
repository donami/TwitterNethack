package com.bth.Game.Util;

import com.bth.Game.Cave.Ground;
import com.bth.Game.Cave.Rock;
import com.bth.Game.Character.Spider;
import com.bth.Game.Character.Wizard;
import com.bth.Game.Item.Potion;

import java.util.List;

public class MapLoader extends FileReader {
    private int cols;
    private int rows;
    private Entity ground;
    private Entity rock;

    public MapLoader() {
        this.cols = 10;
        this.rows = 5;
        this.ground = new Ground();
        this.rock = new Rock();
    }

    /**
     * Load the map from text file and add it to the multidimensional array
     * @return  Map data
     */
    public Entity[][] loadMap() {
        List<String> content = this.getContents();

        Entity[][] entityMap = new Entity[this.rows][this.cols];

        int i = 0;
        for (String line : content) {
             for (int x = 0; x < line.length(); x++) {
                 // Get the entity by the character on the map
                 entityMap[i][x] = this.getEntityByChar(line.charAt(x));
            }
            i++;
        }

        return entityMap;
    }

    /**
     * Get the entity by the character on the map
     * @param c Character symbol
     * @return  The entity
     */
    private Entity getEntityByChar(char c) {
        Entity entity = null;

        switch (c) {
            case 's':
                entity = new Spider();
                break;
            case '-':
                entity = this.ground;
                break;
            case '|':
                entity = this.rock;
                break;
            case 'p':
                entity = new Potion();
                break;
            case 'w':
                entity = new Wizard();
                break;
        }

        return entity;
    }
}
