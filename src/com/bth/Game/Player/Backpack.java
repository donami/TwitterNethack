package com.bth.Game.Player;

import com.bth.Game.Item.Item;
import com.bth.Game.Util.Input;
import com.bth.Game.Util.Observer.Action;
import com.bth.Game.Util.Printer;
import org.apache.commons.lang.StringUtils;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * Print the contents of the backpack and display dialog
     */
    public EnumMap<Action, Item> printBackpack() {
        List<Item> items = this.getItems();
        EnumMap<Action, Item> map = new EnumMap<>(Action.class);

        // Check if the backpack has items
        if (items.size() <= 0) {
            Printer.out.println("\tYour backpack is empty");
            map.put(Action.DO_NOTHING, null);
            return map;
        }

        Printer.out.println("\tYou open your backpack and you find the following items:");
        items.forEach(item -> Printer.out.println("\t\t- " + item.getName()));

        Printer.out.println("\tEnter \"use [itemname]\" to use the item, enter \"close\" to close the backpack");

        boolean validAnswer = false;
        String answer;

        do {
            answer = Input.getInput();

            String[] parts = answer.split(" ");

            if (parts[0].equals("use") && parts.length == 2) {
                boolean contains = false;

                // Check if item exists
                for (Item item : items) {
                    if (item.getName().equals(StringUtils.capitalize(parts[1]))) {
                        contains = true;
                        validAnswer = true;

                        map.put(Action.USE_ITEM, item);
                        break;
                    }
                }

                if (!contains) {
                    Printer.out.println("That item does not seem to be in your backpack");
                }
            }
            else if (parts[0].equals("close")) {
                validAnswer = true;
                map.put(Action.CLOSE_BACKPACK, null);
                Printer.out.println("\tYou close your backpack");
            }
        } while (!validAnswer);

        return map;
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
