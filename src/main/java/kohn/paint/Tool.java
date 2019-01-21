package kohn.paint;

import java.awt.*;

interface Tool {

    Shape getShape();
    void setColor(Color color);
    void isFilled(boolean fill);
    void mousePressed(Point point);
    void mouseDragged(Point point);
    void mouseReleased(Point point);
}
