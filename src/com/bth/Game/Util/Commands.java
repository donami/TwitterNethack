package com.bth.Game.Util;

public enum Commands implements EventInterface {
    NEW_GAME("New Game"),
    HEALTH("Health"),
    HELP("Help"),
    MOVE_EAST("Move east"),
    MOVE_WEST("Move west"),
    MOVE_NORTH("Move north"),
    MOVE_SOUTH("Move south"),
    UNKNOWN("Unknown"),
    QUIT("Quit"),
    AVAILABLE_MOVES("Moves"),
    OPEN_BACKPACK("Open backpack");

    private final String code;

    Commands(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
