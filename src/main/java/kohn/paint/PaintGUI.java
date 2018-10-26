package kohn.paint;

import javax.swing.*;

public class PaintGUI extends JFrame  {
    Canvas canvas;
    public PaintGUI(){
        setTitle("Paint");
        setSize(400, 450);

        canvas = new Canvas();

        add(canvas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main (String[]args){
        new PaintGUI().setVisible(true);
    }

//the only time you are allowed to paint to the screen is through JComponent
}
