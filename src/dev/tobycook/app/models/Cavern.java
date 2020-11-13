package dev.tobycook.app.models;

/**
 * The type Cavern.
 * @author Toby Cook (40316565)
 */
public class Cavern implements Comparable<Cavern> {

    /** The cavern number. */
    private final int number;

    /** The x coordinates. */
    private final int x;

    /** The y coordinates. */
    private final int y;

    /** The f score. */
    private double fScore;

    /**
     * Instantiates a new Cavern.
     *
     * @param number the number
     * @param x      the x
     * @param y      the y
     */
    public Cavern(int number, int x, int y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets f score.
     *
     * @param fScore the f score
     */
    public void setFScore(double fScore) {
        this.fScore = fScore;
    }

    /**
     * Gets f score.
     *
     * @return the f score
     */
    public double getFScore() {
        return fScore;
    }

    /**
     * @see java.lang.Comparable
     */
    @Override
    public int compareTo(Cavern o) {
        return Double.compare(this.fScore, o.fScore);
    }
}
