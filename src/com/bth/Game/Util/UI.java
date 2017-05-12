package com.bth.Game.Util;

import java.io.PrintStream;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class UI {
    private static final String warning = "[!] ";
    private static final String prompt = "[?] ";
    private static final String message = "   ";
    private static final String info = "[.] ";
    private static final String BLACK = "\u001B[0;30m";
    private static final String RED = "\u001B[0;31m";
    private static final String GREEN = "\u001B[0;32m";
    private static final String YELLOW = "\u001B[0;33m";
    private static final String BLUE = "\u001B[0;34m";
    private static final String MAGENTA = "\u001B[0;35m";
    private static final String CYAN = "\u001B[0;36m";
    private static final String WHITE = "\u001B[0;37m";

    private static PrintStream out = System.out;
    static Scanner in = new Scanner(System.in);

//    public static void write(String text) {
//        UI.out.println("\t" + text);
//    }

    public static void write(String text, Object ... args) {
        if (args.length == 0) {
            UI.out.println("\t" + text);
        }
        else {
            UI.out.printf("\t" + text + "%n", args);
        }
    }

    public static void write(String[] lines) {
        for (String line: lines) {
            UI.write(line);
        }
    }

    public static void question(String text, Object ... args) {
        UI.write(BLUE + prompt + text + GREEN, args);
    }

    public static void warning(String text, Object ... args) {
        UI.write(RED + warning + text + GREEN, args);
    }

    public static void header(String text, Object ... args) {
        UI.write(BLUE + text + GREEN, args);
    }

    public static void info(String text, Object ... args) {
        UI.write(CYAN + info + text + GREEN, args);
    }

    public static void positive(String text, Object ... args) {
        UI.write(YELLOW + text + GREEN, args);
    }

}
