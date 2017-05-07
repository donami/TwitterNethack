package com.bth.Game.Item;

import com.bth.Game.Util.EventInterface;
import com.bth.Game.Util.Observer.Action;
import com.bth.Game.Util.Printer;

import java.util.ArrayList;
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
        Printer.out.println("\tYou found a " + item.getName().toLowerCase() + "!");
        Printer.out.println("\t\t- " + item.getDescription());
        Printer.out.println("\tYou can either use it now, or save it to your backpack to use it later");
        Printer.out.println("\tWhat do you want to do");

        ArrayList<EventInterface> menu = new ArrayList<>();
        menu.add(Action.SAVE_ITEM);
        menu.add(Action.USE_ITEM);
        menu.add(Action.DO_NOTHING);

        int choice = Printer.printCommandMenu(menu);

        return (Action) menu.get(choice);
    }
}
