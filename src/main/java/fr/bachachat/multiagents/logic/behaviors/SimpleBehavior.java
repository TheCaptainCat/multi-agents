package fr.bachachat.multiagents.logic.behaviors;

import fr.bachachat.multiagents.logic.Agent;
import fr.bachachat.multiagents.logic.Grid;
import fr.bachachat.multiagents.logic.Pathfinding;
import fr.bachachat.multiagents.logic.Vector;

public class SimpleBehavior extends Behavior {
    public SimpleBehavior(Grid grid, Agent agent) {
        super(grid, agent);
    }

    @Override
    public void moveAgent() {
        if (!this.agent.getPosition().equals(this.agent.getDestination())) {
            Vector vector = Pathfinding.nextPosition(this.grid, this.agent);
            this.grid.moveAgent(this.agent, vector);
        }
    }
}
