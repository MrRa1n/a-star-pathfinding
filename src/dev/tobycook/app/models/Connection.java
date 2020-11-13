package dev.tobycook.app.models;

/**
 * The type Connection.
 * @author Toby Cook (40316565)
 */
public class Connection {

    /** The parent cavern. */
    private final Cavern parent;

    /** The child cavern. */
    private final Cavern child;

    /**
     * Instantiates a new Connection.
     *
     * @param parent the parent
     * @param child  the child
     */
    public Connection(Cavern parent, Cavern child) {
        this.parent = parent;
        this.child = child;
    }

    /**
     * Gets parent.
     *
     * @return the parent
     */
    public Cavern getParent() {
        return parent;
    }

    /**
     * Gets child.
     *
     * @return the child
     */
    public Cavern getChild() {
        return child;
    }
}
