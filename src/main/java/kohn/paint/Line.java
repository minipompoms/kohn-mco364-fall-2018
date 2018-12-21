package kohn.paint;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Line extends Shape  {
    public ArrayList<Point> lines = new ArrayList<>();
    private Point start;
    private Point end;
    private boolean stroke;

    public Line(Color color) {
        super(color);
        this.color = color;
    }

    public ArrayList<Point> getList() {
        return lines;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setStart(Point start) {
        super.setStart(start);
    }

    @Override
    public void setEnd(Point end) {
        super.setEnd(end);
    }


    public void setStroke(boolean stroke) {
        this.stroke = stroke;
    }

    @Override
    public void draw(Graphics imageGraphics, BufferedImage bufferedImage) {
        super.draw(imageGraphics, bufferedImage);
        Graphics2D g2 = (Graphics2D) imageGraphics;

        for (int i = 1; i < lines.size(); i++) {
            start = lines.get(i);
            end = lines.get(i - 1);
            if (stroke) {
                g2.setStroke(new BasicStroke(6));
            }
            else {
                g2.setStroke(new BasicStroke(1));
            }
                g2.drawLine(start.x, start.y, end.x, end.y);

        }
    }
}
