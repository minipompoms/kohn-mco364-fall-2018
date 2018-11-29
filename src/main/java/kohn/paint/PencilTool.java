package kohn.paint;

import java.awt.*;

public class PencilTool implements Tool {

    private Color color;
    private Line line;

    public PencilTool(){


    }


    @Override
    public Shape getShape() {
        return line;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void mousePressed(Point point) {
        line = new Line(color);
    }



    @Override
    public void mouseDragged(Point point) {
        line.getList().add(new Point(point));
    }

    @Override
    public void mouseReleased(Point point) {

    }
}
