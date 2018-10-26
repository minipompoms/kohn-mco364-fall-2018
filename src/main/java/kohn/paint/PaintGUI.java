package kohn.paint;

import javax.swing.*;

public class PaintGUI extends JFrame  {
    Canvas canvas;
    public PaintGUI(){
        setTitle("Paint");
        setSize(600, 450);
        setLocation(510, 250);
        canvas = new Canvas();

        add(canvas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main (String[]args){
        new PaintGUI().setVisible(true);
    }

}
