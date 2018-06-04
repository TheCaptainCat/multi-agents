package fr.bachachat.multiagents.logic;

public class Agent implements Runnable {
    private Vector position;
    private Vector destination;
    private Grid grid;

    public Agent(Vector position, Vector destination, Grid grid) {
        this.position = position;
        this.destination = destination;
        this.grid = grid;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getDestination() {
        return destination;
    }

    public boolean hasArrived() {
        return this.position.equals(this.destination);
    }

    public void move(int x, int y) {
        this.position = new Vector(x, y);
    }

    @Override
    public void run() {

    }
}
