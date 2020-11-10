package dev.tobycook.app.pathfinding;

import dev.tobycook.app.models.Cavern;
import dev.tobycook.app.models.Connection;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class AStar {

    private final PriorityQueue<Cavern> openList;

    private final Map<Cavern, Cavern> cameFrom;
    private final Map<Cavern, Double> gScore;

    private final Cavern start;
    private final Cavern goal;

    public AStar(Cavern start, Cavern goal) {
        openList = new PriorityQueue<>();
        cameFrom = new HashMap<>();
        gScore = new HashMap<>();

        this.start = start;
        this.goal = goal;
    }

    private double calculateCost(Cavern start, Cavern goal) {
        return Math.sqrt(Math.pow((start.getX() - goal.getX()), 2)
                + Math.pow((start.getY() - goal.getY()), 2));
    }

    public Deque<Cavern> findPath(List<Connection> connections) {
        openList.add(start);
        gScore.put(start, 0.0);
        start.setFScore(0.0);

        while (!openList.isEmpty()) {
            // Get the current Cavern with the lowest fScore
            Cavern current = openList.poll();

            // get rid of fscore ?
            if (current.equals(goal)) {
                double gScoreTotal = 0.0;
                for (double d : gScore.values()) {
                    gScoreTotal += d;
                }
                System.out.println(gScoreTotal);
                System.out.println(gScore);
                return reconstructPath(cameFrom, current);
            }

            openList.remove(current); // wont need this maybe

            List<Connection> cd = connections.stream()
                .filter(c -> c.getParent().equals(current))
                .collect(Collectors.toList());

            for (Connection c : cd) {
                Cavern neighbour = c.getChild();

                // Weight of the edge
                double tentativeGScore = gScore.getOrDefault(current, Double.POSITIVE_INFINITY)
                    + calculateCost(current, neighbour);

                if (tentativeGScore < gScore.getOrDefault(neighbour, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbour, current);
                    gScore.put(neighbour, tentativeGScore);
                    neighbour.setFScore(gScore.getOrDefault(neighbour, Double.POSITIVE_INFINITY)
                        + calculateCost(neighbour, goal));

                    if (!openList.contains(neighbour)) {
                        openList.add(neighbour);
                    }
                }
            }
        }

        return null;
    }

    private Deque<Cavern> reconstructPath(Map<Cavern, Cavern> cameFrom, Cavern current) {
        Deque<Cavern> totalPath = new ArrayDeque<>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.addFirst(current);
        }

        return totalPath;
    }
}
