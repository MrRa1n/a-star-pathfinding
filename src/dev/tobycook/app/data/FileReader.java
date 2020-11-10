package dev.tobycook.app.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileReader {

    public FileReader() {}

    public String read(String fileName) {
        try {
            File file = new File(fileName);

            return new String(Files.readAllBytes(file.toPath()));

        } catch (IOException e) {
            System.out.println("Failed to read file");
        }
        return "";
    }
}
