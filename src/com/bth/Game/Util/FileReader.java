package com.bth.Game.Util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by markus on 2017-05-04.
 */
public class FileReader {
    FileReader() {
    }

    List<String> getContents() {
        try {
            return Files.readAllLines(Paths.get("map.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
