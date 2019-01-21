package kohn.paint;

import java.awt.*;

public class EraserTool implements Tool {

    private Line line;

    public EraserTool(){

    }

    @Override
    public Shape getShape() {
        return line;
    }

    @Override
    public void setColor(Color color) {
    }

    @Override
    public void isFilled(boolean fill) {

    }

    @Override
    public void mousePressed(Point point) {
        line = new Line(Color.WHITE);
        line.setStroke(true);
    }

    @Override
    public void mouseDragged(Point point) {
        line.getList().add(new Point(point));
    }


    @Override
    public void mouseReleased(Point point) {

    }
}
