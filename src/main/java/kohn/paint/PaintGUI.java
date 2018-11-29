package kohn.paint;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaintGUI extends JFrame {

    private Canvas canvas;
    private JButton shapeButton;
    private JButton lineButton;
    private Tool pencil = new PencilTool();
    private Tool rectangleTool = new RectangleTool();

    public PaintGUI() {
        setTitle("Paint");
        setSize(700, 550);
        setLocation(510, 250);
        canvas = new Canvas();
        canvas.setTool(pencil);
        Border border = new EmptyBorder(5,5,5,5);
        JPanel drawPanel = new JPanel(new GridLayout(0,1,20, 10));
        drawPanel.setBorder(border);
        //JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 40, 20));
        drawPanel.setBackground(Color.lightGray);
        shapeButton = new JButton(new ImageIcon("src/images/shapes_tool.png"));

        shapeButton.addActionListener(e -> {
            canvas.setTool(rectangleTool);
        });
        lineButton = new JButton(new ImageIcon("src/images/pencil_tool.png"));
        lineButton.addActionListener(e ->{
            canvas.setTool(pencil);

        });

        drawPanel.add(shapeButton);
        drawPanel.add(lineButton);
        JButton colorButton = new JButton(new ImageIcon("src/images/color_tool.png"));
        drawPanel.add(colorButton);
        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(
                    PaintGUI.this,
                    "COLORS",
                    canvas.getColor());
            canvas.setColor(newColor);

        });
        add(drawPanel, BorderLayout.WEST);
       // add(buttonPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    public static void main(String[] args) {

        new PaintGUI().setVisible(true);
    }

}
