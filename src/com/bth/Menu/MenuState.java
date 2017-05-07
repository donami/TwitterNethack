package com.bth.Menu;

import com.bth.App.State;
import com.bth.App.StateManager;
import com.bth.Game.Util.Printer;

import java.util.ArrayList;

public class MenuState extends State {
    private StateManager stateManager;
    private ArrayList<String> menu;
    private Printer printer;

    public MenuState(StateManager stateManager) {
        this.printer = new Printer();
        this.stateManager = stateManager;
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.menu = new ArrayList<>();

        menu.add("New game");
        menu.add("Exit application");

        this.displayMenu();
    }

    /**
     * Display the menu
     */
    private void displayMenu() {
        String welcome = "Welcome to TwitterNethack!\n" +
                "\t==========================";

        int menuChoice = this.printer.printMenu(welcome, this.menu);

        this.handleMenuSelection(menuChoice);
    }

    /**
     * Handle the users menu selection
     * @param answer    The number of menu selection
     */
    private void handleMenuSelection(int answer) {
        switch (answer) {
            case 0:
                // Launch game
                this.launchGame();
                break;

            case 1:
                // Exit game
                this.quit();
                break;
        }
    }

    /**
     * Launch the game
     */
    private void launchGame() {
        this.stateManager.setState(StateManager.States.PLAY);
    }

    /**
     * Exit application
     */
    private void quit() {
        System.exit(0);
    }
}
