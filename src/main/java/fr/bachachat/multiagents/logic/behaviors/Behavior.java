package fr.bachachat.multiagents.logic.behaviors;

import fr.bachachat.multiagents.logic.Agent;
import fr.bachachat.multiagents.logic.Grid;

public abstract class Behavior {
    protected Grid grid;
    protected Agent agent;

    public Behavior(Grid grid, Agent agent) {
        this.grid = grid;
        this.agent = agent;
    }

    public abstract void moveAgent();
}
