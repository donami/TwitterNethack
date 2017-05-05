package com.bth.Game.Util;

import com.bth.Game.Item.Item;
import com.bth.Game.Util.Observer.Action;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Printer {
    public Printer() {

    }

    public Commands showGameDialog() {
        System.out.println("\tWhat do you want to do? Type \"help\" for commands");

        String answer;
        boolean validAnswer = false;

        HashMap<String, Commands> commandMap = new HashMap<String, Commands>() {
            {
                put("help", Commands.HELP);
                put("move east", Commands.MOVE_EAST);
                put("move south", Commands.MOVE_SOUTH);
                put("move west", Commands.MOVE_WEST);
                put("move north", Commands.MOVE_NORTH);
                put("health", Commands.HEALTH);
                put("quit", Commands.QUIT);
                put("moves", Commands.AVAILABLE_MOVES);
                put("open backpack", Commands.OPEN_BACKPACK);
            }
        };

        // Default command
        Commands command = Commands.UNKNOWN;

        // Loop through users command until correct one is entered
        do {
            answer = this.getInput();

            // Check if command exists
            if (commandMap.containsKey(answer)) {
                validAnswer = true;
                command = commandMap.get(answer);
            }
            else {
                System.out.println("Invalid command entered. Type \"help\" for commands");
            }
        } while (!validAnswer);

        // Handle the answer
        switch (command) {
            case HELP:
                // TODO: 2017-05-05 write help text
                System.out.println("\t===================");
                System.out.println("\tAvailable commands:");
                System.out.println("\t\thealth: Display your current health");
                System.out.println("\t\tmove <direction>: Move in specific direction, valid directions are: north, south, west, east");
                System.out.println("\t\topen backpack: Open your backpack and display it's content");
                System.out.println("\t\tmoves: Display available moves");
                System.out.println("\t\tquit: Exit the game");
                break;
            case MOVE_EAST:
                break;
            case MOVE_WEST:
                break;
            case MOVE_NORTH:
                break;
            case MOVE_SOUTH:
                break;
            case AVAILABLE_MOVES:
                break;
            case HEALTH:
                break;
            case QUIT:
                break;
            case OPEN_BACKPACK:
                break;
            case UNKNOWN:
            default:
                System.out.println("Invalid command");
                break;
        }

        return command;
    }

    /**
     * Print possible moves to the console
     * @param possibleMoves List of possible moves
     */
    public void printAvailableMoves(List<String> possibleMoves) {
        System.out.println("\tYou look around and see that you can make the following moves: " + String.join(", ", possibleMoves));
    }

    /**
     * Print player moved message
     * @param direction Direction to move
     */
    public void printPlayerMoved(String direction) {
        System.out.println("You moved to the " + direction);
    }

    /**
     * Print player move fail message
     */
    public void printPlayerMoveFail() {
        System.out.println("You are not able to move in that direction");
    }

    /**
     * Print player stuck message
     */
    public void printPlayerStuck() {
        System.out.println("Uh-oh there is no possible moves, you are stuck!");
    }

    /**
     * Print player's current health
     * @param health    Health
     */
    public void printCurrentHealth(int health) {
        System.out.println("Your current health is: " + health);
    }

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


}
