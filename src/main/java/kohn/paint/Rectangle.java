package kohn.paint;

import java.awt.*;

public class Rectangle extends Shape {

    private Color color;
    private Point start;
    private Point end;


    public Rectangle(Point start, Point end, Color color) {
        super(color);
        this.color = color;
        this.start = start;
        this.end = end;
    }

    public Color getColor(){
        return color;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int w = Math.abs(start.x - end.x);
        int h = Math.abs(start.y - end.y);
        graphics.drawRect(x, y, w, h);
    }
}
