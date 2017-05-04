package com.bth.Game.Item;

import java.util.LinkedList;
import java.util.List;

public class ItemHandler {
    private List<Item> items;
    private ActionDispatcher actionDispatcher;

    public ItemHandler() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.items = new LinkedList<>();
        this.actionDispatcher = new ActionDispatcher();
    }

    /**
     * Get the items
     * @return  The items
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * Display information about specified item
     * @param item  The item
     */
    public void displayItemInfo(Item item) {
        // TODO: 2017-05-04 implement
    }


    /**
     * Display a save dialog for specified item
     * @param item  The item to save
     * @return  The answer
     */
//    public String itemSaveDialog(Item item) {
//
//    }
}
