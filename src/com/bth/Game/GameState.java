package com.bth.Game;

import com.bth.App.State;
import com.bth.App.StateManager;
import com.bth.Game.Util.Commands;
import com.bth.Game.Util.Constants;
import com.bth.Game.Util.Decision;
import com.bth.Game.Util.UI;

import java.util.ArrayList;

public class GameState extends State {
    private Game game;
    private StateManager stateManager;

    public GameState(StateManager stateManager) {
        this.stateManager = stateManager;

        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.game = new Game();
        this.game.setGameState(this);
        this.game.enterNextCave();

        this.gameLoop();
    }

    /**
     * The game loop
     */
    private void gameLoop() {
        ArrayList<String> possibleMoves = this.game.getPossibleMoves();
        Commands command;

        UI.write(Constants.POSSIBLE_MOVES.getText(), String.join(", ", possibleMoves));

        if (possibleMoves.isEmpty()) {
            UI.write(Constants.NO_POSSIBLE_MOVES.getText());

            // End the game as there is no possible moves
            this.endGameSession();
        }
        else {
            do {
                // Ensure that player has health, otherwise game is over
                if (this.game.getPlayer().getHealth() <= 0) {
                    this.game.getPlayer().die();
                    this.endGameSession();
                    break;
                }

                possibleMoves = this.game.getPossibleMoves();

                command = Decision.showGameDialog();

                switch (command) {
                    case MOVE_EAST:
                        game.handleMove(Movement.EAST);
                        break;
                    case MOVE_NORTH:
                        game.handleMove(Movement.NORTH);
                        break;
                    case MOVE_SOUTH:
                        game.handleMove(Movement.SOUTH);
                        break;
                    case MOVE_WEST:
                        game.handleMove(Movement.WEST);
                        break;
                    case AVAILABLE_MOVES:
                        UI.write(Constants.POSSIBLE_MOVES.getText(), String.join(", ", possibleMoves));
                        break;
                    case HEALTH:
                        UI.write(Constants.CURRENT_HEALTH.getText(), game.getPlayer().getHealth());
                        break;
                    case STATS:
                        UI.write(game.getPlayer().stats());
                        break;
                    case OPEN_BACKPACK:
                        if (!game.handleOpenBackpack()) {
                            UI.write(Constants.BACKPACK_EMPTY.getText());
                        }
                        break;
                    default:
                }
            } while (command != Commands.QUIT);
        }
    }

    /**
     * End the game session
     */
    void endGameSession() {
        this.stateManager.setState(StateManager.States.MENU);
    }
}
