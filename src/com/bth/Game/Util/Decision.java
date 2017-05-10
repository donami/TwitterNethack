package com.bth.Game.Util;

import java.util.*;

public class Decision {
    public static int printMenu(String question, ArrayList<String> menu) {
        UI.write(question);

        return Decision.printMenu(menu);
    }

    private static int printMenu(ArrayList<String> menu) {
        for (int i = 0; i < menu.size(); i++) {
            UI.write("%d. %s", (i + 1), menu.get(i));
        }

        int choice = UI.in.nextInt();

        while (choice < 1 || choice > menu.size()) {
            UI.write(Constants.ENTER_VALID_NUMBER.getText());
            choice = UI.in.nextInt();
        }

        return choice - 1;
    }

    private static int printCommandMenu(ArrayList<EventInterface> menu) {
        for (int i = 0; i < menu.size(); i++) {
            UI.write("%d. %s", (i + 1), menu.get(i).getCode());
        }

        int choice = UI.in.nextInt();

        while (choice < 1 || choice > menu.size()) {
            UI.write(Constants.ENTER_VALID_NUMBER.getText());
            choice = UI.in.nextInt();
        }

        return choice - 1;
    }

    public static Commands showGameDialog() {
        UI.write(Constants.WHAT_TO_DO.getText());

        ArrayList<EventInterface> menu = new ArrayList<>();
        menu.add(Commands.MOVE_NORTH);
        menu.add(Commands.MOVE_SOUTH);
        menu.add(Commands.MOVE_EAST);
        menu.add(Commands.MOVE_WEST);
        menu.add(Commands.HEALTH);
        menu.add(Commands.STATS);
        menu.add(Commands.AVAILABLE_MOVES);
        menu.add(Commands.OPEN_BACKPACK);
        menu.add(Commands.QUIT);

        int choice = Decision.printCommandMenu(menu);

        return (Commands) menu.get(choice);
    }
}
