package fr.bachachat.multiagents.logic.messages;

import fr.bachachat.multiagents.logic.Agent;
import fr.bachachat.multiagents.logic.Vector;

public class MoveOrder implements Order {
    private Agent emiter;
    private Vector source;
    private Agent receiver;
    private Vector destination;

    public MoveOrder(Agent emiter, Vector source, Agent receiver, Vector destination) {
        this.emiter = emiter;
        this.source = source;
        this.receiver = receiver;
        this.destination = destination;
    }
}
