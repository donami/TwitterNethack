package com.bth.Menu;

import com.bth.App.State;
import com.bth.App.StateManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuState extends State {
    private StateManager stateManager;

    public MenuState(StateManager stateManager) {
        this.stateManager = stateManager;
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.displayMenu();
    }

    /**
     * Display the menu
     */
    private void displayMenu() {
        System.out.println("\tWelcome to TwitterNethack!");
        System.out.println("\t==========================");

        System.out.println("\t1. Launch game \n" +
                "\t0. Exit application \n");


        System.out.print("\tEnter menu selection");

        boolean validInput = false;

        do {
            try {
                int answer = Integer.parseInt(this.getInput());

                // Check if selection exists in the menu
                if (answer >= 0 && answer <= 1) {
                    validInput = true;

                    this.handleMenuSelection(answer);
                }
                else {
                    System.out.println("Invalid menu selection entered");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid menu selection entered");
            }
        } while (!validInput);

    }

    /**
     * Handle the users menu selection
     * @param answer    The number of menu selection
     */
    private void handleMenuSelection(int answer) {
        switch (answer) {
            case 1:
                // Launch game
                this.launchGame();
                break;

            case 0:
                // Exit game
                this.quit();
            default:
                System.out.println("Invalid selection");
                break;
        }
    }

    /**
     * Get the input from console
     * @return  Input as string
     */
    private String getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;

        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return input;
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
