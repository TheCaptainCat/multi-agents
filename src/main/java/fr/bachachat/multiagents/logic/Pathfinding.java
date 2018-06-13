package fr.bachachat.multiagents.logic;

import java.util.*;
import java.util.function.Predicate;

public class Pathfinding {
    private static double heuristic(Vector v1, Vector v2) {
        return v1.distanceTo(v2);
    }

    private static void computeNeighbours(Grid grid, Map<Vector, Double> neighbours, Set<Vector> visited,
                                          Vector position, Vector destination, double cost) {
        Integer[] xs = new Integer[]{-1, 1, 0, 0};
        Integer[] ys = new Integer[]{0, 0, -1, 1};
        List<Predicate<Vector>> predicates = new ArrayList<>();
        predicates.add((Vector v) -> v.getX() > 0);
        predicates.add((Vector v) -> v.getX() < grid.getSize() - 1);
        predicates.add((Vector v) -> v.getY() > 0);
        predicates.add((Vector v) -> v.getY() < grid.getSize() - 1);
        for (int i = 0; i < 4; i++) {
            if (predicates.get(i).test(position)) {
                Vector neighbour = new Vector(position.getX() + xs[i], position.getY() + ys[i]);
                if (!visited.contains(neighbour)) {
                    neighbours.put(neighbour, heuristic(neighbour, destination) + cost);
                }
            }
        }
    }

    public static Vector nextPosition(Grid grid, Agent agent) {
        Vector position = agent.getPosition();
        List<Vector> path = new ArrayList<>();
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
            neighbours.remove(minVector);
            path.add(minVector);
            position = minVector;
        } while (!(neighbours.isEmpty() || position.equals(agent.getDestination())));
        return path.get(0);
    }
}
