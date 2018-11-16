package kohn.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JComponent implements MouseMotionListener, MouseListener {

    private List<Shape> shapes = new ArrayList<>();
    private Tool tool;
    private Point start;
    private Point end;
    private Color color = Color.black;
    private Rectangle rectangle;

    public Canvas() {

        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
      /*  Line line = new Line(color);
        tool = new PencilTool(line);
        shapes.add(line);*/

        start = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        end = e.getPoint();
        rectangle = new Rectangle(start, end, color);
        shapes.add(rectangle);

        tool = new ShapeTool(rectangle);

        tool.onDrag(end);

        repaint();
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        rectangle = null;

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }


    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }


}
