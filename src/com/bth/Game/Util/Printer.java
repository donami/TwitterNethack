package com.bth.Game.Util;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class Printer implements PrinterInterface {
    private Scanner in;
    public PrintStream out;

    public Printer() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void setIn(InputStream in) {
        this.in = new Scanner(in);
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    public void println(String line) {
        this.out.println(line);
    }

    public int printMenu(String question, ArrayList<String> menu) {
        this.out.println("\t" + question);

        return this.printMenu(menu);
    }

    public int printMenu(ArrayList<String> menu) {
        for (int i = 0; i < menu.size(); i++) {
            this.out.println("\t" + (i + 1) + ". " + menu.get(i));
        }

        int choice = in.nextInt();

        while (choice < 1 || choice > menu.size()) {
            out.println("\tPlease enter a valid number");
            choice = in.nextInt();
        }

        return choice - 1;
    }

    public int printCommandMenu(ArrayList<EventInterface> menu) {
        for (int i = 0; i < menu.size(); i++) {
            this.out.println("\t" + (i + 1) + ". " + menu.get(i).getCode());
        }

        int choice = in.nextInt();

        while (choice < 1 || choice > menu.size()) {
            this.out.println("\tPlease enter a valid number");
            choice = in.nextInt();
        }

        return choice - 1;
    }

    public Commands showGameDialog() {
        this.out.println("\tWhat do you want to do? Type \"help\" for commands");

        ArrayList<EventInterface> menu = new ArrayList<>();
        menu.add(Commands.MOVE_NORTH);
        menu.add(Commands.MOVE_SOUTH);
        menu.add(Commands.MOVE_EAST);
        menu.add(Commands.MOVE_WEST);
        menu.add(Commands.HEALTH);
        menu.add(Commands.AVAILABLE_MOVES);
        menu.add(Commands.OPEN_BACKPACK);
        menu.add(Commands.QUIT);

        int choice = this.printCommandMenu(menu);

        return (Commands) menu.get(choice);
    }

    /**
     * Print possible moves to the console
     * @param possibleMoves List of possible moves
     */
    public void printAvailableMoves(List<String> possibleMoves) {
        out.println("\tYou look around and see that you can make the following moves: " + String.join(", ", possibleMoves));
    }

    /**
     * Print player moved message
     * @param direction Direction to move
     */
    public void printPlayerMoved(String direction) {
        out.println("You moved to the " + direction);
    }

    /**
     * Print player move fail message
     */
    public void printPlayerMoveFail() {
        out.println("You are not able to move in that direction");
    }

    /**
     * Print player stuck message
     */
    public void printPlayerStuck() {
        out.println("Uh-oh there is no possible moves, you are stuck!");
    }

    /**
     * Print player's current health
     * @param health    Health
     */
    public void printCurrentHealth(int health) {
        out.println("Your current health is: " + health);
    }
}
