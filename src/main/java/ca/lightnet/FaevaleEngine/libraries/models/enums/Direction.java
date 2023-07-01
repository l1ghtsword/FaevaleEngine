package ca.lightnet.FaevaleEngine.libraries.models.enums;


import org.bukkit.util.Vector;

public enum Direction {
    NORTH(new Vector(0,0,-1)),
    EAST(new Vector(1,0,0)),
    SOUTH(new Vector(0,0,1)),
    WEST(new Vector(-1,0,0)),
    UP(new Vector(0,1,0)),
    DOWN(new Vector(0,-1,0));

    private final Vector vector;

    Direction(Vector vector) {
        this.vector = vector;
    }

    public Vector getVector() {
        return vector;
    }
}
