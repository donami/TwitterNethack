package com.bth.Game.Util;


import com.bth.Game.Character.EntityFactory;
import com.bth.Game.Character.EntityType;

import java.util.List;

public class MapLoader extends FileReader {
    private int cols;
    private int rows;
    private Entity ground;
    private Entity rock;
    private String name;
    private EntityFactory entityFactory;

    public MapLoader() {
        this.cols = 20;
        this.rows = 10;
        this.entityFactory = new EntityFactory();
        this.ground = this.entityFactory.getEntity(EntityType.GROUND);
        this.rock = this.entityFactory.getEntity(EntityType.ROCK);
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
                entity = this.entityFactory.getEntity(EntityType.FINISH);
                break;
            case 's':
                entity = this.entityFactory.getEntity(EntityType.SPIDER);
                break;
            case '-':
                entity = this.ground;
                break;
            case '|':
            case '_':
                entity = this.rock;
                break;
            case 'p':
                entity = this.entityFactory.getEntity(EntityType.POTION);
                break;
            case 'w':
                entity = this.entityFactory.getEntity(EntityType.WIZARD);
                break;
            case '^':
                entity = this.entityFactory.getEntity(EntityType.SWORD);
                break;
        }

        return entity;
    }
}
