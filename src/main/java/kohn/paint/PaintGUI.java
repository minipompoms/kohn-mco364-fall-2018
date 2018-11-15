package kohn.paint;

import javax.swing.*;
import java.awt.*;

public class PaintGUI extends JFrame {

    private Canvas canvas;
    private JButton shapeButton;
    private JButton lineButton;

    public PaintGUI() {
        setTitle("Paint");
        setSize(700, 550);
        setLocation(510, 250);
        canvas = new Canvas();

        JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 20, 10));
        buttonPanel.setBackground(Color.lightGray);
        shapeButton = new JButton();
        shapeButton.addActionListener(e -> {

        });
        lineButton = new JButton("/");
        buttonPanel.add(shapeButton);
        buttonPanel.add(lineButton);
        JButton colorButton = new JButton("CLICK ME FOR COLOR");
        buttonPanel.add(colorButton);
        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(
                    PaintGUI.this,
                    "COLORS",
                    canvas.getColor());
            canvas.setColor(newColor);

        });

        add(buttonPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void addShape() {

    }

    public static void main(String[] args) {
        new PaintGUI().setVisible(true);
    }

}
