package kohn.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JComponent implements MouseMotionListener, MouseListener {


    private ArrayList<Line> lines;
    private Line line;
    private Point prev;
    private Point next;
    private Color color = Color.black;


    public Canvas(){
        lines = new ArrayList<>();
        next = new Point();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Color grey = new Color(219, 219, 219);
        g.setColor(grey);
        g.fillRect(0, 0, getWidth(), getHeight());
        for(int i = 1; i < lines.size(); i++){
            line = lines.get(i);
            g.setColor(line.getColor());
            g.drawLine(line.getStart().x, line.getStart().y, line.getEnd().x, line.getEnd().y);
      }
    }



    @Override
    public void mouseDragged(MouseEvent e) {
        if (prev != null) {
            next = e.getPoint();
            lines.add(new Line(prev, next, color));
            repaint();
            prev = next;
           /* mouseClicked(e);
            Cursor cursor = toolkit.createCustomCursor(image , new Point(getX(),
                    getY()), "pencil_cursor.jpg");
            e.getComponent().setCursor(cursor);*/
        }

    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }


    public void mouseClicked(MouseEvent e){
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            prev = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        prev = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
