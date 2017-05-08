package com.bth.Game.Util;

import java.util.List;

public class MapLoader extends FileReader {
    private int cols;
    private int rows;

    public MapLoader() {
        this.cols = 10;
        this.rows = 5;
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
                char currentChar = line.charAt(x);
                entityMap[i][x] = MapEntityMapper.entityMap.get(currentChar);

            }
            i++;
        }

        return entityMap;
    }
}
