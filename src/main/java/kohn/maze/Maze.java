package kohn.maze;

import java.util.Random;
import java.util.Stack;

public class Maze {

    private int rows;
    private int cols;
    private Cell[][] maze;
    private Stack<Cell> stack = new Stack<>();
    private final Random RANDOM = new Random();


    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        maze = new Cell[rows][cols];
    }

    private void generateMaze() {
        int x, y;
        for (x = 0; x < rows; x++) {
            for (y = 0; y < cols; y++) {
                Cell cell = new Cell(x, y);
                maze[x][y] = cell;
            }
        }

        for (x = 0; x < rows; x++) {
            for (y = 0; y < cols; y++) {
                assignNeighbors(maze[x][y]);
            }
        }
    }

    private void createPath() {
        int x = 0;
        int y = 0;
        Cell cell = maze[x][y];

        while (containsUnvisitedCells()) {
            if (removeVisitedNeighbors(cell) != 0) {
                Cell neighbor = getRandomNeighbor(cell);
                if(neighbor != null) {
                    removeWalls(cell, neighbor);
                    stack.push(cell);
                    cell = neighbor;
                    cell.visited = true;
                }
            }
            else {
                cell = stack.pop();
            }
        }
    }

    private void assignNeighbors(Cell cell) {

        if (cell.getY() != 0) { //assign left neighbor
            cell.neighbors.add(maze[cell.getX()][cell.getY() - 1]);
        }

        if (cell.getX() != 0) { //assign top neighbor
            cell.neighbors.add(maze[cell.getX() - 1][cell.getY()]);
        }

        if (cell.getX() != rows - 1) { //assign south neighbor
            cell.neighbors.add(maze[cell.getX() + 1][cell.getY()]);
        }

        if (cell.getY() != cols - 1) {//assign east neighbor
            cell.neighbors.add(maze[cell.getX()][cell.getY() + 1]);
        }
    }

    private Cell getRandomNeighbor(Cell cell) {
        if (cell.neighbors.size() == 0) {
            return null;
        }
        cell = cell.neighbors.get(RANDOM.nextInt(cell.neighbors.size()));

        return cell;
    }

    private void removeWalls(Cell current, Cell neighbor) {
       /* System.out.println("Remove wall between (row " + current.getX() + ", col " + current.getY() + ") &  (row " +
                neighbor.getX() + ", col " + neighbor.getY() +")");*/
        int x = current.getX();
        int y = current.getY();
        if (x == neighbor.getX() + 1 && y == neighbor.getY()) {
            current.north = false;
            neighbor.south = false;
        }
        //east neighbor
        else if (x == neighbor.getX() && y == neighbor.getY() + 1) {
            current.west = false;
            neighbor.east = false;
        }
        //west neighbor
        else if (x == neighbor.getX() && y == neighbor.getY() - 1) {
            current.east = false;
            neighbor.west = false;
        }
        //north neighbor
        else if (x == neighbor.getX() - 1 && y == neighbor.getY()) {
            current.south = false;
            neighbor.north = false;
        }
        current.neighbors.remove(neighbor);
        neighbor.neighbors.remove(current);
        displayMaze();
    }

    private int removeVisitedNeighbors(Cell cell) {
        cell.neighbors.removeIf(c -> c.visited);
        return cell.neighbors.size();
    }

    private boolean containsUnvisitedCells() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (!maze[x][y].visited) {
                    return true;
                }
            }
        }
        return false;
    }

    private void displayMaze() {
        int x, y;
        StringBuilder sb = new StringBuilder();

        for (x = 0; x < rows; x++) {
            for (y = 0; y < cols; y++) {
                if (maze[x][y].north ) {
                    sb.append("+---");
                }
                else {
                    sb.append("+   ");
                }
            }
            sb.append("+\n");

            for (y = 0; y < cols; y++) {
                if (maze[x][y].west) {
                    sb.append("|   ");
                }
                else {
                    sb.append("    ");
                }
            }
            sb.append("|\n");
        }

        for (y = 0; y < cols; y++) {
            sb.append("+---");
        }
        sb.append("+");
        System.out.println();
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Maze m = new Maze(4, 4);
        m.generateMaze();
        m.displayMaze();
        m.createPath();
        m.displayMaze();
    }
}
