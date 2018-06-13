package fr.bachachat.multiagents.logic.behaviors;

import fr.bachachat.multiagents.logic.Agent;
import fr.bachachat.multiagents.logic.Grid;
import fr.bachachat.multiagents.logic.Pathfinding;
import fr.bachachat.multiagents.logic.Vector;
import fr.bachachat.multiagents.logic.messages.Message;
import fr.bachachat.multiagents.logic.messages.MoveOrder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MessageBehavior extends Behavior {
    public MessageBehavior(Grid grid, Agent agent) {
        super(grid, agent);
    }

    @Override
    public void moveAgent() {
        Vector vector = null;
        Message message = this.agent.popMessage();
        boolean reading = true;
        while (reading && message != null) {
            if (message instanceof MoveOrder) {
                MoveOrder moveOrder = (MoveOrder) message;
                if (!(moveOrder.getDestination() != this.agent.getPosition() || moveOrder.getSource() != moveOrder.getEmitter().getPosition())) {
                    Map<Vector, Double> neighbours = new HashMap<>();
                    Pathfinding.computeNeighbours(this.grid, neighbours, new HashSet<>(), this.agent.getPosition(), this.agent.getDestination(), 0);
                    neighbours.remove(((MoveOrder) message).getSource());
                    vector = Pathfinding.findBestCandidate(neighbours);
                    reading = false;
                }
            }
            if (reading)
                message = this.agent.popMessage();
        }
        if (vector == null && !this.agent.getPosition().equals(this.agent.getDestination()))
            vector = Pathfinding.nextPosition(this.grid, this.agent);
        if (vector != null) {
            Agent agent = this.grid.getAgent(vector);
            if (agent == null) {
                this.grid.moveAgent(this.agent, vector);
            } else {
                agent.addMessage(new MoveOrder(this.agent, agent));
            }
        }
    }
}
