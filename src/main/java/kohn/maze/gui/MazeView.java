package kohn.maze.gui;

import kohn.maze.Cell;
import kohn.maze.Maze;

import javax.swing.*;
import java.awt.*;
public class MazeView extends JComponent {

    Maze maze;


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        maze = new Maze(5, 5);
        g2.setColor(Color.GREEN);

        BasicStroke wideStroke = new BasicStroke(4.5f);
        g2.setStroke(wideStroke);
        g2.drawRect(0, 1, 390, 500);


        maze.generateMaze();
        maze.createPath();
        System.out.println(maze.toString());
        //  paintBorder(g2, Color.black);
        // paintCells(g2);
        //   paintGrid(g2);


    }


    public void paintBorder(Graphics g, Color c) {
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke bs = new BasicStroke(17, 2, BasicStroke.CAP_ROUND);
        g2.setColor(c);
        g2.setStroke(bs);
        g2.drawRect(1, 1, getWidth() - 1, getHeight());
    }

    public void paintCells(Graphics g) {

        int x = 0;
        int y = 0;
        //   Maze maze = new Maze(x, y);
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


    public void paintGrid(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int x = 0;
        int y = 0;
        //  Maze maze = new Maze(x, y);
        Color grey = new Color(180, 180, 180);
        BasicStroke bs = new BasicStroke(3, 1, BasicStroke.CAP_ROUND);
        g2.setStroke(bs);
        g.setColor(Color.black);

        int cellWidth = getWidth() / maze.getRows();
        int cellHeight = getHeight() / maze.getCols();

        for (x = 0; x < maze.getRows(); x++) {
            for (y = 0; y < maze.getCols(); y++) {
                g.setColor(grey);
                g.drawLine(x * cellWidth, 0, x * cellWidth, maze.getRows() * cellWidth);
            }
        }

        g.setColor(Color.black);
        for (
                int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                x = col * cellWidth;
                y = row * cellHeight;
                Cell cell = new Cell(row, col);
                if (cell.isNorth()) {
                    g.drawLine(x, y, x + cellWidth, y);
                }
                if (cell.isSouth()) {
                    g.drawLine(x + cellWidth, y + cellHeight, x, y + cellHeight);
                }
                if (cell.isEast()) {
                    g.drawLine(x + cellWidth, y + cellHeight, x + cellWidth, y);
                }
                if (cell.isWest()) {
                    g.drawLine(x, y, x, y + cellHeight);
                }
            }
        }
    }

}