package kohn.paint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rectangle extends Shape {

    private Color color;
    private Point start;
    private Point end;
    private boolean fill;

    public Rectangle(Color color) {
        super(color);
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public void setFilled(boolean fill) {
        this.fill = fill;
    }

    @Override
    public void setStart(Point start) {
        this.start = start;
    }

    @Override
    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void draw(Graphics imageGraphics, BufferedImage bufferedImage) {
        super.draw(imageGraphics, bufferedImage);
        Graphics2D g2 = (Graphics2D) imageGraphics;
        g2.setStroke(new BasicStroke(1));
        if(start != null && end != null){
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int w = Math.abs(start.x - end.x);
            int h = Math.abs(start.y - end.y);
            if(fill){
                imageGraphics.fillRect(x, y, w, h);
            }
            else{
                imageGraphics.drawRect(x, y, w, h);
            }
        }

    }

}
