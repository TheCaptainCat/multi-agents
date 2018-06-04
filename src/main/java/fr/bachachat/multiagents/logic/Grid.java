package fr.bachachat.multiagents.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grid {
    private final int size;
    private HashMap<Vector, Agent> agents;

    public Grid(int size) {
        this.size = size;
        this.agents = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public List<Agent> getAgents() {
        return new ArrayList<>(this.agents.values());
    }

    public boolean isCompleted() {
        for (Agent agent : getAgents()) {
            if (!agent.hasArrived())
                return false;
        }
        return true;
    }
}
