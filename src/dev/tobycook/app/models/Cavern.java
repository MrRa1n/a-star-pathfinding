package dev.tobycook.app.models;

public class Cavern implements Comparable<Cavern> {

    private final String name;

    private final int x;

    private final int y;

    private double fScore;

    public Cavern(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setFScore(double fScore) {
        this.fScore = fScore;
    }

    @Override
    public String toString() {
        return "Cavern{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", fScore=" + fScore +
                '}';
    }

    @Override
    public int compareTo(Cavern o) {
        if (this.fScore > o.fScore) return 1;
        return -1;
    }
}
