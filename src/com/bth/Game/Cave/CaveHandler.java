package com.bth.Game.Cave;

import java.util.LinkedList;
import java.util.List;

public class CaveHandler {
    private List<Cave> caves;
    private Cave currentCave;

    public CaveHandler() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.caves = new LinkedList<>();
        this.currentCave = null;
    }

    /**
     * Create a cave
     * @return The cave
     */
    public Cave createCave(int id, String name) {
        Cave cave = new Cave(id, name, "map.txt");
        this.caves.add(cave);
        return cave;
    }

    /**
     * Attempt to move the player
     * @param direction The direction to move the player
     * @return  True if move was successful, else false
     */
    public boolean movePlayer(String direction) {
        // TODO: 2017-05-04 implement
        return true;
    }

    /**
     * Set the player position
     * @param direction The direction to move the player
     * @return  The new player position
     */
    public String setPlayerPos(String direction) {
        // TODO: 2017-05-04 implement
        return direction;
    }

    /**
     * Getter for the caves
     * @return  The caves
     */
    public List<Cave> getCaves() {
        return caves;
    }

    /**
     * Setter for the caves
     * @param caves The caves
     */
    public void setCaves(List<Cave> caves) {
        this.caves = caves;
    }

    /**
     * Getter for the current cave
     * @return  The current cave
     */
    public Cave getCurrentCave() {
        return currentCave;
    }

    /**
     * Setter for the current cave
     * @param currentCave   The cave
     */
    public void setCurrentCave(Cave currentCave) {
        this.currentCave = currentCave;
    }
}
