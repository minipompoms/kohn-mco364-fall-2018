package kohn.paint;

import javax.swing.*;
import java.awt.*;

public class PaintGUI extends JFrame {
    Canvas canvas;

    public PaintGUI() {
        setTitle("Paint");
        setSize(600, 450);
        setLocation(510, 250);

        canvas = new Canvas();

        add(canvas, BorderLayout.CENTER);
        ;

        JButton button = new JButton("CLICK ME FOR COLOR");
        add(button, BorderLayout.NORTH);
        button.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(
                    PaintGUI.this,
                    "COLORS",
                    canvas.getColor());
            canvas.setColor(newColor);

        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void addShape() {

    }

    public static void main(String[] args) {
        new PaintGUI().setVisible(true);
    }

}
