package kohn.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JComponent implements MouseMotionListener, MouseListener {

    private ArrayList<Line> lines;
    private List<Shapes> sh = new ArrayList<>();

    private Shapes s;
    private Rectangle rectangle;

    private Point prev;
    private Point next;
    private Point start;
    private Point end;
    private Color color = Color.black;

    public Canvas() {
        lines = new ArrayList<>();
        next = new Point();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //  for (int i = 1; i < shapes.size(); i++) {
        // drawLines(i, g);
        // }

        for (int i = 0; i < sh.size(); i++) {
            drawRectangles(i, g2);
        }

    }


    private void drawLines(int index, Graphics g) {
        Line line = lines.get(index);
        g.setColor(line.getColor());
        g.drawLine(line.getStart().x, line.getStart().y, line.getEnd().x, line.getEnd().y);
    }


    public void drawRectangles(int index, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (rectangle != null) {
            g2.draw(rectangle);
        }
        rectangle = sh.get(index).getRectangle();

        g2.setColor(sh.get(index).getColor());
        g2.draw(rectangle);


    }

    @Override
    public void mousePressed(MouseEvent e) {
        // prev = e.getPoint();
        start = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /*if (prev != null) {
            next = e.getPoint();
            lines.add(new Line(prev, next, color));
            repaint();
            prev = next;*/
           /* mouseClicked(e);
            Cursor cursor = toolkit.createCustomCursor(image , new Point(getX(),
                    getY()), "pencil_cursor.jpg");
            e.getComponent().setCursor(cursor);*/

        end = e.getPoint();
        rectangle = createShape().getRectangle();
        repaint();
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        //  prev = null;
        rectangle = null;
        sh.add(createShape());
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


    private Shapes createShape() {
        s = new Shapes(start, end, color);
        return s;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
