package kohn.paint;

import java.awt.*;

public class Shapes {

    private Point start;
    private Point end;
    private Color color;
    private Rectangle rectangle;

    public Shapes(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }


    public Point getStart() {
        return start;
    }


    public Point getEnd() {
        return end;
    }

    public Color getColor() {
        return color;
    }


    public void createRectangle(Point end) {
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int w = Math.abs(start.x - end.x);
        int h = Math.abs(start.y - end.y);
        rectangle = new Rectangle(x, y, w, h);
    }

    public Rectangle getRectangle(){
        createRectangle(end);
        return rectangle;
    }



}
