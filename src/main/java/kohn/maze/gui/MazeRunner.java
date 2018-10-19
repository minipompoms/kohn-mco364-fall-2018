package kohn.maze.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class MazeRunner {

    Point prevPoint;
    Point nextPoint;
    public BufferedImage runner = null;

    public MazeRunner(Point prevPoint, Point nextPoint) {
        this.prevPoint = prevPoint;
        this.nextPoint = nextPoint;

        try {
            runner = read(new File("src/images/mario-running.png"));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void drawRunner(Graphics g) { g.drawImage(runner, nextPoint.x, nextPoint.y, null); }

}
