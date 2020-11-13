package dev.tobycook.app.data;

import dev.tobycook.app.models.Cavern;
import dev.tobycook.app.models.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.ListIterator;

/**
 * The type Data parser.
 * @author Toby Cook (40316565)
 */
public class DataParser {

    /** The list iterator. */
    private final ListIterator<String> iterator;

    /** The total caverns. */
    private final int totalCaverns;

    /**
     * Instantiates a new Data parser.
     *
     * @param data the data
     */
    public DataParser(String data) {
        this.iterator = Arrays.asList(data.split(",")).listIterator();
        this.totalCaverns = Integer.parseInt(iterator.next());
    }

    /**
     * Gets cavern information.
     *
     * @return the cavern information
     */
    public List<Cavern> getCavernInformation() {
        List<Cavern> caverns = new ArrayList<>();
        int cavernNumber = 1;
        for (int i = 0; i < totalCaverns; i++) {
            int x = Integer.parseInt(iterator.next());
            int y = Integer.parseInt(iterator.next());

            caverns.add(new Cavern(cavernNumber++, x, y));
        }
        return caverns;
    }

    /**
     * Gets connections.
     *
     * @param caverns the caverns
     * @return the connections
     */
    public List<Connection> getConnections(List<Cavern> caverns) {
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < totalCaverns; i++) {
            for (int j = 0; j < totalCaverns; j++) {
                String data = iterator.next();
                if (data.equals("1")) {
                    connections.add(new Connection(caverns.get(j), caverns.get(i)));
                }
            }
        }
        return connections;
    }

    /**
     * Parse path string.
     *
     * @param path the path
     * @return the string
     */
    public String parsePath(Deque<Cavern> path) {
        if (path == null) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        for (Cavern c : path) {
            output.append(c.getNumber()).append(" ");
        }
        return output.toString();
    }
}
