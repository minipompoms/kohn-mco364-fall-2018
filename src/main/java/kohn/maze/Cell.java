package kohn.maze;
public class Cell {

    Cell node;
    int row;
    int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getNext() {
        return node;
    }

    protected void setNext(Cell node) {
        this.node = node;
    }
}