package fr.bachachat.multiagents.logic;

import fr.bachachat.multiagents.logic.behaviors.Behavior;
import fr.bachachat.multiagents.logic.behaviors.MessageBehavior;
import fr.bachachat.multiagents.logic.messages.Message;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Agent implements Runnable {
    private static int IDS = 0;
    private static long PAUSE_TIME = 200;

    private int id;
    private long pauseTime = new Random().nextInt(100) + 200;
    private Vector position;
    private Vector destination;
    private Grid grid;
    private boolean running;
    private Behavior behavior;
    private Queue<Message> messages;

    public Agent(Vector position, Vector destination, Grid grid) {
        System.out.println(pauseTime);
        this.id = Agent.IDS++;
        this.position = position;
        this.destination = destination;
        this.grid = grid;
        this.running = false;
        this.behavior = new MessageBehavior(this.grid, this);
        this.messages = new ConcurrentLinkedQueue<>();
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

    public Message popMessage() {
        return this.messages.size() > 0 ? this.messages.poll() : null;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
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
                Thread.sleep(this.pauseTime);
                this.grid.getSemaphore().acquire();
                this.behavior.moveAgent();
                this.grid.getSemaphore().release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Agent(%d, %s)", this.id, this.position.toString());
    }
}
