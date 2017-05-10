package com.bth.Game.Util.Observer;

import com.bth.Game.Item.Item;
import com.bth.Game.Util.EventInterface;

public enum Action implements EventInterface {
    INCREASE_HEALTH("Increase health", "increase health", Type.TYPE_HASVALUE),
    INCREASE_DAMAGE("Increase damage", "increase damage", Type.TYPE_HASVALUE),
    CLOSE_BACKPACK("Close backpack", "close", Type.TYPE_DEFAULT),
    USE_ITEM("Use item", "use", Type.TYPE_HASOBJECT),
    SAVE_ITEM("Save item", "save", Type.TYPE_HASOBJECT),
    DO_NOTHING("Do nothing", "nothing", Type.TYPE_UNKNOWN),
    INVALID_COMMAND("Invalid command", "invalid", Type.TYPE_UNKNOWN);

    private final String code;
    private final String command;
    private final Type type;
    private Item object;

    Action(String code, String command, Type type) {
        this.code = code;
        this.command = command;
        this.type = type;
    }

    public String getCode() {
        return this.code;
    }

    public String getCommand() {
        return this.command;
    }

    public Type getType() {
        return this.type;
    }

    public void setObject(Item object) {
        this.object = object;
    }

    public Item getObject() {
        return this.object;
    }
}