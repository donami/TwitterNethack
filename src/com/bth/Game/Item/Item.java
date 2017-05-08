package com.bth.Game.Item;

import com.bth.Game.Player.Player;
import com.bth.Game.Util.Entity;
import com.bth.Game.Util.Observer.Action;
import com.bth.Game.Util.Observer.Observer;
import com.bth.Game.Util.Observer.Subject;

import java.util.ArrayList;

interface ItemInterface {
    void use();
    boolean isUsable();
}

public abstract class Item implements Entity, ItemInterface, Subject {
    private int id;
    String name;
    String description;
    private String defaultAction;
    private ArrayList<Observer> observers = new ArrayList<>();
    private Entity relatedEntity;

    /**
     * Getter for related entity
     * @return  The entity
     */
    public Entity getRelatedEntity() {
        return relatedEntity;
    }

    /**
     * Setter for related entity
     * @param entity    The entity
     */
    public void setRelatedEntity(Entity entity) {
        this.relatedEntity = entity;
    }

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

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    @Override
    public void notifyObservers(Action action, Object value) {
        for (Observer ob : observers) {
            ob.update(action, value);
        }
    }
}
