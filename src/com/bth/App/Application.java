package com.bth.App;

public class Application {

    private StateManager stateManager;

    public Application() {
        this.initialize();
    }

    private void initialize() {
        this.stateManager = new StateManager();
    }

    public void run() {

    }
}
