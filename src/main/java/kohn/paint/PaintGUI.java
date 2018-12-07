package kohn.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class PaintGUI extends JFrame {

    private Canvas canvas;


    private Tool pencil = new PencilTool();
    private Tool rectangleTool = new RectangleTool();
    private Tool eraserTool = new EraserTool();
    private BufferedImage bufferedImage;


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

        JButton saveButton = new JButton(new ImageIcon("src/images/file_icon.png"));
        saveButton.addActionListener(e -> {
            bufferedImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(), TYPE_INT_ARGB);
            Graphics2D g2 = bufferedImage.createGraphics();
            canvas.paint(g2);
            JFileChooser fileChooser = new JFileChooser("/src/");
            fileChooser.isFileSelectionEnabled();
            int returnValue = fileChooser.showOpenDialog(null);
            if(returnValue == JFileChooser.APPROVE_OPTION){

            }
            try {
                ImageIO.write(bufferedImage, "png", new File("saved_image.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            });



        JButton colorButton = new JButton(new ImageIcon("src/images/color_tool.png"));
        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(
                    PaintGUI.this,
                    "COLORS",
                    canvas.getColor());
            canvas.setColor(newColor);

        });

        drawPanel.add(saveButton);
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

    public void save(){
    }

    public static void main(String[] args) {

        new PaintGUI().setVisible(true);
    }

}
