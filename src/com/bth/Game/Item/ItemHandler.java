package com.bth.Game.Item;

import com.bth.Game.Player.Backpack;
import com.bth.Game.Util.Dialog;
import com.bth.Game.Util.Entity;
import com.bth.Game.Util.EventInterface;
import com.bth.Game.Util.Observer.Action;
import com.bth.Game.Util.UI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemHandler {
    private List<Item> items;
    private ItemDialog itemDialog;

    public ItemHandler() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.items = new LinkedList<>();
        this.itemDialog = new ItemDialog();
    }

    /**
     * Get the items
     * @return  The items
     */
    public List<Item> getItems() {
        return this.items;
    }

    public void addItems(List<Item> items) {
        this.items.addAll(items);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * Get item by code/name
     * @param code  Code/name of item to look for
     * @return  Item if found
     */
    public Item getItemByCode(String code) {
        Item item = null;

        for (Item i : this.items) {
            if (i.getName().toLowerCase().equals(code)) {
                item = i;
                break;
            }
        }

        return item;
    }

    /**
     * Display a save dialog for specified item
     * @param entity  The item to save
     * @return  The answer
     */
    public boolean itemSaveDialog(Entity entity, Backpack backpack) {
        Item item = (Item) entity;
        Action action = this.itemDialog.getChoice();
        boolean removeFromCave = false;

        switch (action) {
            case USE_ITEM:
                this.handleUseItem(item, backpack);
                removeFromCave = true;
                break;
            case SAVE_ITEM:
                if (backpack.contains(item)) {
                    UI.write("You already have %s in your backpack, use it before adding another", item.getName());
                }
                else {
                    backpack.addItem(item);
                    removeFromCave = true;
                }
                break;
            case DO_NOTHING:
                break;
        }

        return removeFromCave;
    }

    /**
     * Handler for use item action
     * @param item      The item to use
     * @param backpack  The backpack that contains the item
     */
    private void handleUseItem(Item item, Backpack backpack) {
        backpack.addItem(item);
        this.useItem(item);
        backpack.removeItem(item);
    }

    /**
     * Display string representation of the item dialog message
     * @param entity    Entity
     */
    public void printItemInfo(Entity entity) {
        Item item = (Item) entity;

//        String[] lines = new String[]{
//            "You found a " + item.getName().toLowerCase() + "!",
//            "\t" + item.getDescription(),
//            "You can either use it now, or save it to your backpack to use it later",
//            "What do you want to do?"
//        };

        UI.positive("You found a " + item.getName().toLowerCase() + "!");
        UI.positive("\t" + item.getDescription());
        UI.positive("You can either use it now, or save it to your backpack to use it later");
        UI.question("What do you want to do?");

//        UI.write(lines);
    }

    /**
     * Use an item and remove after use
     * @param item  The item to use
     */
    public void useItem(Item item) {
        // Use the item
        item.use();

        // Remove item after use
        this.removeItem(item);
    }

    /**
     * Check if items contain the item
     * @param item  The item to find
     * @return  True if exists, else false
     */
    public boolean contains(Item item) {
        return this.items.contains(item);
    }

    /**
     * Get item dialog
     * @return  The item dialog
     */
    public ItemDialog getItemDialog() {
        return this.itemDialog;
    }

    /**
     * Set item dialog
     * @param itemDialog    The item dialog
     */
    public void setItemDialog(ItemDialog itemDialog) {
        this.itemDialog = itemDialog;
    }
}
