package kohn.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Canvas extends JComponent implements MouseMotionListener, MouseListener {
    private ArrayList<Line2D> lines;
    private Toolkit toolkit;
    private Image image;
    private Line2D line;
    private Point prev;
    private Point next;
    private BufferedImage bufferedImage;
    private Graphics2D g2;

    public Canvas(){
        bufferedImage=  new BufferedImage(500, 600, BufferedImage.TYPE_INT_ARGB);
        g2 = bufferedImage.createGraphics();
        next = new Point();
        line = new Line2D.Double();
        lines = new ArrayList<>();
        toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage("src/images/pencil_cursor.png");
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        Color grey = new Color(219, 219, 219);
        g2.setColor(grey);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);

        g2.setColor(Color.black);
        for(Line2D line : lines){
          draw(line);
      }
    }



    @Override
    public void mouseDragged(MouseEvent e) {
        if (prev != null) {
            next = e.getPoint();
            line = new Line2D.Double(prev, next);
            lines.add(line);

            repaint();
            prev = next;
            mouseClicked(e);
            Cursor cursor = toolkit.createCustomCursor(image , new Point(getX(),
                    getY()), "pencil_cursor.jpg");
            e.getComponent().setCursor(cursor);
            repaint();
        }

    }

    private void draw(Line2D line){
        g2.setColor(Color.black);
        g2.draw(line);
    }

    @Override
    public void mouseMoved(MouseEvent e) {



    }


    public void mouseClicked(MouseEvent e){
        if(SwingUtilities.isRightMouseButton(e)){
            clearCanvas();
        }
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

    public void clearCanvas(){

        repaint();

    }
}
