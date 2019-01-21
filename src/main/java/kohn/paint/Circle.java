package kohn.paint;

import java.awt.*;

public class Circle extends Shape {

    private Color color;
    private double radius;
    private Point start;
    private Point end;

    public Circle(Color color){
        super(color);
        this.color = color;
    }

    @Override
    public void setStart(Point start) {
        this.start = start;
    }

    @Override
    public void setEnd(Point end) {
        super.setEnd(end);
    }

}
