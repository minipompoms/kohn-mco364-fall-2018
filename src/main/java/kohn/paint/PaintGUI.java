package kohn.paint;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaintGUI extends JFrame {

    private Canvas canvas;


    private Tool pencil = new PencilTool();
    private Tool rectangleTool = new RectangleTool();
    private Tool eraserTool = new EraserTool();

    public PaintGUI() {

        setTitle("Paint");
        setSize(700, 550);
        setLocation(510, 250);
        canvas = new Canvas();
        canvas.setTool(pencil);
        Border border = new EmptyBorder(5,5,5,5);
        JPanel drawPanel = new JPanel(new GridLayout(0,1,20, 10));
        drawPanel.setBorder(border);
        drawPanel.setBackground(Color.lightGray);

        JButton undoButton = new JButton(new ImageIcon("src/images/undo_tool.png"));
        undoButton.addActionListener(e -> {
            canvas.undo();
        });

        JButton eraserButton = new JButton(new ImageIcon("src/images/eraser_tool.png"));
        eraserButton.addActionListener(e -> {
            canvas.setTool(eraserTool);
        });

        JButton shapeButton = new JButton(new ImageIcon("src/images/shape_tool.png"));
        shapeButton.addActionListener(e -> {
            canvas.setTool(rectangleTool);
            rectangleTool.isFilled(false);
        });

        JButton lineButton = new JButton(new ImageIcon("src/images/pencil_tool.png"));
        lineButton.addActionListener(e ->{
            canvas.setTool(pencil);

        });

        JButton fillButton = new JButton(new ImageIcon("src/images/shape_fill_tool.png"));
        fillButton.addActionListener(e ->{
            rectangleTool.isFilled(true);
            canvas.setTool(rectangleTool);
        });


        JButton colorButton = new JButton(new ImageIcon("src/images/color_tool.png"));
        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(
                    PaintGUI.this,
                    "COLORS",
                    canvas.getColor());
            canvas.setColor(newColor);

        });
        drawPanel.add(undoButton);
        drawPanel.add(eraserButton);
        drawPanel.add(fillButton);
        drawPanel.add(shapeButton);
        drawPanel.add(lineButton);
        drawPanel.add(colorButton);

        add(drawPanel, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    public static void main(String[] args) {

        new PaintGUI().setVisible(true);
    }

}
