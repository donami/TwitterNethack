package com.bth.Game.Util;

public enum Constants {
    POSSIBLE_MOVES("You look around and see that you can make the following moves: %s"),
    NO_POSSIBLE_MOVES("Uh-oh there is no possible moves, you are stuck!"),
    MOVED_TO("You moved to the %s"),
    INVALID_COMMAND("Invalid command"),
    BACKPACK_EMPTY("Your backpack is empty"),
    CURRENT_HEALTH("Your current health is: %d"),
    PLAYER_DEAD("Uh-oh! You are now dead."),
    HEALTH_INCREASED_BY("Your health is increased by %d"),
    ENTER_VALID_NUMBER("Please enter a valid number"),
    WHAT_TO_DO("What do you want to do?"),
    NOT_ABLE_TO_MOVE("You are not able to move in that direction");

    private String text;

    Constants(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

}
