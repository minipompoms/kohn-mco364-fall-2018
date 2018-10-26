package kohn.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JComponent implements MouseMotionListener {

    private ArrayList<Point> pointers;
    private Toolkit toolkit;
    private Image image;


    public Canvas(){
        pointers = new ArrayList<>();
        toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage("src/images/pencil_cursor.png");
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics)g;
        Color grey = new Color(219, 219, 219);
        g2.setColor(grey);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.black);
        int x;
        int y;
        for(int i = 0; i < pointers.size()-1; ++i){
            x =  pointers.get(i).x;
            y =  pointers.get(i).y;

            g2.drawLine(x, y, pointers.get(i+1).x, pointers.get(i+1).y);
        }

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        pointers.add(e.getPoint());
        mouseClicked(e);
        Cursor cursor = toolkit.createCustomCursor(image , new Point(getX(),
                getY()), "pencil_cursor.jpg");
        e.getComponent().setCursor(cursor);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {



    }


    public void mouseClicked(MouseEvent e){
        if(SwingUtilities.isRightMouseButton(e)){
            clearCanvas();
        }
    }

    public void clearCanvas(){
        pointers.clear();
        repaint();

    }
}
