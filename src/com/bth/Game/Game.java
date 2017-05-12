package com.bth.Game;

import com.bth.Game.Cave.Cave;
import com.bth.Game.Cave.CaveHandler;
import com.bth.Game.Character.Creature;
import com.bth.Game.Item.ItemHandler;
import com.bth.Game.Player.Player;
import com.bth.Game.Util.*;
import com.bth.Game.Util.Observer.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private GameState gameState;
    private ItemHandler itemHandler;
    private Collision collision;
    private CaveHandler caveHandler;
    private Player player;
    private Cave currentCave;
    private String[] maps;
    private Interpreter interpreter;
    private HashMap<Character, Integer> playerPos = new HashMap<>();

    public Game() {
        this.gameState = null;

        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.maps = new String[]{"first_cave", "scary_dungeon"};
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
     * Action handler for opening the backpack
     * @return true if backpack contains items
     */
    boolean handleOpenBackpack() {
        Action action = Action.DO_NOTHING;

        // Check if backpack is empty
        if (this.player.getBackpack().isEmpty()) {
            return false;
        }

        // Open the backpack and display it's content
        UI.write(this.player.getBackpack().open());
        // Catch user input
        String actionString = this.player.getBackpack().selectItemDialog();
        // Get the actual action from the user input
        action = this.interpreter.interpretString(actionString);


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

        return true;
    }

    /**
     * Setter for interpreter
     * @param interpreter   The interpreter
     */
    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * Handle the player move
     * @param movement  Direction to move
     */
    public void handleMove(Movement movement) {
        // Check if move was success full
        if (this.moveDirection(movement)) {
            UI.info(Constants.MOVED_TO.getText(), movement.getCode());

            // Check if player's new position is colliding with an entity
            this.handleEntityCollision();

        } else {
            UI.info(Constants.NOT_ABLE_TO_MOVE.getText());
        }

        if (this.player.isAlive()) {
            ArrayList<String> possibleMoves = this.caveHandler.getPossibleMoves(this.currentCave, this.playerPos);
            UI.info(Constants.POSSIBLE_MOVES.getText(), String.join(", ", possibleMoves));
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
            case FINISH_CAVE:
                this.finishCave();
                break;
            default:
            case DO_NOTHING:
                break;
        }
    }

    /**
     * Handler for when player reaches the end of the cave
     */
    private void finishCave() {
        // Check if there are more caves, otherwise the game is completed
        if (this.caveHandler.getCaves().size() > 0) {
            UI.header("- You have reached the end of the cave, but there are more caves to expore...\n\n");
            Game.pause(2000);
            // Enter the new cave
            this.enterCave(this.caveHandler.getNextCave());
        }
        else {
            UI.header("============ GAME COMPLETE ============");
            UI.header("- No more caves, you have completed the game!\n\n");
            Game.pause(2500);
            // End the game session
            this.endGameSession();
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

        UI.info("You found a %s", enemy.getName());
        UI.info(enemy.getInfo());
        UI.question(Constants.WHAT_TO_DO.getText());

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
    boolean moveDirection(Movement direction) {
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
     * Set the game state
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Get the game state
     * @return  The game state
     */
    public GameState getGameState() {
        return this.gameState;
    }

    public void setCaveHandler(CaveHandler caveHandler) {
        this.caveHandler = caveHandler;
    }

    /**
     * Create the caves
     */
    private void createCaves() {
        for (int i = 0; i < this.maps.length; i++) {
            this.caveHandler.createCave(i, this.maps[i]);
        }
    }

    /**
     * Enter the next cave
     */
    void enterNextCave() {
        this.enterCave(this.caveHandler.getNextCave());
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

        UI.header("=============NEW CAVE============");
        UI.header("You enter a new cave.");
        UI.header("Cave name: " + this.currentCave.getName());
        UI.header("=================================");
    }

    /**
     * Getter for player position
     * @return  The player position
     */
    public HashMap<Character, Integer> getPlayerPos() {
        return this.playerPos;
    }

    /**
     * Set player pos
     * @param playerPos The player pos
     */
    public void setPlayerPos(HashMap<Character, Integer> playerPos) {
        this.playerPos = playerPos;
    }

    /**
     * Setter for current cave
     * @param cave  The cave
     * @return  Current cave
     */
    public Cave setCurrentCave(Cave cave) {
        this.currentCave = cave;
        return this.currentCave;
    }

    /**
     * Getter for item handler
     * @return  The item handler
     */
    public ItemHandler getItemHandler() {
        return this.itemHandler;
    }

    /**
     * Setter for item handler
     * @param itemHandler   The item handler
     */
    public void setItemHandler(ItemHandler itemHandler) {
        this.itemHandler = itemHandler;
    }

    /**
     * Getter for current cave
     * @return  The current cave
     */
    public Cave getCurrentCave() {
        return this.currentCave;
    }

    /**
     * Setter for player
     * @param player    Player
     */
    public void setPlayer(Player player) {
        this.player = player;
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
    void endGameSession() {
        gameState.endGameSession();
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

    /**
     * Get possible moves
     * @return  Possible moves
     */
    public ArrayList<String> getPossibleMoves() {
        return this.caveHandler.getPossibleMoves(this.currentCave, this.playerPos);
    }

    /**
     * Getter for player
     * @return  The player
     */
    public Player getPlayer() {
        return this.player;
    }
}
