package dev.tobycook.app.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;


/**
 * The type File handler.
 * @author Toby Cook (40316565)
 */
public class FileHandler {

    /**
     * Instantiates a new File handler.
     */
    public FileHandler() {}

    /**
     * Reads data from a file given its file name.
     *
     * @param fileName the file name
     * @return the string of data in the file
     */
    public String read(String fileName) {
        try {
            File file = new File(fileName);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.out.println("Failed to read file");
            return "";
        }
    }


    /**
     * Write data to a file.
     *
     * @param fileName the file name
     * @param data     the data to write to the file
     */
    public void save(String fileName, String data) {
        try {
            FileWriter writer = new FileWriter(fileName.concat(".csn"));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
