package com.bth.Game;

public enum Movement {
    NORTH("north"),
    SOUTH("south"),
    WEST("west"),
    EAST("east");

    private final String code;

    Movement(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
