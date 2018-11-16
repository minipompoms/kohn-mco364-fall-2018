package kohn.paint;

import java.awt.*;

public abstract class Shape {
    Color color;

    public Shape(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public void draw(Graphics graphics){
        graphics.setColor(color);
    }

}
