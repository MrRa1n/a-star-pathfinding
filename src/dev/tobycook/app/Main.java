package dev.tobycook.app;

import dev.tobycook.app.data.DataParser;
import dev.tobycook.app.data.FileReader;
import dev.tobycook.app.models.Cavern;
import dev.tobycook.app.models.Connection;
import dev.tobycook.app.pathfinding.AStar;

import java.util.Deque;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileReader reader = new FileReader();
        String cavernData = "";

        if (args.length > 0) {
            cavernData = reader.read(args[0]);
        }

        DataParser parser = new DataParser(cavernData);

        List<Cavern> caverns = parser.getCavernInformation();

        List<Connection> connections = parser.getConnections(caverns);

        AStar a = new AStar(caverns.get(0), caverns.get(caverns.size()-1));

        Deque<Cavern> path = a.findPath(connections);

        System.out.println(path.toString());
    }
}
