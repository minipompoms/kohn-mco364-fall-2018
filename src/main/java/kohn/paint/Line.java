package kohn.paint;

import java.awt.*;
import java.util.ArrayList;

public class Line extends Shape {

    public ArrayList<Point> lines = new ArrayList<>();
    private Point start;
    private Point end;

    public Line(Color color){
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

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);

        for(int i = 1; i < lines.size(); i++){
            start = lines.get(i);
            end = lines.get(i-1);
            graphics.drawLine(start.x, start.y, end.x, end.y);
        }
    }
}
