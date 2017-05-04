package com.bth.Game;

import com.bth.App.State;
import com.bth.App.StateManager;
import com.bth.Game.Cave.Cave;
import com.bth.Game.Cave.CaveHandler;
import com.bth.Game.Item.ItemHandler;
import com.bth.Game.Player.Player;
import com.bth.Game.Util.Printer;

import java.util.ArrayList;
import java.util.HashMap;

public class Game extends State {
    private StateManager stateManager;
    private ItemHandler itemHandler;
    private CaveHandler caveHandler;
    private Player player;
    private Printer printer;
    private Cave currentCave;
    private int numberOfCaves = 1;

    private HashMap<Character, Integer> playerPos = new HashMap<>();

    public Game(StateManager stateManager) {
        this.stateManager = stateManager;

        this.initialize();
        this.start();
    }

    /**
     * Initialize
     */
    private void initialize() {
        System.out.println("STARTING GAME");
        this.player = new Player();
        this.caveHandler = new CaveHandler();
        this.itemHandler = new ItemHandler();
        this.printer = new Printer();
        this.currentCave = null;

        // Create caves
        this.createCaves();
    }

    /**
     * Start the game
     */
    private void start() {
        ArrayList<String> possibleMoves = this.getPossibleMoves(this.playerPos);

        System.out.println("\tYou enter a new cave.");
        System.out.println("\tCave name: " + this.currentCave.getName());

        if (possibleMoves.isEmpty()) {
            System.out.println("Uh-oh there is no possible moves, you are stuck!");
            // End the game as there is no possible moves
            this.endGameSession();
        }
        else {
            System.out.println("\tYou look around and see that you can make the following moves: " + String.join(", ", possibleMoves));
        }

    }

    /**
     * Create the caves
     */
    private void createCaves() {
        for (int i = 0; i < this.numberOfCaves; i++) {
            this.caveHandler.createCave(i, "First cave");
        }

        // Set the first cave to current cave
        this.currentCave = this.caveHandler.getCaves().get(0);
        this.currentCave.loadMapData();
        this.setPlayerPos();
    }

    /**
     * Set the player position
     */
    private void setPlayerPos() {
        char[][] mapData = this.currentCave.getMapData();

        for (int y = 0; y < mapData.length; y++) {
            for (int x = 0; x < mapData[y].length; x++) {
                if (mapData[y][x] == 'x') {
                    playerPos.put('x', x);
                    playerPos.put('y', y);
                }
            }
        }
    }

    /**
     * Return a list of possible moves
     * @return  List of possible moves
     */
    private ArrayList<String> getPossibleMoves(HashMap<Character, Integer> position) {
        ArrayList<String> possibleMoves = new ArrayList<>();

        if (this.currentCave.validateMove(Movement.NORTH, position))
            possibleMoves.add("north");

        if (this.currentCave.validateMove(Movement.SOUTH, position))
            possibleMoves.add("south");

        if (this.currentCave.validateMove(Movement.EAST, position))
            possibleMoves.add("east");

        if (this.currentCave.validateMove(Movement.WEST, position))
            possibleMoves.add("west");

        return possibleMoves;
    }

    /**
     * End the game session
     */
    private void endGameSession() {
        this.stateManager.setState(StateManager.States.MENU);
    }
}
