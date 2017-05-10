package com.bth.Game.Util;

import com.bth.Game.Cave.Ground;
import com.bth.Game.Cave.Rock;
import com.bth.Game.Character.Spider;
import com.bth.Game.Character.Wizard;
import com.bth.Game.Item.Finish;
import com.bth.Game.Item.Potion;
import com.bth.Game.Item.Sword;

import java.util.LinkedList;
import java.util.List;

public class MapLoader extends FileReader {
    private int cols;
    private int rows;
    private Entity ground;
    private Entity rock;
    private String name;

    public MapLoader() {
        this.cols = 20;
        this.rows = 10;
        this.ground = new Ground();
        this.rock = new Rock();
        this.name = "Cave";
    }

    /**
     * Load the map from text file and add it to the multidimensional array
     * @return  Map data
     */
    public Entity[][] loadMap(String file) {
        String filename = file.concat(".txt");

        List<String> content = this.getContents("maps/" + filename);

        this.name = content.remove(0);

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

    public String getName() {
        return name;
    }

    /**
     * Get the entity by the character on the map
     * @param c Character symbol
     * @return  The entity
     */
    private Entity getEntityByChar(char c) {
        Entity entity = null;

        switch (c) {
            case 'f':
                entity = new Finish();
                break;
            case 's':
                entity = new Spider();
                break;
            case '-':
                entity = this.ground;
                break;
            case '|':
            case '_':
                entity = this.rock;
                break;
            case 'p':
                entity = new Potion();
                break;
            case 'w':
                entity = new Wizard();
                break;
            case '^':
                entity = new Sword();
                break;
        }

        return entity;
    }
}
