package com.bth.Game.Player;

import com.bth.Game.Item.Item;
import com.bth.Game.Util.Observer.Action;
import com.bth.Game.Util.Observer.Observer;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Backpack {
    private List<Item> items;
    private boolean open;
    private ItemObserver itemObserver;
    private Player player;

    public Backpack(Player player) {
        this.player = player;
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.itemObserver = new ItemObserver();
        this.items = new LinkedList<>();
        this.open = false;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    /**
     * Return a string representation of the backpack content
     * @return String   Backpack string message
     */
    private String printBackpack() {
        StringBuilder msg = new StringBuilder();
        msg.append("\tYou open your backpack and you find the following items:\n");

        this.items.forEach(item -> msg.append("\t\t- ").append(item.getName()).append("\n"));

        msg.append("\tEnter \"use [itemname]\" to use the item, enter \"close\" to close the backpack");

        return msg.toString();
    }

    /**
     * Present a item handling dialog on the backpack
     * @return  User input
     */
    public String selectItemDialog() {
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
     * Check if the backpack contains specific item
     * @param item  The item
     * @return  True if backpack contains item, else false
     */
    public boolean contains(Item item) {
        return this.items.contains(item);
    }

    /**
     * Open the backpack
     * @return  String representation of the backpack
     */
    public String open() {
        this.setOpen(true);
        return this.printBackpack();
    }

    /**
     * Set the backpack to open or closed
     * @param open  If the backpack is open or close
     * @return  True if backpack is open, else false
     */
    public Backpack setOpen(boolean open) {
        this.open = open;

        return this;
    }

    /**
     * Getter for open
     * @return  Value of open
     */
    public boolean getOpen() {
        return this.open;
    }

    /**
     * Add an item to the backpack
     * @param item  The item to add
     * @return  The added item
     */
    public Item addItem(Item item) {
        this.items.add(item);
        item.registerObserver(this.itemObserver);
        return item;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        item.removeObserver(this.itemObserver);
    }

    /**
     * Observer for items
     */
    private class ItemObserver implements Observer {

        @Override
        public void update(Action action, Object value) {
            switch (action) {
                case INCREASE_HEALTH:
                    Backpack.this.player.increaseHealth((int) value);
                    break;
                case INCREASE_DAMAGE:
                    Backpack.this.player.increaseDamage((int) value);
                    break;
            }
        }

    }
}
