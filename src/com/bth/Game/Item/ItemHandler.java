package com.bth.Game.Item;

import com.bth.Game.Util.Input;
import com.bth.Game.Util.Printer;
import com.sun.org.apache.xpath.internal.SourceTree;

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
    public String itemSaveDialog(Item item) {
        Printer.out.println("\tYou found a " + item.getName().toLowerCase() + "!");
        Printer.out.println("\t\t- " + item.getDescription());
        Printer.out.println("\tYou can either use it now, or save it to your backpack to use it later");
        Printer.out.println("\tType \"save\" to save it, or \"use\" to use it now");

        boolean validAnswer = false;
        String answer;

        do {
            answer = Input.getInput();

            if (answer.equals("save") || answer.equals("use")) {
                validAnswer = true;
            }
        } while (!validAnswer);

        return answer;
    }
}
