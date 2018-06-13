package fr.bachachat.multiagents.logic;

public class Agent implements Runnable {
    private static int IDS = 0;
    private static long PAUSE_TIME = 1000;

    private int id;
    private Vector position;
    private Vector destination;
    private Grid grid;
    private boolean running;

    public Agent(Vector position, Vector destination, Grid grid) {
        this.id = Agent.IDS++;
        this.position = position;
        this.destination = destination;
        this.grid = grid;
        this.running = false;
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
                if (!this.position.equals(this.destination)) {
                    Vector vector = Pathfinding.nextPosition(this.grid, this);
                    this.grid.moveAgent(this, vector);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
