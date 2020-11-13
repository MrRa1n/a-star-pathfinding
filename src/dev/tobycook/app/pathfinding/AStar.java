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

/**
 * The type A star.
 * @author Toby Cook (40316565)
 */
public class AStar {

    /** The open list. */
    private final PriorityQueue<Cavern> openList;

    /** Map of caverns came from. */
    private final Map<Cavern, Cavern> cameFrom;

    /** The g score. */
    private final Map<Cavern, Double> gScore;

    /** The start cavern. */
    private final Cavern start;

    /** The goal cavern. */
    private final Cavern goal;

    /**
     * Instantiates a new A star.
     *
     * @param start the start
     * @param goal  the goal
     */
    public AStar(Cavern start, Cavern goal) {
        openList = new PriorityQueue<>();
        cameFrom = new HashMap<>();
        gScore = new HashMap<>();

        this.start = start;
        this.goal = goal;
    }

    /**
     * Calculate the path cost using Euclidean formula.
     *
     * @param start the start cavern
     * @param goal the goal cavern
     * @return the calculated cost
     */
    private double calculateCost(Cavern start, Cavern goal) {
        return Math.sqrt(Math.pow((goal.getX() - start.getX()), 2)
                + Math.pow((goal.getY() - start.getY()), 2));
    }

    /**
     * Find path deque.
     *
     * @param connections the connections
     * @return the deque
     */
    public Deque<Cavern> findPath(List<Connection> connections) {
        start.setFScore(0.0);
        openList.add(start);
        gScore.put(start, 0.0);

        while (!openList.isEmpty()) {
            // Get the current Cavern with the lowest fScore
            Cavern current = openList.poll();

            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

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

                    if (!openList.contains(neighbour)) {
                        neighbour.setFScore(gScore.getOrDefault(neighbour, Double.POSITIVE_INFINITY)
                            + calculateCost(neighbour, goal));
                        openList.add(neighbour);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Reconstruct the path from the start cavern to goal cavern.
     *
     * @param cameFrom the map of nodes came from
     * @param current the current node
     * @return the path
     */
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
