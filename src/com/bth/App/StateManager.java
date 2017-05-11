package com.bth.App;

import com.bth.Game.Game;
import com.bth.Game.GameState;
import com.bth.Menu.MenuState;

public class StateManager {
    public enum States {
        PLAY,
        MENU
    }

    private Game game;
    private State state;

    public StateManager() {
        this.initialize();
    }

    private void initialize() {
        this.setCurrentState(new MenuState(this));
    }

    /**
     * Set the state of the application
     * @param state The state instance
     * @return  The new state
     */
    private State setCurrentState(State state) {
        this.state = state;

        return this.state;
    }

    /**
     * Set the state from constant
     * @param state The state enum
     */
    public void setState(States state) {
        switch (state) {
            case MENU:
                this.setCurrentState(new MenuState(this));
                break;
            case PLAY:
                GameState gameState = new GameState(this);
                this.setCurrentState(gameState);
                break;
                default:
        }
    }

    /**
     * Get the current state
     * @return  Current state
     */
    State getState() {
        return this.state;
    }


    /**
     * Set the game instance
     * @param g The game instance
     * @return  Game instance
     */
    public Game setGameInstance(Game g) {
        this.game = g;
        return this.game;
    }
}
