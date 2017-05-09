package com.bth.Game.Util;

import java.util.ArrayList;

public class Dialog {
    private ArrayList<EventInterface> options;

    public Dialog(ArrayList<EventInterface> menu) {
        this.options = menu;
    }

    private void printMenu() {
        for (int i = 0; i < this.options.size(); i++) {
            UI.write("%d. %s", (i + 1), options.get(i).getCode());
        }
    }

    public int getSelection() {
        this.printMenu();

        int choice = UI.in.nextInt();

        while (choice < 1 || choice > options.size()) {
            UI.write("Please enter a valid number");
            choice = UI.in.nextInt();
        }

        return choice - 1;
    }
}
