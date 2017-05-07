package com.bth.Game.Util;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public interface PrinterInterface {
    void println(String line);
    int printCommandMenu(ArrayList<EventInterface> menu);
    void setOut(PrintStream out);
    void setIn(InputStream in);
    void printAvailableMoves(List<String> possibleMoves);
    void printPlayerMoved(String direction);
    void printPlayerMoveFail();
    void printPlayerStuck();
    void printCurrentHealth(int health);
    Commands showGameDialog();
    int printMenu(ArrayList<String> menu);
    int printMenu(String question, ArrayList<String> menu);
}
