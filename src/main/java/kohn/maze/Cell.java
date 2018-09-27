package kohn.maze;

import java.util.ArrayList;

public class Cell {

    private int x;
    private int y;
    protected ArrayList<Cell> neighbors = new ArrayList<>();

    protected boolean visited;
    protected boolean north;
    protected boolean south;

    public boolean isNorth() {
        return north;
    }

    public boolean isSouth() {
        return south;
    }

    public boolean isEast() {
        return east;
    }

    public boolean isWest() {
        return west;
    }

    protected boolean east;
    protected boolean west;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        north = true;
        south = true;
        east = true;
        west = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}