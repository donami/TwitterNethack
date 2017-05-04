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
    public char[][] loadMap() {
        List<String> content = this.getContents();

        char[][] map = new char[this.rows][cols];

        int i = 0;
        for (String line : content) {
             for (int x = 0; x < line.length(); x++) {
                char currentChar = line.charAt(x);
                map[i][x] = currentChar;

            }
            i++;
        }

        return map;
    }

    /**
     * Used for easier debugging
     * @param map   Map data
     */
    private void printMap(char[][] map) {
        for (char[] container : map) {
            for (char c : container) {
                System.out.print(c);
            }
            System.out.println("");
        }
    }
}
