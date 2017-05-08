package com.bth.Game.Item;

import com.bth.Game.Player.Backpack;
import com.bth.Game.Util.Dialog;
import com.bth.Game.Util.Entity;
import com.bth.Game.Util.EventInterface;
import com.bth.Game.Util.Observer.Action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemHandler {
    private List<Item> items;

    public ItemHandler() {
        this.initialize();
    }

    /**
     * Initialize
     */
    private void initialize() {
        this.items = new LinkedList<>();
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
     * Display information about specified item
     * @param item  The item
     */
    public void displayItemInfo(Item item) {
        // TODO: 2017-05-04 implement
    }

    /**
     * Display a save dialog for specified item
     * @param entity  The item to save
     * @return  The answer
     */
    public boolean itemSaveDialog(Entity entity, Backpack backpack) {
        Item item = (Item) entity;

        ArrayList<EventInterface> menu = new ArrayList<>();
        menu.add(Action.SAVE_ITEM);
        menu.add(Action.USE_ITEM);
        menu.add(Action.DO_NOTHING);

        Dialog dialog = new Dialog(menu);
        int choice = dialog.getSelection();
        boolean removeFromCave = false;

        switch ((Action) menu.get(choice)) {
            case USE_ITEM:
                this.handleUseItem(item, backpack);
                removeFromCave = true;
                break;
            case SAVE_ITEM:
                backpack.addItem(item);
                removeFromCave = true;
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
     * String representation of the item dialog message
     * @param entity    Entity
     * @return  String
     */
    public String[] itemDialogMessage(Entity entity) {
        Item item = (Item) entity;

        return new String[]{
            "You found a " + item.getName().toLowerCase() + "!",
            "\t" + item.getDescription(),
            "You can either use it now, or save it to your backpack to use it later",
            "What do you want to do?"
        };
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
}
