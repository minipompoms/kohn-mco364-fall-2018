package kohn.paint;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public void draw(Graphics imageGraphics, BufferedImage bufferedImage){
        imageGraphics.setColor(color);
    }

}
