package dev.tobycook.app.models;

public class Connection {
    private final Cavern parent;
    private final Cavern child;

    public Connection(Cavern parent, Cavern child) {
        this.parent = parent;
        this.child = child;
    }

    public Cavern getParent() {
        return parent;
    }

    public Cavern getChild() {
        return child;
    }

    @Override
    public String toString() {
        return parent.getName() + " - " + child.getName();
    }
}
