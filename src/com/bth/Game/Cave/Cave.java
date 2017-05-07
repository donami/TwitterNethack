package com.bth.Game.Cave;

import com.bth.Game.Character.CharacterEntity;
import com.bth.Game.Item.Item;
import com.bth.Game.Movement;
import com.bth.Game.Util.Entity;
import com.bth.Game.Util.FileReader;
import com.bth.Game.Util.MapEntityMapper;
import com.bth.Game.Util.MapLoader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Cave {
    private int id;
    private String name;
    private String description;
    private List<Item> items;
    private List<CharacterEntity> characters;
    private String mapPath;
    private Entity[][] mapData;

    Cave(int id, String name, String mapPath) {
        this.id = id;
        this.name = name;
        this.mapPath = mapPath;
        this.mapData = null;
        this.description = null;
        this.items = new LinkedList<>();
        this.characters = new LinkedList<>();

        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {

    }

    /**
     * Read and load map data
     */
    public void loadMapData() {
        MapLoader mapLoader = new MapLoader();
        this.mapData = mapLoader.loadMap();

        for (Entity[] row: this.mapData) {
            for (Entity col : row) {
                if (col instanceof Item) {
                    this.items.add((Item) col);
                }
            }
        }
    }

    /**
     * Validate the player move
     * @param movement  The movement to validate
     * @return  True if it is possible to make the move, else false
     */
    public boolean validateMove(Movement movement, HashMap<Character, Integer> playerPos) {
        int playerX = playerPos.get('x');
        int playerY = playerPos.get('y');

        int width = mapData[playerY].length - 1;
        int height = mapData.length - 1;

        boolean moveable = false;

        switch (movement) {
            case WEST:
                moveable =  !(playerX == 0 ||
                        Rock.class.isInstance(this.getEntityAtPos(playerX - 1, playerY)));

            break;

            case EAST:
                moveable = !(playerX >= width ||
                        Rock.class.isInstance(this.getEntityAtPos(playerX + 1, playerY)));

            break;

            case SOUTH:
                moveable =  !(playerY == height ||
                        Rock.class.isInstance(this.getEntityAtPos(playerX, playerY + 1)));

            break;

            case NORTH:
                moveable = !(playerY == 0 ||
                        Rock.class.isInstance(this.getEntityAtPos(playerX, playerY - 1)));
            break;
            default:
        }

        return moveable;
    }

    /**
     * Get the entity in the cave map by coords
     * @param x     X coordinate
     * @param y     Y coordinate
     * @return  The char
     */
    public Entity getEntityAtPos(int x, int y) {
        return this.mapData[y][x];
    }


    /**
     * Remove entity from cave
     * @param x X-coordinate
     * @param y Y-coordinate
     */
    void removeEntityAtPos(int x, int y) {
        this.mapData[y][x] = new Ground();
    }

    /**
     * Getter for ID
     * @return The ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for ID
     * @param id The ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for name
     * @return  The name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name
     * @param name  The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the map path
     * @return  The map path
     */
    public String getMapPath() {
        return mapPath;
    }

    /**
     * Setter for the map path
     * @param mapPath   The map path
     */
    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
    }

    /**
     * Getter for the map data
     * @return  Map data
     */
    public Entity[][] getMapData() {
        return mapData;
    }

    /**
     * Setter for map data
     * @param mapData   Map data
     */
    public void setMapData(Entity[][] mapData) {
        this.mapData = mapData;
    }

    /**
     * Getter for description
     * @return  The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description
     * @param description   The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for items
     * @return  List of the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Setter for the items
     * @param items List of the items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Getter for the characters
     * @return  List of the characters
     */
    public List<CharacterEntity> getCharacters() {
        return characters;
    }

    /**
     * Setter for the characters
     * @param characters The characters
     */
    public void setCharacters(List<CharacterEntity> characters) {
        this.characters = characters;
    }
}
