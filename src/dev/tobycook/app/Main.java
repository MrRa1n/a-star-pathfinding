package dev.tobycook.app;

import dev.tobycook.app.data.DataParser;
import dev.tobycook.app.data.FileHandler;
import dev.tobycook.app.models.Cavern;
import dev.tobycook.app.models.Connection;
import dev.tobycook.app.pathfinding.AStar;

import java.util.Deque;
import java.util.List;

/**
 * The type Main.
 * @author Toby Cook (40316565)
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("No cavern file provided");
        }

        String fileName = args[0];

        if (!fileName.endsWith(".cav")) {
            fileName = fileName.concat(".cav");
        }

        FileHandler reader = new FileHandler();
        String cavernData = reader.read(fileName);

        DataParser parser = new DataParser(cavernData);
        List<Cavern> caverns = parser.getCavernInformation();
        List<Connection> connections = parser.getConnections(caverns);

        AStar a = new AStar(caverns.get(0), caverns.get(caverns.size()-1));

        Deque<Cavern> path = a.findPath(connections);
        reader.save(fileName.replaceAll(".cav", ""), parser.parsePath(path));
    }

}
