package com.bth;

import com.bth.App.Application;

public class Main
{
    public static void main(String[] args) {
        String GREEN = "\u001B[0;32m";

        System.out.println(GREEN);

        Application app = new Application();

        app.run();
    }
}
