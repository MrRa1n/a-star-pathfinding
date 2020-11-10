package dev.tobycook.app.data;

import dev.tobycook.app.models.Cavern;
import dev.tobycook.app.models.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


public class DataParser {

    private final ListIterator<String> iterator;

    private final int totalCaverns;

    public DataParser(String data) {
        this.iterator = Arrays.asList(data.split(",")).listIterator();
        this.totalCaverns = Integer.parseInt(iterator.next());
    }

    public List<Cavern> getCavernInformation() {
        List<Cavern> caverns = new ArrayList<>();
        int cavernNumber = 1;

        for (int i = 0; i < totalCaverns; i++) {
            int x = Integer.parseInt(iterator.next());
            int y = Integer.parseInt(iterator.next());

            caverns.add(new Cavern("Cavern " + cavernNumber++, x, y));
        }

        return caverns;
    }

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

}
