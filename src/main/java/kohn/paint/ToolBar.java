package kohn.paint;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ToolBar extends JPanel {

    private Tool pencil = new PencilTool();
    private Tool rectangleTool = new RectangleTool();
    private Tool eraserTool = new EraserTool();

    public ToolBar(Canvas canvas){

        Border border = new EmptyBorder(5,5,5,5);
        setBorder(border);
        setBackground(Color.lightGray);
        setLayout((new GridLayout(0,1,20, 10)));
        canvas.setTool(pencil);

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
                    ToolBar.this,
                    "COLORS",
                    canvas.getColor());
            canvas.setColor(newColor);
        });

        add(undoButton);
        add(eraserButton);
        add(fillButton);
        add(shapeButton);
        add(lineButton);
        add(colorButton);
    }
}
