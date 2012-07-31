package de.otto.codelab.eternity2;

public class Piece {

    private final int north;
    private final int east;
    private final int south;
    private final int west;

    private Piece(final int north, final int east, final int south, final int west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    public int getNorth() {
        return north;
    }

    public int getEast() {
        return east;
    }

    public int getWest() {
        return west;
    }

    public int getSouth() {
        return south;
    }

    public static Piece from(final int north, final int east, final int south, final int west) {
        return new Piece(north, east, south, west);
    }

}
