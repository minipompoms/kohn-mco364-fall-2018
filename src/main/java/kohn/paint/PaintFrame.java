package kohn.paint;

import javax.swing.*;
import java.awt.*;

public class PaintFrame extends JFrame {

    private Canvas canvas;

    public PaintFrame() {

        setTitle("Paint");
        setSize(700, 550);
        setLocation(510, 250);
        canvas = new Canvas();
        ToolBar toolBar = new ToolBar(canvas);

        MenuBar mb = new MenuBar(canvas);
        JMenuBar menuBar = mb.createMenuBar();

        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }






}
