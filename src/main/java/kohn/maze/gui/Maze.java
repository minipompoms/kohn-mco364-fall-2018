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
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        paintCells(g2);
        createPath();
        repaintWall(g2);
        displayMaze();
    }


    private void paintCells(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Color gray = new Color(75,75,75);
        g.setColor(gray);
        for (int x = 0; x < rows; x++)
            for (int y=0; y < cols; y++){
                g2.fillRect(y*60, x*60, 60, 60);
            }
    }



    private void repaintWall(Graphics g) {
        Color blue = new Color(30, 80, 170);
        Color purple = new Color(100, 70, 200);
        Color gray = new Color(75,75,75);
        int y;
        int w = getWidth()/cols;
        int h = getHeight()/rows;
        for (int x = 1; x < rows; x++) {
                for ( y = 0; y < cols; y++) {

                    if (maze[x][y].north) {
                        g.setColor(gray);
                        g.drawLine( (y*40),(x*40), (y*40), (x*40)+w);


                    }
                 else if (!maze[x][y].north) {
                        g.setColor(purple);
                        g.drawLine((y*40),(y*40), y*40, (x*40)+w);

                    }

                  if (maze[x][y].west) {
                      g.setColor(gray);
                      g.drawLine( y*40,  y*40, x*40+h, y*40);

                    }
               else if(!maze[x][y].west){
                      g.setColor(purple);
                      g.drawLine( y*40,  y*40, x*40+h, y*40);



                    }
                    //System.out.println(x+", "+y);

                }

        }
    }
    private void testingWall(Graphics g) {
        int width = getSize().width;
        int height = getSize().height;

        int rowHeight = (height / rows);
        int rowWidth = (width / cols);
        Color backgroundColor = new Color(30, 30, 30);
        g.setColor(backgroundColor);
        Color wallColor = new Color(30, 80, 170);
        Color backgroundWall = new Color(220, 30, 200);
        Color z = new Color(100, 70, 200);

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (maze[x][y].north) {
                    g.setColor(backgroundColor);
                    g.fillRect(x,y,rowWidth,rowHeight);

                    g.setColor(wallColor);
                    g.drawLine(x,y,y+rowWidth, y);
                }
                else if (maze[x][y].north) {
                    g.setColor(backgroundWall);
                    g.drawLine(x, y, x+rowWidth, y);
                }

                if (maze[x][y].west) {
                    g.setColor(z);
                    g.drawLine(y, x, y+rowHeight, x);
                }
                //    else if(maze[x][y].west == false){
                //        g.setColor(backgroundWall);
                // g.drawLine(rowWidth, y, rowWidth, height);

                //  }

            }
            //  repaint();
            // g.drawLine(0, x * rowHeight, width, x * rowHeight);
            // g.drawLine(x * rowWidth, 0, x * rowWidth, height);
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

    private void displayMaze() {

        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (maze[x][y].north ) {
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
        System.out.println();
        System.out.println(sb.toString());
    }

}