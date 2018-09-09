package kohn.maze;

import java.util.Random;
import java.util.Stack;

public class Maze {

    private int rows;
    private int cols;
    private Cell[][] maze;
    private Stack<Cell> stack = new Stack<>();


    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        maze = new Cell[rows][cols];
    }

    private void generateMaze() {
        int x, y;
        for (x = 0; x < rows; x++) {
            for (y = 0; y < cols; y++) {
                Cell cell = new Cell(x, y);//creates new cell with coordinates
                maze[x][y] = cell; //assigns this cell to the 2D array
                cell.visited = false; //cell has not been visited yet
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

        while (!containsUnvisitedCells()) {
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
        if (cell.getY() != 0) { //assigns left cell coordinates to north cell of cell
            cell.neighbors.add(maze[cell.getX()][cell.getY() - 1]);
        }

        if (cell.getX() != 0) { //assigns left to cell if its not the first column
            cell.neighbors.add(maze[cell.getX() - 1][cell.getY()]);
        }

        if (cell.getX() != rows - 1) { //assigns neighbor to the right of cell if not last column
            cell.neighbors.add(maze[cell.getX() + 1][cell.getY()]);
        }

        if (cell.getY() != cols - 1) {//bottom neighbor if not last row
            cell.neighbors.add(maze[cell.getX()][cell.getY() + 1]);
        }
    }

    private Cell getRandomNeighbor(Cell cell) {
        if (cell.neighbors.size() == 0) {
            return null;
        }
        Random random = new Random();
        cell = cell.neighbors.get(random.nextInt(cell.neighbors.size()));

        return cell;
    }

    private void removeWalls(Cell current, Cell neighbor) {
        System.out.println("Remove wall between (row " + current.getX() + ", col " + current.getY() + ") &  (row " +
                neighbor.getX() + ", col " + neighbor.getY() +")");
        int x = current.getX();
        int y = current.getY();
        //bottom cell = remove south wall from current & top from neighbor
        if (x == neighbor.getX() + 1 && y == neighbor.getY()) {
            current.south = false;
            neighbor.north = false;

        }
        //right cell = remove right wall from current & left from neighbor
        else if (x == neighbor.getX() && y == neighbor.getY() + 1) {
            current.east = false;
            neighbor.west = false;
        }
        //left cell = remove left wall from current & right from neighbor
        else if (x == neighbor.getX() && y == neighbor.getY() - 1) {
            current.west = false;
            neighbor.east = false;
        }
        //top cell = remove top from current & bottom from neighbor
        else if (x == neighbor.getX() - 1 && y == neighbor.getY()) {
            current.north = false;
            neighbor.south = false;
        }

        current.neighbors.remove(neighbor);
        neighbor.neighbors.remove(current);
        displayMaze();
    }

    private int removeVisitedNeighbors(Cell cell) {
        for (int i = 0; i < cell.neighbors.size(); i++) {
            cell.neighbors.removeIf(c -> c.visited);
        }
        return cell.neighbors.size();
    }

    private boolean containsUnvisitedCells() {
        int x, y;
        for (x = 0; x < rows; x++) {
            for (y = 0; y < cols; y++) {
                if (!maze[x][y].visited) {
                    return false;
                }
            }
        }
        return true;
    }

    private void displayMaze() {
        int x, y;
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        for (y = 0; y < cols-1; y++) {
            sb.append("+---");
        }

        for (x = 0; x < rows; x++) {
            for (y = 0; y < cols; y++) {
                if (maze[y][x].north && x > 0) {
                    sb.append("+---");
                }
                else if(x > 0){
                    sb.append("+   ");
                }
            }
            sb.append("+\n");

            for (y = 0; y < cols; y++) {
                if (maze[y][x].west || y == 0) {
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
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Maze m = new Maze(5, 5);
        m.generateMaze();
        m.displayMaze();
        m.createPath();
        m.displayMaze();
    }
}
