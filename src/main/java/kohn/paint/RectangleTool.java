package kohn.paint;

import java.awt.*;

public class RectangleTool implements Tool {

    private Rectangle rectangle;
    private Color color;
    private boolean fill;

    public RectangleTool() {
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void isFilled(boolean fill) {
        this.fill = fill;
    }

    @Override
    public Rectangle getShape() {
        return rectangle;
    }

    @Override
    public void mousePressed(Point start) {
        rectangle = new Rectangle(color);
        rectangle.setFilled(fill);
        rectangle.setStart(start);
    }

    @Override
    public void mouseDragged(Point end) {
        rectangle.setEnd(end);

    }

    @Override
    public void mouseReleased(Point end) {
        rectangle.setEnd(end);

    }

}