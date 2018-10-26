package kohn.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JComponent implements MouseMotionListener {
    protected ArrayList<Point> pointers;


    public Canvas(){
        pointers = new ArrayList<>();
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics)g;
        Color grey = new Color(219, 219, 219);
        g2.setColor(grey);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.black);


        for(int i = 0; i < pointers.size(); ++i){
            int x = (int)pointers.get(i).getX();
            int y = (int) pointers.get(i).getY();
            g2.fillRect(x, y, 3, 3);

        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        pointers.add(e.getPoint());
        repaint();
    }

}
