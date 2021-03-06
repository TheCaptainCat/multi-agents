package fr.bachachat.multiagents.logic;

import java.util.Objects;

public class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceTo(Vector vector) {
        return Math.sqrt(Math.pow(this.x - vector.getX(), 2) + Math.pow(this.y - vector.getY(), 2));
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector)) {
            return false;
        }
        return ((Vector) o).getX() == this.x && ((Vector) o).getY() == this.y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.x, this.y);
    }
}
