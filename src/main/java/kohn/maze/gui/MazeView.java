package kohn.maze.gui;

import kohn.maze.Cell;
import kohn.maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MazeView extends JComponent {

    private int rows;
    private int cols;
    private Maze maze;

    private int cellWidth;
    private int cellHeight;

    protected ArrayList<Cell> path;
    private Point prevPoint = new Point();
    private Point nextPoint = new Point();

    private boolean start;
    int index = 0;

    public void createMaze() {
        maze = new Maze(rows, cols);
        maze.generateMaze();
        path = maze.getPathDirection();


    }

    public void onStart(boolean start) {
        this.start = start;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke wideStroke = new BasicStroke(4.5f);
        g2.setStroke(wideStroke);
        paintCells(g2);
        paintGrid(g2);
        if(start == true){
            paintRunnersPath(g2);

        }
        start = false;
    }


    public void paintCells(Graphics g) {
        index = 0;
        int x;
        int y;
        Graphics2D g2 = (Graphics2D) g;
        Color grey = new Color(180, 180, 180);
        BasicStroke bs = new BasicStroke(3, 1, BasicStroke.CAP_ROUND);

        g2.setStroke(bs);
        g.setColor(Color.black);
        cellWidth = getWidth() / maze.getRows();
        cellHeight = getHeight() / maze.getCols();

        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getRows(); col++) {
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
        for (x = 0; x < maze.getRows(); x++) {
            for (y = 0; y < maze.getCols(); y++) {
                g.setColor(grey);
                g.drawLine(x * cellWidth, 0, x * cellWidth, maze.getRows() * cellWidth);
            }
        }
    }


    public void paintGrid(Graphics g) {
        maze.createPath();

        Graphics2D g2 = (Graphics2D) g;

        Color grey = new Color(180, 180, 180);
        BasicStroke bs = new BasicStroke(3, 1, BasicStroke.CAP_ROUND);
        g2.setStroke(bs);
        g.setColor(Color.black);

        cellWidth = getWidth() / maze.getRows();
        cellHeight = getHeight() / maze.getCols();

        for (int x = 0; x < maze.getRows(); x++) {
            for (int y = 0; y < maze.getCols(); y++) {
                g.setColor(grey);
                g.drawLine(x * cellWidth, 0, x * cellWidth, maze.getRows() * cellWidth);
            }
        }

        g.setColor(Color.black);
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                int x = col * cellWidth;
                int y = row * cellHeight;
                if (maze.hasNorthCell(row, col)) {
                    g.drawLine(x, y, x + cellWidth, y);
                }
                if (maze.hasSouthCell(row, col)) {
                    g.drawLine(x + cellWidth, y + cellHeight, x, y + cellHeight);
                }
                if (maze.hasEastCell(row, col)) {
                    g.drawLine(x + cellWidth, y + cellHeight, x + cellWidth, y);
                }
                if (maze.hasWestCell(row, col)) {
                    g.drawLine(x, y, x, y + cellHeight);
                }

            }

        }
    }

    private void paintRunnersPath(Graphics g) {

        while (index < path.size() - 1) {
            MazeRunner runner = new MazeRunner(prevPoint, nextPoint);
            int y = path.get(index).getX();
            int x = path.get(index).getY();
            int nextY = path.get(index + 1).getX();
            int nextX = path.get(index + 1).getY();
            prevPoint.setLocation((cellWidth * x + 20), ((cellHeight * y) + 20));
            nextPoint.setLocation((cellWidth * nextX + 20), (cellHeight * nextY + 20));
            //   System.out.println(path.get(index).getX()+", "+path.get(index).getY());
            runner.drawRunner(g);
            Color transparent = new Color(60, 60, 60, 15);
            g.setColor(transparent);
            index++;
            g.fillOval(prevPoint.x, prevPoint.y, 45, 35);

        }


    }


    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

}