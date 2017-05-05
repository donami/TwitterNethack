package com.bth.Game;

import com.bth.App.State;
import com.bth.App.StateManager;
import com.bth.Game.Cave.Cave;
import com.bth.Game.Cave.CaveHandler;
import com.bth.Game.Item.Item;
import com.bth.Game.Item.ItemHandler;
import com.bth.Game.Player.Player;
import com.bth.Game.Util.Collision;
import com.bth.Game.Util.Commands;
import com.bth.Game.Util.Entity;
import com.bth.Game.Util.Printer;

import java.util.ArrayList;
import java.util.HashMap;

public class Game extends State {
    private StateManager stateManager;
    private ItemHandler itemHandler;
    private Collision collision;
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
        this.collision = new Collision();
        this.currentCave = null;

        // Create caves
        this.createCaves();
    }

    /**
     * Start the game
     */
    private void start() {
        ArrayList<String> possibleMoves = this.caveHandler.getPossibleMoves(this.currentCave, this.playerPos);

        System.out.println("\tYou enter a new cave.");
        System.out.println("\tCave name: " + this.currentCave.getName());

        if (possibleMoves.isEmpty()) {
            this.printer.printPlayerStuck();

            // End the game as there is no possible moves
            this.endGameSession();
        }
        else {
            this.printer.printAvailableMoves(possibleMoves);

            Commands command;

            do {
                possibleMoves = this.caveHandler.getPossibleMoves(this.currentCave, this.playerPos);

                command = this.printer.showGameDialog();

                switch (command) {
                    case MOVE_EAST:
                        this.handleMove(Movement.EAST);
                        break;
                    case MOVE_NORTH:
                        this.handleMove(Movement.NORTH);
                        break;
                    case MOVE_SOUTH:
                        this.handleMove(Movement.SOUTH);
                        break;
                    case MOVE_WEST:
                        this.handleMove(Movement.WEST);
                        break;
                    case AVAILABLE_MOVES:
                        this.printer.printAvailableMoves(possibleMoves);
                        break;
                    case HEALTH:
                        this.printer.printCurrentHealth(this.player.getHealth());
                        break;
                    default:
                }
            } while (command != Commands.QUIT);

        }

    }

    /**
     * Handle the player move
     * @param movement  Direction to move
     */
    private void handleMove(Movement movement) {
        String movementString = "";

        switch (movement) {
            case EAST: movementString = "east"; break;
            case NORTH: movementString = "north"; break;
            case WEST: movementString = "west"; break;
            case SOUTH: movementString = "south"; break;
            default: break;
        }

        // Check if move was success full
        if (this.movePlayer(movement)) {
            this.printer.printPlayerMoved(movementString);

            // Check if player's new position is colliding with an entity
            this.handleEntityCollision();

        } else {
            // Print message if player was unable to move
            this.printer.printPlayerMoveFail();
        }

        ArrayList<String> possibleMoves = this.caveHandler.getPossibleMoves(this.currentCave, this.playerPos);
        this.printer.printAvailableMoves(possibleMoves);
    }

    /**
     * Handle collision between entity and player
     */
    private void handleEntityCollision() {
        Entity entity = this.currentCave.getEntityAtPos(this.playerPos.get('x'), this.playerPos.get('y'));
        Collision.Dialogs dialog = this.collision.isColliding(entity);

        switch (dialog) {
            case ITEM_SAVE_DIALOG:
                Item item = (Item) entity;
                String answer = this.itemHandler.itemSaveDialog(item);

                if (answer.equals("save")) {
                    // Add the item to the backpack
                    this.player.getBackpack().addItem(item);
                }
                else if (answer.equals("use")) {
                    item.use();
                }

                break;
            default:
            case DO_NOTHING:
                break;
        }

    }

    /**
     * Move the player in specific direction
     * @param direction The direction to move
     * @return  True if move was successful, else false
     */
    private boolean movePlayer(Movement direction) {
        if (!this.currentCave.validateMove(direction, this.playerPos)) {
            return false;
        }

        // Move the player based on direction
        switch (direction) {
            case NORTH:
                this.setPlayerPos(this.playerPos.get('x'), this.playerPos.get('y') - 1);
                break;
            case SOUTH:
                this.setPlayerPos(this.playerPos.get('x'), this.playerPos.get('y') + 1);
                break;
            case EAST:
                this.setPlayerPos(this.playerPos.get('x') + 1, this.playerPos.get('y'));
                break;
            case WEST:
                this.setPlayerPos(this.playerPos.get('x') - 1, this.playerPos.get('y'));
                break;

            default:
        }

        return true;
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
        this.setPlayerPos(0, 0);
    }

    /**
     * Set player position by absolute coords
     * @param x     X position
     * @param y     Y position
     */
    private void setPlayerPos(int x, int y) {
        this.playerPos.put('x', x);
        this.playerPos.put('y', y);
    }

    /**
     * End the game session
     */
    private void endGameSession() {
        this.stateManager.setState(StateManager.States.MENU);
    }

    /**
     * DEBUG
     * TODO REMOVE THIS LATER
     */
    private void debugPlayerPos() {
        System.out.println(this.playerPos.get('x') + ", " + this.playerPos.get('y'));
    }
}
