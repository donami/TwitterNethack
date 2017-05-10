package com.bth.Game.Cave;

import com.bth.Game.Movement;

import java.util.*;

public class CaveHandler {
    private Queue<Cave> caves;

    public CaveHandler() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.caves = new LinkedList<>();
    }

    /**
     * Create a cave
     * @param id Cave's ID
     * @param map Cave's map file
     * @return The cave
     */
    public Cave createCave(int id, String map) {
        Cave cave = new Cave(id, "Cave", map);
        this.caves.add(cave);
        return cave;
    }

    /**
     * Return a list of possible moves
     * @param cave  The cave
     * @param position  The current position
     * @return  List of possible moves
     */
    public ArrayList<String> getPossibleMoves(Cave cave, HashMap<Character, Integer> position) {
        ArrayList<String> possibleMoves = new ArrayList<>();

        if (cave.validateMove(Movement.NORTH, position))
            possibleMoves.add("north");

        if (cave.validateMove(Movement.SOUTH, position))
            possibleMoves.add("south");

        if (cave.validateMove(Movement.EAST, position))
            possibleMoves.add("east");

        if (cave.validateMove(Movement.WEST, position))
            possibleMoves.add("west");

        return possibleMoves;
    }

    /**
     * Remove entity from cave
     * @param cave  The cave to modify
     * @param x X-coordinate
     * @param y Y-coordinate
     */
    public void removeEntityFromCave(Cave cave, int x, int y) {
        cave.removeEntityAtPos(x, y);
    }

    /**
     * Get the next cave in the queue
     * @return Next cave
     */
    public Cave getNextCave() {
        return this.caves.poll();
    }

    /**
     * Getter for the caves
     * @return  The caves
     */
    public Queue<Cave> getCaves() {
        return caves;
    }
}
