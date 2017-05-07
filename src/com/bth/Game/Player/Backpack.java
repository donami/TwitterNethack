package com.bth.Game.Player;

import com.bth.Game.Item.Item;
import com.bth.Game.Util.Printer;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Backpack {
    private List<Item> items;
    private boolean open;

    Backpack() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.items = new LinkedList<>();
        this.open = false;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    /**
     * Print the contents of the backpack and display dialog
     */
    public String printBackpack() {
        Printer.out.println("\tYou open your backpack and you find the following items:");
        this.items.forEach(item -> Printer.out.println("\t\t- " + item.getName()));

        Printer.out.println("\tEnter \"use [itemname]\" to use the item, enter \"close\" to close the backpack");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Get item from backpack
     * @param item  The item
     * @return  The item
     */
    public Item getItem(Item item) {
        return item;
    }

    /**
     * Get the items in backpack
     * @return  The items in backpack
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * Set the backpack to open or closed
     * @param open  If the backpack is open or close
     * @return  True if backpack is open, else false
     */
    public boolean setOpen(boolean open) {
        this.open = open;

        return this.open;
    }

    /**
     * Add an item to the backpack
     * @param item  The item to add
     * @return  The added item
     */
    public Item addItem(Item item) {
        this.items.add(item);
        return item;
    }
}
