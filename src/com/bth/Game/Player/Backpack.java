package com.bth.Game.Player;

import com.bth.Game.Item.Item;

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
