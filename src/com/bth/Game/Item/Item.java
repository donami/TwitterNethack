package com.bth.Game.Item;

import com.bth.Game.Util.Entity;

interface ItemInterface {
    void use();
}

public abstract class Item implements Entity, ItemInterface {
    private int id;
    String name;
    String description;
    private String defaultAction;

    /**
     * Getter for ID
     * @return  The ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for ID
     * @param id  ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for ID
     * @return  The name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name  The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for description
     * @return  The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description    The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for defaultAction
     * @return  The default action
     */
    public String getDefaultAction() {
        return defaultAction;
    }

    /**
     * Setter for defaultAction
     * @param defaultAction The defaultAction
     */
    public void setDefaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
    }
}
