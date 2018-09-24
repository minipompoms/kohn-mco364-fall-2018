package kohn.maze.gui;


import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Stack;

public class Maze extends JComponent {

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

    public void generateMaze() {
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
                if (neighbor != null) {
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

        int x = current.getX();
        int y = current.getY();
        //south
        if (y == neighbor.getY() && x == neighbor.getX() + 1) {
            current.north = false;
            neighbor.south = false;
        }
        //east
        else if (y == neighbor.getY() + 1 && x == neighbor.getX()) {
            current.west = false;
            neighbor.east = false;
        }
        //west
        else if (y == neighbor.getY() - 1 && x == neighbor.getX()) {
            current.east = false;
            neighbor.west = false;
        }
        //north
        else if (y == neighbor.getY() && x == neighbor.getX() - 1) {
            current.south = false;
            neighbor.north = false;
        }
        current.neighbors.remove(neighbor);
        neighbor.neighbors.remove(current);

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

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        paintCells(g2);
      //  createPath();
      //  paintGrid(g2);

        //displayMaze();
    }


    public final void paintCells(final Graphics g) {
        Color grey = new Color(180, 180, 180);
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke bs = new BasicStroke(3, 1, BasicStroke.CAP_ROUND);

        g2.setStroke(bs);
        g.setColor(Color.black);

        int cellWidth = getWidth() / rows;
        int cellHeight = getHeight() / cols;
        int x = 0;
        int y = 0;

        g.drawRect(x, y, cellWidth * x, cellHeight * y);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                x = col * cellWidth;
                y = row * cellHeight;
                if (row % 2 == 0) {
                    g.setColor(Color.lightGray);
                }
                else if (row % 2 == 1) {
                    g.setColor(grey);
                }
                g.fillRect(x, y, cellWidth, cellHeight);
            }

        }
        for (x = 0; x < rows; x++) {
            for (y = 0; y < cols; y++) {
                g.setColor(grey);
                g.drawLine(x * cellWidth, 0, x * cellWidth, rows * cellWidth);
            }
        }
    }

    public final void paintGrid(final Graphics g) {
        int cellWidth = getWidth() / rows;
        int cellHeight = getHeight() / cols;
        int x, y;

        g.setColor(Color.black);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                x = col * cellWidth;
                y = row * cellHeight;
                if (maze[row][col].north) {
                    g.drawLine(x, y, x + cellWidth, y);
                }
                if (maze[row][col].south) {
                    g.drawLine(x + cellWidth, y + cellHeight, x, y + cellHeight);
                }
                if (maze[row][col].east) {
                    g.drawLine(x + cellWidth, y + cellHeight, x + cellWidth, y);
                }
                if (maze[row][col].west) {
                    g.drawLine(x, y, x, y + cellHeight);
                }
            }
        }
    }

    private void displayMaze() {

        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (maze[x][y].north) {
                    sb.append("+---");
                }
                else {
                    sb.append("+   ");
                }
            }
            sb.append("+\n");
            for (int y = 0; y < cols; y++) {
                if (maze[x][y].west) {
                    sb.append("|   ");
                }
                else {
                    sb.append("    ");
                }
            }
            sb.append("|\n");
        }
        for (int y = 0; y < cols; y++) {
            sb.append("+---");
        }
        sb.append("+");
        System.out.println(sb.toString());
    }

}