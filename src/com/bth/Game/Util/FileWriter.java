package com.bth.Game.Util;

import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {

    public FileWriter() {

    }

    public void writeToFile(String filename, String text) {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
