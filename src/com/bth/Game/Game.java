package com.bth.Game;

import com.bth.App.State;
import com.bth.App.StateManager;
import com.bth.Game.Cave.Cave;
import com.bth.Game.Cave.CaveHandler;
import com.bth.Game.Character.Creature;
import com.bth.Game.Item.ItemHandler;
import com.bth.Game.Player.Player;
import com.bth.Game.Util.*;
import com.bth.Game.Util.Observer.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class Game extends State {
    private StateManager stateManager;
    private ItemHandler itemHandler;
    private Collision collision;
    private CaveHandler caveHandler;
    private Player player;
    private Cave currentCave;
    private int numberOfCaves = 1;
    private Interpreter interpreter;
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
        this.player = new Player();
        this.caveHandler = new CaveHandler();
        this.itemHandler = new ItemHandler();
        this.collision = new Collision();
        this.currentCave = null;
        this.interpreter = new Interpreter(this.itemHandler);

        // Create caves
        this.createCaves();
    }

    /**
     * Start the game
     */
    private void start() {
        ArrayList<String> possibleMoves = this.caveHandler.getPossibleMoves(this.currentCave, this.playerPos);

        UI.write("You enter a new cave.");
        UI.write("Cave name: " + this.currentCave.getName());

        if (possibleMoves.isEmpty()) {
            UI.write(Constants.NO_POSSIBLE_MOVES.getText());

            // End the game as there is no possible moves
            this.endGameSession();
        }
        else {
            UI.write(Constants.POSSIBLE_MOVES.getText(), String.join(", ", possibleMoves));
            Commands command;

            do {
                // Ensure that player has health, otherwise game is over
                if (this.player.getHealth() <= 0) {
                    this.player.die();
                    this.endGameSession();
                    break;
                }

                possibleMoves = this.caveHandler.getPossibleMoves(this.currentCave, this.playerPos);

                command = Decision.showGameDialog();

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
                        UI.write(Constants.POSSIBLE_MOVES.getText(), String.join(", ", possibleMoves));
                        break;
                    case HEALTH:
                        UI.write(Constants.CURRENT_HEALTH.getText(), this.player.getHealth());
                        break;
                    case OPEN_BACKPACK:
                        this.handleOpenBackpack();
                        break;
                    default:
                }
            } while (command != Commands.QUIT);
        }

    }

    /**
     * Action handler for opening the backpack
     */
    private void handleOpenBackpack() {
        Action action;

        // Check if backpack is empty
        if (this.player.getBackpack().isEmpty()) {
            UI.write(Constants.BACKPACK_EMPTY.getText());
            action = Action.DO_NOTHING;
        }
        else {
            // Open the backpack and display it's content
            UI.write(this.player.getBackpack().open());
            // Catch user input
            String actionString = this.player.getBackpack().selectItemDialog();
            // Get the actual action from the user input
            action = this.interpreter.interpretString(actionString);
        }

        switch (action) {
            case USE_ITEM:
                this.itemHandler.useItem(action.getObject());

                this.player.getBackpack().getItems().remove(action.getObject());
                break;
            case CLOSE_BACKPACK:
                // Close backpack
                this.player.getBackpack().setOpen(false);
                break;
            case INVALID_COMMAND:
                UI.write(Constants.INVALID_COMMAND.getText());
                break;
            case DO_NOTHING:
                break;
        }
    }

    /**
     * Handle the player move
     * @param movement  Direction to move
     */
    private void handleMove(Movement movement) {
        // Check if move was success full
        if (this.moveDirection(movement)) {
            UI.write(Constants.MOVED_TO.getText(), movement.getCode());

            // Check if player's new position is colliding with an entity
            this.handleEntityCollision();

        } else {
            UI.write(Constants.NOT_ABLE_TO_MOVE.getText());
        }

        if (this.player.isAlive()) {
            ArrayList<String> possibleMoves = this.caveHandler.getPossibleMoves(this.currentCave, this.playerPos);
            UI.write(Constants.POSSIBLE_MOVES.getText(), String.join(", ", possibleMoves));
        }
    }

    /**
     * Handle collision between entity and player
     */
    private void handleEntityCollision() {
        Entity entity = this.currentCave.getEntityAtPos(this.playerPos.get('x'), this.playerPos.get('y'));
        Collision.Dialogs dialog = this.collision.isColliding(entity);

        switch (dialog) {
            case ITEM_SAVE_DIALOG:
                this.pickUpItem(entity);
                break;
            case INTERACT_ENEMY:
                this.interactWithCharacter(entity);
                break;
            default:
            case DO_NOTHING:
                break;
        }
    }

    /**
     * Pick up item
     * @param entity    The item entity
     */
    private void pickUpItem(Entity entity) {
        this.itemHandler.printItemInfo(entity);
        boolean removeFromCave = this.itemHandler.itemSaveDialog(entity, this.player.getBackpack());

        if (removeFromCave) {
            this.caveHandler.removeEntityFromCave(this.currentCave, this.playerPos.get('x'), this.playerPos.get('y'));
        }
    }

    /**
     * Handle interaction with a creature
     * @param entity    Creature entity
     */
    private void interactWithCharacter(Entity entity) {
        Creature enemy = (Creature) entity;
        Fight fight = new Fight(this.player, enemy);

        UI.write("You found a %s", enemy.getName());
        UI.write(enemy.getInfo());
        UI.write(Constants.WHAT_TO_DO.getText());

        ArrayList<EventInterface> menu = new ArrayList<>();
        menu.add(Commands.FIGHT);
        menu.add(Commands.RUN_AWAY);

        // Display menu
        Dialog dialog = new Dialog(menu);
        int choice = dialog.getSelection();

        switch ((Commands) menu.get(choice)) {
            case FIGHT:
                fight.attack();
                break;
            case RUN_AWAY:
                fight.run();
                break;
        }

        // If the enemy died during the fight, it should be remove from the cave
        if (!enemy.isAlive()) {
            this.caveHandler.removeEntityFromCave(this.currentCave, this.playerPos.get('x'), this.playerPos.get('y'));
        }
    }

    /**
     * Move the player in specific direction
     * @param direction The direction to move
     * @return  True if move was successful, else false
     */
    private boolean moveDirection(Movement direction) {
        if (!this.currentCave.validateMove(direction, this.playerPos)) {
            return false;
        }

        int playerX = this.playerPos.get('x');
        int playerY = this.playerPos.get('y');

        // Move the player based on direction
        switch (direction) {
            case NORTH:
                this.setPlayerPos(playerX, playerY - 1);
                break;
            case SOUTH:
                this.setPlayerPos(playerX, playerY + 1);
                break;
            case EAST:
                this.setPlayerPos(playerX + 1, playerY);
                break;
            case WEST:
                this.setPlayerPos(playerX - 1, playerY);
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

        // Enter the first cave
        this.enterCave(this.caveHandler.getCaves().get(0));
    }

    /**
     * @param cave  Cave to enter
     */
    private void enterCave(Cave cave) {
        // Set the current cave
        this.setCurrentCave(cave);
        // Load map data for the cave
        this.currentCave.loadMapData();
        // Add items to the item handler
        this.itemHandler.addItems(this.currentCave.getItems());
        // Initialize the player position
        this.setPlayerPos(0, 0);
    }

    /**
     * Setter for current cave
     * @param cave  The cave
     * @return  Current cave
     */
    private Cave setCurrentCave(Cave cave) {
        this.currentCave = cave;
        return this.currentCave;
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
     * Pause
     * @param time  Time to pause
     */
    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the player health
     * @param health    The new health
     */
    private void setHealth(int health) {
        this.player.setHealth(health);
    }
}
