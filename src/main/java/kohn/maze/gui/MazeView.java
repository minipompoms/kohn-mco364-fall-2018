package kohn.maze.gui;

import kohn.maze.Maze;

import javax.swing.*;
import java.awt.*;

public class MazeView extends JComponent {

    private Maze maze;
    private int rows;
    private int cols;

    public void createMaze() {
        maze = new Maze(rows, cols);
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke wideStroke = new BasicStroke(4.5f);
        g2.setStroke(wideStroke);
        maze.generateMaze();
        paintCells(g2);
        maze.createPath();
        paintGrid(g2);
        System.out.println(maze.toString());
    }

    public void paintCells(Graphics g) {

        int x;
        int y;
        Graphics2D g2 = (Graphics2D) g;

        Color grey = new Color(180, 180, 180);

        BasicStroke bs = new BasicStroke(3, 1, BasicStroke.CAP_ROUND);

        g2.setStroke(bs);
        g.setColor(Color.black);

        int cellWidth = getWidth() / maze.getRows();
        int cellHeight = getHeight() / maze.getCols();

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

        Graphics2D g2 = (Graphics2D) g;

        Color grey = new Color(180, 180, 180);
        BasicStroke bs = new BasicStroke(3, 1, BasicStroke.CAP_ROUND);
        g2.setStroke(bs);
        g.setColor(Color.black);

        int cellWidth = getWidth() / maze.getRows();
        int cellHeight = getHeight() / maze.getCols();

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

}