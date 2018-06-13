package fr.bachachat.multiagents.logic;

import fr.bachachat.multiagents.logic.behaviors.Behavior;
import fr.bachachat.multiagents.logic.behaviors.SimpleBehavior;

public class Agent implements Runnable {
    private static int IDS = 0;
    private static long PAUSE_TIME = 1000;

    private int id;
    private Vector position;
    private Vector destination;
    private Grid grid;
    private boolean running;
    private Behavior behavior;

    public Agent(Vector position, Vector destination, Grid grid) {
        this.id = Agent.IDS++;
        this.position = position;
        this.destination = destination;
        this.grid = grid;
        this.running = false;
        this.behavior = new SimpleBehavior(this.grid, this);
    }

    public int getId() {
        return id;
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

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
    }

    @Override
    public synchronized void run() {
        this.running = true;
        try {
            while (this.running) {
                Thread.sleep(Agent.PAUSE_TIME);
                this.grid.getSemaphore().acquire();
                this.behavior.moveAgent();
                this.grid.getSemaphore().release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
