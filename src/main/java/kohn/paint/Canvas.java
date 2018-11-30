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
    private Color color = Color.black;

    private Tool tool;


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
        tool.setColor(color);
        tool.mousePressed(e.getPoint());
        shapes.add(tool.getShape());

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        tool.mouseDragged(e.getPoint());
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tool.mouseReleased(e.getPoint());
        repaint();
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

    public void setTool(Tool tool){
        this.tool = tool;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void undo(){
        int size = shapes.size()-1;
        shapes.remove(size);
        repaint();
    }

}
