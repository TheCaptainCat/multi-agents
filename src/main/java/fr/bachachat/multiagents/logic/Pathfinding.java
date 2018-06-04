package fr.bachachat.multiagents.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pathfinding {
    private static double heuristic(Vector v1, Vector v2) {
        return v1.distanceTo(v2);
    }

    private static void computeNeighbours(Grid grid, Map<Vector,
            Double> neighbours, Set<Vector> visited, Vector position, Vector destination, double cost) {
        if (position.getX() > 0) {
            Vector neighbour = new Vector(position.getX() - 1, position.getY());
            if (!visited.contains(neighbour)) {
                neighbours.put(neighbour, heuristic(neighbour, destination) + cost);
            }
        }
        if (position.getX() < grid.getSize() - 1) {
            Vector neighbour = new Vector(position.getX() + 1, position.getY());
            if (!visited.contains(neighbour)) {
                neighbours.put(neighbour, heuristic(neighbour, destination) + cost);
            }
        }
        if (position.getY() > 0) {
            Vector neighbour = new Vector(position.getX(), position.getY() - 1);
            if (!visited.contains(neighbour)) {
                neighbours.put(neighbour, heuristic(neighbour, destination) + cost);
            }
        }
        if (position.getY() < grid.getSize() - 1) {
            Vector neighbour = new Vector(position.getX(), position.getY() + 1);
            if (!visited.contains(neighbour)) {
                neighbours.put(neighbour, heuristic(neighbour, destination) + cost);
            }
        }
    }

    public static Vector nextPosition(Grid grid, Agent agent) {
        Vector position = agent.getPosition();
        Map<Vector, Double> neighbours = new HashMap<>();
        double cost = 0;
        Set<Vector> visited = new HashSet<>();
        do {
            cost++;
            visited.add(position);
            computeNeighbours(grid, neighbours, visited, position, agent.getDestination(), cost);
            Vector minVector = null;
            double minCost = Double.POSITIVE_INFINITY;
            for (Vector vector : neighbours.keySet()) {
                if (neighbours.get(vector) < minCost) {
                    minCost = neighbours.get(vector);
                    minVector = vector;
                }
            }
            position = minVector;
        } while (!(neighbours.isEmpty() || position.equals(agent.getDestination())));
        return position;
    }
}
