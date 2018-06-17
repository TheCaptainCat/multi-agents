package fr.bachachat.multiagents.logic.messages;

import fr.bachachat.multiagents.logic.Agent;
import fr.bachachat.multiagents.logic.Vector;

public class MoveOrder implements Order {
    private Agent emitter;
    private Vector source;
    private Agent receiver;
    private Vector destination;

    public MoveOrder(Agent emitter, Agent receiver) {
        this.emitter = emitter;
        this.source = emitter.getPosition())
        this.receiver = receiver;
        this.destination = receiver.getPosition();
    }

    public Agent getEmitter() {
        return emitter;
    }

    public Vector getSource() {
        return source;
    }

    public Agent getReceiver() {
        return receiver;
    }

    public Vector getDestination() {
        return destination;
    }
}
