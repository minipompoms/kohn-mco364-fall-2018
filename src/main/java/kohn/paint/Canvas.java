package kohn.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Canvas extends JComponent implements MouseMotionListener {

    private int x;
    private int y;

    public Canvas(){
       this.addMouseMotionListener(this);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics)g;

        g2.fillOval(x, y, 30, 30);
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        y = e.getY();
        x = e.getX();
        System.out.println(e.getX() + ", "+ e.getY());
        repaint(x, y,25 , 25);
    }



}
