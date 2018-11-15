package kohn.paint;

import java.awt.*;

public class Line {

    private Point start;
    private Point end;
    private Color color;

    public Line(Point start, Point end, Color color){
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

}
