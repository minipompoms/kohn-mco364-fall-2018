package kohn.paint;

import java.awt.*;
import java.util.ArrayList;

public class Line extends Shape{

    public ArrayList<Point> lines = new ArrayList<>();

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
    public void draw(Graphics graphics) {
        super.draw(graphics);

        for(int i = 1; i < lines.size(); i++){
            Point start = lines.get(i);
            Point end = lines.get(i-1);
            graphics.drawLine(start.x, start.y, end.x, end.y);
        }

    }

}
