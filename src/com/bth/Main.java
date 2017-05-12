package com.bth;

import com.bth.App.Application;

public class Main
{
    public static void main(String[] args) {
        String BLACK = "\u001B[0;30m";
        String RED = "\u001B[0;31m";
        String GREEN = "\u001B[0;32m";
        String YELLOW = "\u001B[0;33m";
        String BLUE = "\u001B[0;34m";
        String MAGENTA = "\u001B[0;35m";
        String CYAN = "\u001B[0;36m";
        String WHITE = "\u001B[0;37m";

        System.out.println(GREEN);

        Application app = new Application();

        app.run();
    }
}
