package kohn.paint;

import java.awt.*;

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
    public void draw(Graphics graphics) {
        super.draw(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setStroke(new BasicStroke(1));

        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int w = Math.abs(start.x - end.x);
        int h = Math.abs(start.y - end.y);
        if(fill){
            graphics.fillRect(x, y, w, h);
        }
        else{
            graphics.drawRect(x, y, w, h);
        }
    }

}
