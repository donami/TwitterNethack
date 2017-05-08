package com.bth.Game.Item;

import com.bth.Game.Util.EventInterface;
import com.bth.Game.Util.Observer.Action;
import com.bth.Game.Util.Printable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemHandler extends Printable {
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
     * @param item  The item to save
     * @return  The answer
     */
    public Action itemSaveDialog(Item item) {
        this.printer.println("\tYou found a " + item.getName().toLowerCase() + "!");
        this.printer.println("\t\t- " + item.getDescription());
        this.printer.println("\tYou can either use it now, or save it to your backpack to use it later");
        this.printer.println("\tWhat do you want to do");

        ArrayList<EventInterface> menu = new ArrayList<>();
        menu.add(Action.SAVE_ITEM);
        menu.add(Action.USE_ITEM);
        menu.add(Action.DO_NOTHING);

        int choice = this.printer.printCommandMenu(menu);

        return (Action) menu.get(choice);
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
