package com.bth.Game.Util;

import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    private static PrintStream out = System.out;
    static Scanner in = new Scanner(System.in);

    public static void write(String text) {
        UI.out.println("\t" + text);
    }

    public static void write(String text, Object ... args) {
        UI.out.printf("\t" + text + "%n", args);
    }

    public static void write(String[] lines) {
        for (String line: lines) {
            UI.write(line);
        }
    }
}
