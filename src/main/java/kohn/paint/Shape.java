package kohn.paint;

import java.awt.*;

public abstract class Shape {
    Color color;
    Point start;
    Point end;

    public Shape(Color color){
        this.color = color;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Color getColor(){
        return color;
    }

    public void draw(Graphics graphics){
        graphics.setColor(color);
    }

}
