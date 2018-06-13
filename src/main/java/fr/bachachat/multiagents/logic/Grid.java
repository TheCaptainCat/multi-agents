package fr.bachachat.multiagents.logic;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class Grid {
    private final int size;
    private Map<Vector, Agent> agents;
    private Semaphore semaphore;

    public Grid(int size) {
        this.size = size;
        this.agents = new ConcurrentHashMap<>();
        this.semaphore = new Semaphore(1);
    }

    public void initializeRandomAgents(int count) {
        int x = -1;
        int y = -1;
        int x2 = -1;
        int y2 = -1;
        Set<Vector> destinations = new HashSet<>();
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            while (x == -1 || y == -1 || this.agents.containsKey(new Vector(x, y))) {
                x = r.nextInt(this.size);
                y = r.nextInt(this.size);
            }
            Vector origin = new Vector(x, y);
            while (x2 == -1 || y2 == -1 || destinations.contains(new Vector(x2, y2)) || destinations.equals(origin)) {
                x2 = r.nextInt(this.size);
                y2 = r.nextInt(this.size);
            }
            Vector destination = new Vector(x2, y2);
            destinations.add(destination);
            this.agents.put(origin, new Agent(origin, destination, this));
        }
    }

    public int getSize() {
        return size;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public List<Agent> getAgents() {
        return new ArrayList<>(this.agents.values());
    }

    public Agent getAgent(Vector vector) {
        return this.agents.get(vector);
    }

    public void moveAgent(Agent agent, Vector destination) {
        if (!this.agents.containsKey(destination)) {
            this.agents.remove(agent.getPosition());
            this.agents.put(destination, agent);
            agent.setPosition(destination);
        }
    }

    public boolean isCompleted() {
        for (Agent agent : getAgents()) {
            if (!agent.hasArrived())
                return false;
        }
        return true;
    }

    public void addAgent(Agent agent) {
        this.agents.put(agent.getPosition(), agent);
    }

    public void launchAgents() {
        for (Agent agent : this.getAgents()) {
            new Thread(agent).start();
        }
    }
}
